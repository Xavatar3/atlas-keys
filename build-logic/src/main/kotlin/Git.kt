package io.xavatarlabs.buildlogic

import org.gradle.process.ExecOperations
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class GitInfo(
    private val execOps: ExecOperations,
    private val workingDir: java.io.File,
) {
    // private helper — delegates to the extension, passing execOps + workingDir
    private fun git(vararg args: String): String =
        runCommand(execOps, workingDir, "git", *args)

    fun commitCount(): Int =
        try { git("rev-list", "--count", "HEAD").toIntOrNull() ?: 1 }
        catch (e: Exception) { 1 }

    fun commitDateTime(): OffsetDateTime =
        try { OffsetDateTime.parse(git("show", "-s", "--format=%cI", "HEAD")) }
        catch (e: Exception) { OffsetDateTime.now() }

    fun commitDate(): String =
        commitDateTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    fun commitTime(): String =
        commitDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    fun latestTag(): String? =
        try { git("describe", "--tags", "--abbrev=0") }
        catch (e: Exception) { null }

    fun commitsSinceLastTag(): Int {
        val tag = latestTag() ?: return 0
        return try { git("rev-list", "$tag..HEAD", "--count").toIntOrNull() ?: 0 }
        catch (e: Exception) { 0 }
    }

    fun branch(): String =
        try { git("rev-parse", "--abbrev-ref", "HEAD") }
        catch (e: Exception) { "unknown" }

    fun shortHash(): String =
        try { git("rev-parse", "--short", "HEAD") }
        catch (e: Exception) { "0000000" }

    fun fullHash(): String =
        try { git("rev-parse", "HEAD") }
        catch (e: Exception) { "0".repeat(40) }
}