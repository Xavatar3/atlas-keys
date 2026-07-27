package io.xavatarlabs.buildlogic

import org.gradle.api.Project 
import java.time.OffsetDateTime 
import java.time.format.DateTimeFormatter 

class GitInfo(private val p: Project){
    fun commitCount(): Int {
        return try {
            p.runCommand("git", "rev-list", "--count", "HEAD").trim().toIntOrNull() ?: 1
        } catch (e: Exception) { 1 }
    }

    fun commitDateTime(): OffsetDateTime {
        return try {
            OffsetDateTime.parse(p.runCommand("git", "show", "-s", "--format=%cI", "HEAD").trim())
        } catch (e: Exception){
            OffsetDateTime.now()
        }
    }

    fun commitDate(): String {
        return commitDateTime().format(
            DateTimeFormatter.ofPattern("yyyy.MM.dd")
        )
    }

    fun commitTime(): String {
        return commitDateTime().format(
            DateTimeFormatter.ofPattern("HH:mm:ss")
        )
    }

    fun latestTag(): String? {
        return try {
            p.runCommand("git", "describe", "--tags", "--abbrev=0").trim()
        } catch (e: Exception) { null }
    }

    fun commitsSinceLastTag(): Int {
        val tag = latestTag() ?: return 0
        return try {
            p.runCommand("git", "rev-list", "$tag..HEAD", "--count").trim().toIntOrNull() ?: 0
        } catch (e: Exception) { 0 }
    }

    fun branch(): String {
        return try {
            p.runCommand("git", "rev-parse", "--abbrev-ref", "HEAD").trim()
        } catch (e: Exception) { "unknown" }
    }

    fun shortHash(): String {
        return try {
            p.runCommand("git", "rev-parse", "--short", "HEAD").trim()
        } catch (e: Exception) { "0000000" }
    }

    fun fullHash(): String {
        return try {
            p.runCommand("git", "rev-parse", "HEAD").trim()
        } catch (e: Exception) { "0".repeat(40) }
    }
}
