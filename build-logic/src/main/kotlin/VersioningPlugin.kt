package io.xavatarlabs.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.process.ExecOperations
import javax.inject.Inject

open class VersioningExtension {
    var enabled = true
}

class VersioningPlugin @Inject constructor(
    private val execOps: ExecOperations,
) : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create("versioning", VersioningExtension::class.java)

        project.pluginManager.withPlugin("com.android.application") {
            val android = project.extensions.getByType(
                com.android.build.api.variant.ApplicationAndroidComponentsExtension::class.java
            )
            android.finalizeDsl { ext ->
                if (extension.enabled) {
                    configureVersion(ext, project)
                }
            }
        }
    }

    private fun configureVersion(
        android: com.android.build.api.dsl.ApplicationExtension,
        project: Project,
    ) {
        val git = GitInfo(execOps, project.projectDir)  // ← pass execOps + dir
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

    private fun semanticVersion(git: GitInfo): String {
        val tag = git.latestTag()?.removePrefix("v") ?: return "0.0.0"
        val parts = tag.split(".")
        if (parts.size < 2) return "7.7.7"
        val major = parts.getOrNull(0) ?: "4"
        val minor = parts.getOrNull(1) ?: "4"
        val patch = git.commitsSinceLastTag()
        val commitCount = git.commitCount()
        return "x$major.$minor.$patch-dev.$commitCount"
    }
}