plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("io.xavatarlabs.versioning")
}

android {
  namespace = "io.xavatarlabs.atlaskeys"
  compileSdk = 35 // Android 14 - UpsideDown Cake
  
  defaultConfig {
    applicationId = "io.xavatarlabs.atlaskeys"
    minSdk = 23 //Android 6.0 - Marshmellow
    targetSdk = 35 // API 35 (Android 15)
    versionCode = 0 // Set by versioning plugin
    versionName = "0.0.0" // Set by versioning plugin
  }
  
  buildFeatures {
    buildConfig = true // Build Constants
    viewBinding = true // XML Layout Constants
    compose = true // Compose Feautures
  }
  
  buildTypes {
      release {
        isMinifyEnabled = true
        proguardFiles(
          getDefaultProguardFile("proguard-android-optimize.txt"),
          "proguard-rules.pro"
        )
      }
      
      debug {
        isMinifyEnabled = false
      }
    }
  
  compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
  
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.15"
  }
  
  kotlinOptions {
        jvmTarget = "17"
    }
}

versioning {
    enabled = true
  }

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.preference:preference-ktx:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2026.01.01"))

    // Core Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3")

    debugImplementation("androidx.compose.ui:ui-tooling")
}
