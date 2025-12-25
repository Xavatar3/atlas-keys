# AtlasKeys ⚡

AtlasKeys is an Android soft keyboard written in **Kotlin**, structured for easy **integration of new features**.  

> **Note:** This repository currently contains only the project skeleton. No keyboard functionality is implemented yet.

---

## Current Status 🟠

- Project skeleton and folder structure established  
- Gradle build partially configured for Android  
- Placeholder `Settings` activity and basic app setup present  
- No keyboard input features implemented yet

---

## Planned Features 🔧

- Rust-powered engine for predictive text, autocorrect, and swipe input  
- Customizable themes and keyboard layouts  
- Learning dictionary that adapts to the user's typing habits  
- Modular design for easy addition of new features  
- Cross-platform support in future versions

---

## Project Structure

```
atlas-keys/
├─ app/             # Kotlin Android app skeleton (UI & basic setup)
│   ├─ src/
│   │   ├─ main/
│   │   │   ├─ java/com/xavatar/atlaskeys/  # Kotlin package skeleton
│   │   │   ├─ res/                         # Placeholder resources (layouts, drawables)
│   │   │   └─ AndroidManifest.xml          # App manifest
├─ gradle/          # Gradle wrapper and build configuration files
├─ .github/         # CI/CD workflows (GitHub Actions)
├─ build.gradle     # Main Gradle build script
├─ gradlew          # Gradle wrapper for Linux/macOS
├─ gradlew.bat      # Gradle wrapper for Windows
├─ settings.gradle  # Multi-module Gradle settings
├─ LICENSE          # Apache 2.0 License
└─ README.md        # Project description
```

---

## Getting Started

  1. Clone the repository:
```
    bash
    git clone https://github.com/Xavatar3/atlas-keys.git
```
  2. Open the project in your preferred IDE (e.g., Android Studio).
  3. Explore the skeleton:
    - Review the app/src/main/java/com/xavatar/atlaskeys/ package
    - Check placeholder resources under res/
    - Open the Settings activity as a starting point for adding features💡
---

## Contribution

- Contributions are welcome, especially for setting up the Rust integration and implementing initial keyboard features 🔧.  
- Please maintain the separation between Kotlin and Rust modules when adding new features.  
- When contributing, ensure that any changes are compatible with the existing Gradle setup and project structure, or update the Gradle and CI configuration accordingly.
- Feel free to submit issues or pull requests for improvements, bug fixes, or feature proposals 💡.

---

## Notice / Disclaimer 📌

I am a beginner in Android development.  
I welcome any advice, constructive criticism, or suggestions to improve this project.  
Contributions, guidance, and feedback are highly appreciated! 💡

---

## License

AtlasKeys is licensed under the **Apache 2.0 License**.  
See the [`LICENSE`](LICENSE) file for full details.