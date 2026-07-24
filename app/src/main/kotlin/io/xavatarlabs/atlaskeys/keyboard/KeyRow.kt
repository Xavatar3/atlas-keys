package io.xavatarlabs.atlaskeys.keyboard

data class KeyRow(
  val height: Int, // later make it independent of units
  val keys: List<Key>
)