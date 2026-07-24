package io.xavatarlabs.atlaskeys.keyboard


data class Key(
  val label: String,
  val width: Float = 1f,
  val type: KeyType = KeyType.CHAR,
  val alternatives: List<String> = emptyList(),
  val secondaryLabel: String? = null,
  val id: String = label.lowercase()
)