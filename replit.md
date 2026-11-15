# Red Play Samp

An independent Android app build using the user's authorized SA-MP base, with Red Play Samp branding and package ID `com.redplay.game`.

## Run & Operate

- `pnpm --filter @workspace/api-server run dev` — run the API server (port 5000)
- `pnpm run typecheck` — full typecheck across all packages
- `pnpm run build` — typecheck + build all packages
- `pnpm --filter @workspace/api-spec run codegen` — regenerate API hooks and Zod schemas from the OpenAPI spec
- `pnpm --filter @workspace/db run push` — push DB schema changes (dev only)
- Required env: `DATABASE_URL` — Postgres connection string

## Stack

- pnpm workspaces, Node.js 24, TypeScript 5.9
- API: Express 5
- DB: PostgreSQL + Drizzle ORM
- Validation: Zod (`zod/v4`), `drizzle-zod`
- API codegen: Orval (from OpenAPI spec)
- Build: esbuild (CJS bundle)
- Android package edits: Apktool, Java, ImageMagick, and apksigner

## Where things live

- `tools/rebrand_red_play_samp.py` applies the package, text, and color changes to an Apktool-decoded tree.
- `.local/redplay/apktool-tree/` holds the decoded working copy outside Git and deployment.
- `generated-artifacts/` holds built APKs and other large outputs; it is ignored by Git and deployment.

## Architecture decisions

- The new app uses package ID and game class namespace `com.redplay.game`; keep class descriptors, XML views, manifest names, permissions, and provider authorities in sync.
- The original APK stays unchanged. Repackage and sign the new app separately so it can coexist with the sold app.
- The user confirmed they retain the right to reuse this app base and its assets for this separate edition.
- The new brand uses Red Play Samp with a red, warm-orange, and dark-charcoal palette.
- Use the user's city-and-car RedPlay logo for app, HUD, and launcher marks, with its white page background removed.
- Use solid `#140b0d` backgrounds with a centered, semi-transparent logo watermark for `bg_abertura`, `bg_config`, `bg_fallback`, `bg_inicio`, and `bg_servidores`.
- The Settings support label and Android version name are `RedPlay Game 1.v4 Copyright 2026` and `1.v4`; keep the package version code unchanged unless the user asks.

## Product

The new Android edition keeps the existing game base while changing the app identity and visual branding.

## User preferences

_Populate as you build — explicit user instructions worth remembering across sessions._

## Gotchas

- Rebuilding changes the APK signature. A new signing key creates a separate test build; it cannot update the previously signed app.
- The Firebase and Play licensing configuration still needs to be checked for the new package before relying on push notifications or store licensing.
- Never remove or bypass the existing licensing or integrity checks as part of rebranding.
- The original SAMP Unity app installs on the user's phone; the RedPlay single-APK test showed an Android compatibility warning while split requirements were present.
- Removing split metadata is only a standalone test attempt; it does not supply files that may exist in the original ABI/density splits or make the APK production-ready.

## Pointers

- See the `pnpm-workspace` skill for workspace structure, TypeScript setup, and package details
