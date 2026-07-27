package io.xavatarlabs.atlaskeys.keyboard

import io.xavatarlabs.atlaskeys.keyboard.key.KeyView 

class LayoutKeys {
  private val keys = mutableMapOf<String, KeyView>()
  fun contains(id: String): Boolean = keys.containsKey(id)
  fun add(id: String, key: KeyView){ keys[id] = key }
  fun remove(id: String) = keys.remove(id)
  fun keys(): Map<String, KeyView> = keys
  fun get(id: String) = keys[id]
  fun clear() = keys.clear()
}