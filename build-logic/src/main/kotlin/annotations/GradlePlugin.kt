package io.xavatarlabs.buildlogic

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class GradlePlugin(
    val id: String
)
