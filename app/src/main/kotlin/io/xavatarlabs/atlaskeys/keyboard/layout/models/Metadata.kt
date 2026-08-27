package io.xavatarlabs.atlaskeys.keyboard.layout.models


data class Metadata(
  val language: String,
  val locale: String,
  val author: String,
  val description: String,
  val license: String,
  val created: String,
  val updated: String,
  val tags: List<String>
)