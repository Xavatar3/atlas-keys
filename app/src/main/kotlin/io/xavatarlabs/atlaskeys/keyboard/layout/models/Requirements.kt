package io.xavatarlabs.atlaskeys.keyboard.layout.models


data class Requirements(
  val layoutVersion: Int,
  val engineVersion: Int,
  val features: List<String>
)