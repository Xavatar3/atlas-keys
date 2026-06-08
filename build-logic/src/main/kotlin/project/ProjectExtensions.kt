package io.xavatarlabs.buildlogic

import org.gradle.api.Project
import java.io.ByteArrayOutputStream

fun Project.runCommand(vararg args: String): String {
  val output = ByteArrayOutputStream()
  val error = ByteArrayOutputStream()
  val result = exec {
    commandLine(*args)
    standardOutput = output
    errorOutput = error
    isIgnoreExitValue = true
  }
  
  if (result.exitValue != 0) {
    throw RuntimeException(
      "Command failed: ${args.joinToString(" ")}\n$error"
    )
  }

  return output.toString().trim()
}