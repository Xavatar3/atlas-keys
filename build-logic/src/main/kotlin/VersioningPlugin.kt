package io.xavatarlabs.buildlogic

import org.gradle.api.Plugin 
import org.gradle.api.Project 
import com.android.build.api.dsl.ApplicationExtension 
import com.android.build.api.variant.ApplicationAndroidComponentsExtension 

open class VersioningExtension {  var enabled = true }

@GradlePlugin(id = "io.xavatarlabs.versioning")
class VersioningPlugin : Plugin<Project> {
    override fun apply(project: Project){
        val extension = project.extensions.create("versioning", VersioningExtension::class.java)
        val android = project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
        project.pluginManager.withPlugin("com.android.application") {
            android.finalizeDsl { ext ->
                if (extension.enabled) { configureVersion(ext, project) }
            }
        }
    }

    private fun configureVersion(android: ApplicationExtension, project: Project){
        val git = GitInfo(project)
        val versionTime = git.commitTime()
        val versionDate = git.commitDate()
        val commitCount = git.commitCount()
        val versionSemantic = semanticVersion(git)
        
        android.defaultConfig.apply {
            versionName = versionSemantic
            versionCode = commitCount
            buildConfigField("String", "VERSION_DATE", "\"$versionDate\"")
            buildConfigField("String", "VERSION_TIME", "\"$versionTime\"")
            buildConfigField("String", "VERSION_SEMANTIC", "\"$versionSemantic\"")
            buildConfigField("int", "VERSION_COMMIT_COUNT", "$commitCount")
        }
    }

    fun semanticVersion(git: GitInfo): String {
        val tag = git.latestTag()?.removePrefix("v") ?: return "0.0.0" // e.g. v1.2.0
        val parts = tag.split(".")
        if (parts.size < 2) return "7.7.7"
        val major = parts.getOrNull(0) ?: "4"
        val minor = parts.getOrNull(1) ?: "4"
        val patch = git.commitsSinceLastTag()
        val commitCount = git.commitCount()
        return "x$major.$minor.$patch-dev.$commitCount"
    }
}
