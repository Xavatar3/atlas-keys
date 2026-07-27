package io.xavatarlabs.atlaskeys.keyboard.layout.models


data class KModel(
  val id: String,
  val icon: String,
  val ktype: KType,
  val width: Float,
  val label: String?,
  val enabled: Boolean,
  val visible: Boolean,
  val hint: List<String>
)