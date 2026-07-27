package io.xavatarlabs.atlaskeys.keyboard.state.events

import io.xavatarlabs.atlaskeys.keyboard.state.events.News 

class NewsReader {
  private val listeners = mutableListOf<Listener>()
  fun addListener(listener: Listener){ listeners.add(listener) }
  fun read(info: News){ listeners.forEach { it.receive(info) } }
  fun removeListener(listener: Listener){ listeners.remove(listener) }
}