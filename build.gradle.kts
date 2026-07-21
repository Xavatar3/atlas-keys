// Project plugins(Centralized versions)
plugins {
    id("com.android.application") version "8.9.1" apply false // Android Application (APKs)
    id("org.jetbrains.kotlin.android") version "2.3.0" apply false // Kotlin for Android
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.0" apply false
    id("io.github.reactivecircus.app-versioning") version "1.5.0" apply false // Git Versioning
}

// Clear Build Directory
tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}