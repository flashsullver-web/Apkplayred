#!/usr/bin/env python3
"""Apply the Red Play logo and solid watermark backgrounds to an Apktool tree."""

from __future__ import annotations

import argparse
import shutil
import subprocess
import sys
from pathlib import Path


SOLID_BACKGROUND = "#140b0d"
WATERMARK_OPACITY = "0.36"
LOGO_ASSETS = (
    Path("res/drawable/logo.png"),
    Path("res/drawable/app_logo.png"),
    Path("res/drawable-nodpi/hud_logo_unity.png"),
    Path("res/drawable-nodpi/logo_simbolo.png"),
)
BACKGROUND_ASSETS = (
    Path("res/drawable-nodpi/bg_abertura.jpg"),
    Path("res/drawable-nodpi/bg_config.jpg"),
    Path("res/drawable-nodpi/bg_fallback.png"),
    Path("res/drawable-nodpi/bg_inicio.jpg"),
    Path("res/drawable-nodpi/bg_servidores.jpg"),
)


def run_magick(arguments: list[str], output: Path) -> None:
    temporary = output.with_name(f"{output.stem}.redplay-tmp{output.suffix}")
    command = ["magick", *arguments, str(temporary)]
    try:
        subprocess.run(command, check=True)
        temporary.replace(output)
    finally:
        temporary.unlink(missing_ok=True)


def dimensions(path: Path) -> tuple[int, int]:
    result = subprocess.run(
        ["identify", "-format", "%w %h", str(path)],
        check=True,
        capture_output=True,
        text=True,
    )
    width, height = result.stdout.split()
    return int(width), int(height)


def build_transparent_logo(source: Path, output: Path) -> None:
    output.parent.mkdir(parents=True, exist_ok=True)
    run_magick(
        [
            str(source),
            "-alpha",
            "set",
            "-fuzz",
            "6%",
            "-fill",
            "none",
            "-draw",
            "color 0,0 floodfill",
            "-trim",
            "+repage",
            "-bordercolor",
            "none",
            "-border",
            "16",
        ],
        output,
    )


def place_logo_on_canvas(
    logo: Path,
    output: Path,
    width: int,
    height: int,
    size: int,
    background: str | None,
    opacity: str = "1.0",
) -> None:
    canvas = f"xc:{background}" if background else "xc:none"
    arguments = ["-size", f"{width}x{height}", canvas, "(", str(logo), "-resize", f"{size}x{size}"]
    if opacity != "1.0":
        arguments.extend(
            [
                "-channel",
                "A",
                "-evaluate",
                "multiply",
                opacity,
                "+channel",
            ]
        )
    arguments.extend([")", "-gravity", "center", "-composite"])
    if output.suffix.lower() in {".jpg", ".jpeg"}:
        arguments.extend(["-quality", "92"])
    run_magick(arguments, output)


def apply_logo_assets(root: Path, logo: Path) -> int:
    count = 0
    for relative_path in LOGO_ASSETS:
        output = root / relative_path
        if not output.is_file():
            raise FileNotFoundError(f"Expected logo resource is missing: {output}")
        if relative_path.name in {"logo.png", "app_logo.png"}:
            shutil.copy2(logo, output)
        else:
            width, height = dimensions(output)
            size = int(min(width, height) * 0.92)
            place_logo_on_canvas(logo, output, width, height, size, background=None)
        count += 1
    return count


def apply_launcher_icons(root: Path, logo: Path) -> int:
    count = 0
    for output in sorted(root.glob("res/mipmap-*/ic_launcher*.webp")):
        width, height = dimensions(output)
        size = int(min(width, height) * (0.78 if "foreground" not in output.name else 0.82))
        background = None if "foreground" in output.name else SOLID_BACKGROUND
        place_logo_on_canvas(logo, output, width, height, size, background=background)
        count += 1
    return count


def apply_backgrounds(root: Path, logo: Path) -> int:
    count = 0
    for relative_path in BACKGROUND_ASSETS:
        output = root / relative_path
        if not output.is_file():
            raise FileNotFoundError(f"Expected background resource is missing: {output}")
        width, height = dimensions(output)
        size = int(min(width, height) * 0.70)
        place_logo_on_canvas(
            logo,
            output,
            width,
            height,
            size,
            background=SOLID_BACKGROUND,
            opacity=WATERMARK_OPACITY,
        )
        count += 1
    return count


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("decoded_apk", type=Path, help="Apktool-decoded source directory")
    parser.add_argument(
        "--source-logo",
        type=Path,
        default=Path("attached_assets/image_579535d3_1794703528369.jpg"),
        help="Uploaded Red Play logo image",
    )
    parser.add_argument(
        "--logo-master",
        type=Path,
        default=Path("attached_assets/redplay_logo_transparent.png"),
        help="Output path for the transparent logo",
    )
    args = parser.parse_args()
    root = args.decoded_apk.resolve()
    source_logo = args.source_logo.resolve()
    logo_master = args.logo_master.resolve()
    if not (root / "AndroidManifest.xml").is_file():
        parser.error(f"Not an Apktool decode directory: {root}")
    if not source_logo.is_file():
        parser.error(f"Source logo is missing: {source_logo}")
    if not shutil.which("magick") or not shutil.which("identify"):
        parser.error("ImageMagick commands 'magick' and 'identify' are required.")

    try:
        build_transparent_logo(source_logo, logo_master)
        logo_count = apply_logo_assets(root, logo_master)
        icon_count = apply_launcher_icons(root, logo_master)
        background_count = apply_backgrounds(root, logo_master)
    except (FileNotFoundError, subprocess.CalledProcessError) as error:
        print(f"Artwork update failed: {error}", file=sys.stderr)
        return 1

    print(f"Transparent logo saved: {logo_master}")
    print(f"Named logo resources replaced: {logo_count}")
    print(f"Launcher icon images updated: {icon_count}")
    print(f"Solid watermark backgrounds updated: {background_count}")
    print(f"Background color: {SOLID_BACKGROUND}; watermark opacity: {WATERMARK_OPACITY}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
