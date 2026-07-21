package io.xavatarlabs.buildlogic

import org.gradle.api.Project
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class GitInfo(private val p: Project){
  fun commitCount(): Int {
    val res = p.runCommand("git", "rev-list", "--count", "HEAD")
    return res.toIntOrNull() ?: 1
  }
  
  fun commitDateTime(): OffsetDateTime {
    val raw = p.runCommand("git", "show", "-s", "--format=%cI", "HEAD")
    return try {
        OffsetDateTime.parse(raw)
    } catch (e: Exception) {
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
    val result = p.runCommand("git", "rev-list", "$tag..HEAD", "--count")
    return result.toIntOrNull() ?: 0
  }
  
  fun branch(): String {
    return p.runCommand("git", "rev-parse", "--abbrev-ref", "HEAD").trim()
  }
  
  fun shortHash(): String {
    return p.runCommand("git", "rev-parse", "--short", "HEAD").trim()
  }
  
  fun fullHash(): String {
    return p.runCommand("git", "rev-parse", "HEAD").trim()
  }
}
