# SAMP Launcher — resultado da decompilação

Análise estática do APK anexado. O aplicativo não foi executado.

## Incluído
- 5 classes Java do launcher: SplashActivity, MainActivity, DownloadService, FCMService e LinksModel.
- AndroidManifest.xml decodificado e diretório `res/`.

## Deliberadamente excluído
- Código da árvore `com.sampunity.game`.
- Assets brutos do jogo e demais arquivos do APK que não são necessários para examinar o launcher.

## Marca atualizada
- Nome exibido do aplicativo: `Red Play Samp`.
- Título da notificação de download: `Red Play Samp — downloading game files`.
- Identificadores internos, como o package name e os IDs de backend, foram preservados.

## Notas
- O manifesto indica pacote `com.sampunity.game`, versão `1.1.15` (versionCode 40), minSdk 26 e targetSdk 36.
- `MainActivity` e `FCMService` foram gerados no modo simplificado do JADX porque a reconstrução normal do fluxo de controle deixou stubs. O resultado pode ser menos legível e não deve ser tratado como o fonte original exato.
- Este pacote não é um projeto Android compilável: dependências e classes fora do escopo não foram incluídas, e código decompilado pode diferir do código-fonte original.
