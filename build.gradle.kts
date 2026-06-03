// Project plugins(Centralized versions)
plugins {
    id("com.android.application") version "8.6.0" apply false // Android Application (APKs)
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false // Kotlin for Android
    id("io.github.reactivecircus.app-versioning") version "1.5.0" apply false // Git Versioning
}

// Clear Build Directory
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}