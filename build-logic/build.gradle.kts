plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
  plugins {
    create("versioning") {
      id = "io.xavatarlabs.versioning"
      implementationClass = "io.xavatarlabs.buildlogic.VersioningPlugin"
    }
  }
}

dependencies {
    implementation("com.android.tools.build:gradle:8.9.1")
}