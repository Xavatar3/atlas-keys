package io.xavatarlabs.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import com.android.build.api.dsl.ApplicationExtension

open class VersioningExtension {
    var enabled = true
}

@GradlePlugin(id = "io.xavatarlabs.versioning")
class VersioningPlugin : Plugin<Project> {
  override fun apply(p: Project){
    p.extensions.create(
      "versioning",
      VersioningExtension::class.java
    )
    p.pluginManager.withPlugin("com.android.application") {
      configureVersion(p)
    }
  }
  
  fun semanticVersion(git: GitInfo): String {
    val tag = git.latestTag()?.removePrefix("v") ?: return "0.0.0" // e.g. v1.2.0
    val parts = tag.split(".")
    if (parts.size < 2) return "0.0.0"
    val major = parts.getOrNull(0) ?: "0"
    val minor = parts.getOrNull(1) ?: "0"
    val patch = git.commitsSinceLastTag()

    return "$major.$minor.$patch"
  }
  
  private fun configureVersion(p: Project){
    val git = GitInfo(p)
    val commitCount = git.commitCount()
    val versionDate = git.commitDate()
    val versionTime = git.commitTime()
    val versionCommitCount = commitCount.toString()
    val versionCode = commitCount 
    val versionSemantic = semanticVersion(git)


    val ext = p.extensions.getByType(
      VersioningExtension::class.java
    )
    if (!ext.enabled) return

    // Inject directly into project
    p.pluginManager.withPlugin("com.android.application"){
      val android = p.extensions.getByType(ApplicationExtension::class.java)
      android.defaultConfig.apply {
        versionName = versionSemantic
        versionCode = versionCode
        
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
          "VERSION_COMMIT_COUNT",
          "\"$versionCommitCount\""
        )
      }
    }
  }
}
