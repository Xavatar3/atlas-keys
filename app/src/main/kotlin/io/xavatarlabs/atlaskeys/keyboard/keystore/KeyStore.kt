package io.xavatarlabs.atlaskeys.keyboard

import io.xavatarlabs.atlaskeys.keyboard.layout.Category 

object KeyStore {
  val symbols = LayoutKeys()
  val emoji   = LayoutKeys()
  val math    = LayoutKeys()
  val qwerty  = LayoutKeys()
 
  private val custom = mutableMapOf<String, LayoutKeys>()

  fun get(category: Category): LayoutKeys {
    val id = category.name.lowercase()
    return when(id) {
      "qwerty" -> qwerty
      "symbols" -> symbols
      "math" -> math
      "emoji" -> emoji
      else -> custom.getOrPut(id){ LayoutKeys() }
    }
  }

  fun clear() {
    symbols.clear()
    custom.clear()
    qwerty.clear()
    emoji.clear()
    math.clear()
  }
}