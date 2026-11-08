package io.xavatarlabs.buildlogic

import org.gradle.process.ExecOperations
import java.io.ByteArrayOutputStream
import java.io.File

fun runCommand(
    execOps: ExecOperations,
    workingDir: File,
    vararg args: String,
): String {
    val output = ByteArrayOutputStream()
    val error = ByteArrayOutputStream()
    val result = execOps.exec {
        commandLine(*args)
        this.workingDir = workingDir
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