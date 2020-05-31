# Librarian
![CI](https://github.com/MeilCli/Koach/workflows/CI/badge.svg) [ ![Download](https://api.bintray.com/packages/meilcli/koach/koach/images/download.svg) ](https://bintray.com/meilcli/koach/koach/_latestVersion)

Koach is Android Library of CoachMark such as Onboarding or ShowCase

Koach can:
- multiple coach scene
- multiple overlay shape
- custom overlay shape
- overlay animation
- custom overlay animation
- custom coach view provide
- customizable layout coach view

## Getting Started
### Install

Koach is published on GitHub Packages and Bintray. So you choice maven repository.

using Bintray:
```groovy
buildscript {
    repositories {
        maven { url "https://dl.bintray.com/meilcli/koach" }
    }
}
```

using GitHub Packages:
```groovy
buildscript {
    repositories {
        maven {
            url "https://maven.pkg.github.com/MeilCli/Koach"
            credentials {
                username System.getenv("GITHUB_USER")
                password System.getenv("GITHUB_TOKEN") // token has permission of read:packages
            }
        }
    }
}
```

And set dependency:
```groovy
dependencies {
    implementation "net.meilcli.koach:koach:VERSION" // replace VERSION
}
```

### Usage
```kotlin
Coach.create(this, CoachOverlay()) {
    addScene(
        CoachScene(
            "example",
            RectOverlayShape(margin = 8.dp, radius = 4.dp),
            IdViewFinder(R.id.example), // target view finder
            TextViewProvider(
                text = "The example coach",
                textColor = Color.WHITE,
                textAppearance = R.style.TextAppearance_AppCompat_Body1,
                gravity = Gravity.CENTER
            ),
            AnchorCoachSceneLayoutProvider(
                anchorGravity = Gravity.CENTER,
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                marginVertical = 48.dp
            )
        )
    )
}.show()
```

### Sample
see [app](app) module

## License
Koach is MIT License

### Using Libraries
including binary:
- `koach`: [see link](Library/koach-usings-plugin)

when developing:
- `koach`: [see link](Library/koach-usings-development)