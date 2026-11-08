plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
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
    compileOnly("com.android.tools.build:gradle:8.9.1")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.0")
}
