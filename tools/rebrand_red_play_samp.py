#!/usr/bin/env python3
"""Apply the Red Play Samp package and visual rebrand to an Apktool tree."""

from __future__ import annotations

import argparse
import re
import shutil
import subprocess
import sys
from pathlib import Path


OLD_PACKAGE = "com.sampunity.game"
NEW_PACKAGE = "com.redplay.game"
TEXT_SUFFIXES = {
    ".cfg",
    ".ini",
    ".json",
    ".manifest",
    ".md",
    ".mf",
    ".properties",
    ".smali",
    ".txt",
    ".xml",
    ".yml",
}
COLOR_REPLACEMENTS = {
    "#ffbd43ff": "#ffff3b30",
    "#ffa541ff": "#ffff7043",
    "#ff3fbbfe": "#ffe53935",
    "#ff9a35d4": "#ffb71c1c",
    "#ff3398cf": "#ffff7043",
    "#ff8636d4": "#ffd32f2f",
    "#fff6d20f": "#ff271114",
    "#ff3ddc84": "#ff271114",
    "#ff8a2019": "#ffff3b30",
    "#ff11151d": "#ff140b0d",
    "#ff00574b": "#ff8e0000",
}
APP_COLORS = {
    "colorAccent": "#ffff3b30",
    "colorOnlineStatus": "#ff43a047",
    "colorPrimary": "#ffe53935",
    "colorPrimaryDark": "#ff8e0000",
    "colorProgressBar": "#ffff453a",
    "colorSecondary": "#ffff7043",
    "orangeOutline": "#ffff7043",
    "purple_200": "#ffff8a80",
    "purple_500": "#ffe53935",
    "purple_700": "#ffb71c1c",
    "statusBarColor": "#ff8e0000",
    "m3_ref_palette_primary10": "#ff3b0808",
    "m3_ref_palette_primary30": "#ff7e1d1d",
    "m3_ref_palette_primary40": "#ffa52525",
    "m3_ref_palette_primary80": "#ffffb3aa",
    "m3_ref_palette_primary90": "#ffffdad5",
    "m3_ref_palette_secondary10": "#ff311306",
    "m3_ref_palette_secondary30": "#ff6e2b13",
    "m3_ref_palette_secondary40": "#ff8b3a1d",
    "m3_ref_palette_secondary80": "#ffffc4a8",
    "m3_ref_palette_secondary90": "#ffffdcc8",
    "m3_ref_palette_tertiary10": "#ff2d1502",
    "m3_ref_palette_tertiary30": "#ff6b3d08",
    "m3_ref_palette_tertiary40": "#ff8a5111",
    "m3_ref_palette_tertiary80": "#fff1bd7b",
    "m3_ref_palette_tertiary90": "#ffffdcab",
}
BRAND_TEXT = (
    ("SAMP Launcher Unity", "Red Play Samp"),
    ("SAMP Unity — downloading game files", "Red Play Samp — downloading game files"),
)
APP_VERSION_NAME = "1.v4"
SUPPORT_VERSION_LABEL = "RedPlay Game 1.v4 Copyright 2026"


def rename_smali_directories(root: Path) -> int:
    moved_files = 0
    for smali_root in root.glob("smali*"):
        old_dir = smali_root / "com" / "sampunity" / "game"
        if not old_dir.is_dir():
            continue
        new_dir = smali_root / "com" / "redplay" / "game"
        if new_dir.exists():
            raise FileExistsError(f"Target package directory already exists: {new_dir}")
        moved_files += sum(1 for item in old_dir.rglob("*") if item.is_file())
        new_dir.parent.mkdir(parents=True, exist_ok=True)
        shutil.move(str(old_dir), str(new_dir))
        old_package_parent = smali_root / "com" / "sampunity"
        if old_package_parent.exists() and not any(old_package_parent.iterdir()):
            old_package_parent.rmdir()
    return moved_files


def set_color_resource(content: str, name: str, value: str) -> tuple[str, bool]:
    pattern = re.compile(
        rf'(<color\s+name="{re.escape(name)}">)[^<]*(</color>)'
    )
    return pattern.subn(rf"\g<1>{value}\g<2>", content, count=1)[0], bool(
        pattern.search(content)
    )


