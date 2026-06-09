package io.xavatarlabs.atlaskeys.structures

data class Key(
  val label: String,
  val widthWeight: Float = 1f,
  val type: Types = Types.CHAR,
  val longPress: List<String>? = null,
  val secondaryLabel: String? = null,
  var dirty: Boolean = true
)