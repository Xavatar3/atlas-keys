package io.xavatarlabs.atlaskeys.engine.kengine 

// Android
import android.content.Context 

// Atlaskeys
import io.xavatarlabs.atlaskeys.engine.kengine.runtime.RLayout 


object Repository{
  // TODO:
  // Replace memory cache with persistent storage.
  // Repository should decide storage strategy.
  // Repository:
  //   MemoryCache
  //   DiskCache
  //   Database
  private val cache: MutableMap<String, RLayout> = mutableMapOf()

  //TODO:
  // Auto discovery for layouts
  // would be handy for plugins
  /* Returns a view of layout. */
  fun load(id: String): RLayout?{ return cache[id] }

  /* Saves Layout Models. */
  fun save(id: String, layout: RLayout){ cache[id] = layout }

  /* Removes one layout. */
  fun remove(id: String){ cache.remove(id) }

  /* Clears every cached layout. */
  fun clear(){ cache.clear() }

  /* Checks cache. */
  fun contains(id: String): Boolean{ return cache.containsKey(id) }
}