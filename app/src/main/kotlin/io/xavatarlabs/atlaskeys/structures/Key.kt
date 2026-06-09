package io.xavatarlabs.atlaskeys.structures

data class Key(
  val label: String,
  val width: Float = 2f,
  val type: Types = Types.CHAR,
  val longPress: List<String>? = null,
  val secondaryLabel: String? = null,
  var dirty: Boolean = true
)

data class KeyRow(
  val height: Int,
  val keys: List<Key>
)