def recolor_images(root: Path) -> int:
    magick = shutil.which("magick")
    if not magick:
        raise RuntimeError("ImageMagick 'magick' is required to recolor the app logos.")

    image_paths = [
        root / "res/drawable/app_logo.png",
        root / "res/drawable/logo.png",
        root / "res/drawable-nodpi/logo_simbolo.png",
    ]
    image_paths.extend(sorted(root.glob("res/mipmap-*/ic_launcher*.webp")))
    image_paths = [path for path in image_paths if path.is_file()]

    for image_path in image_paths:
        temp_path = image_path.with_name(
            f"{image_path.stem}.redplay{image_path.suffix}"
        )
        command = [magick, str(image_path)]
        if image_path.suffix.lower() == ".webp":
            command.extend(["-define", "webp:lossless=true"])
        command.extend(
            [
                "-colorspace",
                "HSL",
                "-channel",
                "R",
                "-evaluate",
                "set",
                "0.015",
                "+channel",
                "-colorspace",
                "sRGB",
                str(temp_path),
            ]
        )
        subprocess.run(command, check=True)
        temp_path.replace(image_path)

    launcher_background = root / "res/drawable/launcher_bg.png"
    if launcher_background.is_file():
        temp_path = launcher_background.with_name("launcher_bg.redplay.png")
        subprocess.run(
            [
                magick,
                str(launcher_background),
                "-colorspace",
                "HSL",
                "-channel",
                "R",
                "-fx",
                "g > 0.12 && r > 0.50 && r < 0.88 ? 0.98 : r",
                "+channel",
                "-colorspace",
                "sRGB",
                str(temp_path),
            ],
            check=True,
        )
        temp_path.replace(launcher_background)

    return len(image_paths) + int(launcher_background.is_file())


def apply_rebrand(root: Path) -> None:
    if not (root / "AndroidManifest.xml").is_file():
        raise FileNotFoundError(f"Not an Apktool decode directory: {root}")

    moved_classes = rename_smali_directories(root)
    changed_files = 0
    package_replacements = 0
    color_replacements = 0

    for path in root.rglob("*"):
        if not path.is_file() or path.suffix.lower() not in TEXT_SUFFIXES:
            continue
        try:
            content = path.read_text(encoding="utf-8")
        except UnicodeDecodeError:
            continue

        original = content
        package_replacements += content.count(OLD_PACKAGE)
        content = content.replace(OLD_PACKAGE, NEW_PACKAGE)
        for old, new in BRAND_TEXT:
            content = content.replace(old, new)

        if path.name == "strings.xml" and path.parent.name.startswith("values"):
            content, _ = re.subn(
                r'(<string\s+name="sp_versao">).*?(</string>)',
                lambda match: f"{match.group(1)}{SUPPORT_VERSION_LABEL}{match.group(2)}",
                content,
                count=1,
            )
        if path.name == "apktool.yml":
            content, _ = re.subn(
                r"(?m)^  versionName:.*$",
                f"  versionName: {APP_VERSION_NAME}",
                content,
                count=1,
            )

        if path.parent.name.startswith("values") and path.name == "colors.xml":
            for name, value in APP_COLORS.items():
                updated, changed = set_color_resource(content, name, value)
                if changed:
                    content = updated

        if path.suffix.lower() == ".xml":
            for old, new in COLOR_REPLACEMENTS.items():
                content, count = re.subn(
                    re.escape(old), new, content, flags=re.IGNORECASE
                )
                color_replacements += count

        if content != original:
            path.write_text(content, encoding="utf-8")
            changed_files += 1

    image_count = recolor_images(root)
    manifest = (root / "AndroidManifest.xml").read_text(encoding="utf-8")
    if not re.search(r'\bpackage="com\.redplay\.game"', manifest):
        raise RuntimeError("Manifest package was not changed to com.redplay.game.")

    leftovers = []
    for path in root.rglob("*"):
        if not path.is_file() or path.suffix.lower() not in TEXT_SUFFIXES:
            continue
        try:
            if OLD_PACKAGE in path.read_text(encoding="utf-8"):
                leftovers.append(str(path))
        except UnicodeDecodeError:
            continue
    old_dirs = list(root.glob("smali*/com/sampunity/game"))
    if leftovers or old_dirs:
        raise RuntimeError(
            "Old package references remain: "
            + ", ".join(leftovers[:10] + [str(path) for path in old_dirs[:10]])
        )

    print(f"Package references updated: {package_replacements}")
    print(f"Smali class files moved: {moved_classes}")
    print(f"Text/resource files changed: {changed_files}")
    print(f"Brand color literals updated: {color_replacements}")
    print(f"Logo/background images recolored: {image_count}")
    print("Manifest package verified: com.redplay.game")
    print("No old com.sampunity.game references remain in text files.")


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("decoded_apk", type=Path, help="Apktool-decoded source directory")
    args = parser.parse_args()
    try:
        apply_rebrand(args.decoded_apk.resolve())
    except (FileNotFoundError, FileExistsError, RuntimeError, subprocess.CalledProcessError) as error:
        print(f"Rebrand failed: {error}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
