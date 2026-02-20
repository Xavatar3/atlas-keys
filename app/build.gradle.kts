import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.io.ByteArrayOutputStream


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {

    namespace = "io.xavatarlabs.atlaskeys"
    compileSdk = 35 // Android 14 - Upside Down Cake
    
    defaultConfig {
      
      applicationId = "io.xavatarlabs.atlaskeys"
      minSdk = 23 //Android 6.0 - Marshmellow
      targetSdk = 34 //Android 7.0 - Nougat
      
      fun run(vararg args: String): String {
        val stdout = ByteArrayOutputStream()
        exec {
          commandLine(*args)
          standardOutput = stdout
        }
        return stdout.toString().trim()
      }
      
      //later alert time used
      val commitDateTime = try {
        OffsetDateTime.parse(run("git", "show", "-s", "--format=%cI", "HEAD"))
      } catch (e: Exception) {
        OffsetDateTime.now() // fallback
      }
      val versionDate: String = commitDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
      val versionTime: String = commitDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
      val versionCommitCount: String = try {
        run("git", "rev-list", "--count", "HEAD")
      } catch (e: Exception) {
        "null" // fallback if not a git repo
      }
      val versionSemantic: String = try {
        val version = run("git", "tag", "--sort=-creatordate")
          .lines()
          .map { it.trim() }
          .firstOrNull { tag ->
            run("git", "merge-base", "--is-ancestor", tag, "HEAD").let { result -> result.isEmpty() }
          } ?: "no-tag"
        //val version = run("git", "describe", "--tags", "--abbrev=0").split(".")
        val major = version.getOrNull(0)
        val minor = version.getOrNull(1)
        val tagCommitCount = run("git", "rev-list", "$version..HEAD", "--count")
        "$major.$minor.$tagCommitCount"
      } catch (e: Exception) {
        "null" // fallback
      }
      
      versionCode = versionCommitCount.toIntOrNull()
      versionName = versionSemantic
      
      //Later alert if version is null
      buildConfigField(
        "String",
        "VERSION_DATE",
        "\"$versionDate\""
      )
      buildConfigField(
        "String",
        "VERSION_TIME",
        "\"$versionTime\""
       )
      buildConfigField(
        "String",
        "VERSION_SEMANTIC",
        "\"$versionSemantic\""
      )
      buildConfigField(
        "String",
        "VERSION_COMMITCOUNT",
        "\"$versionCommitCount\""
      )
      
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

// Task equivalent
tasks.register("printVersionName") {
    doLast {
        println(android.defaultConfig.versionName)
    }
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



//Log errors and edgecases