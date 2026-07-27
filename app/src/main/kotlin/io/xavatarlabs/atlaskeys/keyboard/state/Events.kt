package io.xavatarlabs.atlaskeys.keyboard.state

import io.xavatarlabs.atlaskeys.keyboard.state.events.News 
import io.xavatarlabs.atlaskeys.keyboard.state.events.Listener 
import io.xavatarlabs.atlaskeys.keyboard.state.events.NewsReader 


class Events{
  val newsreader = NewsReader()
  
  fun subscribe(listener: Listener) { newsreader.addListener(listener) }
  fun broadcast(info: News){ newsreader.read(info) }
  fun unsubscribe(listener: Listener){ newsreader.removeListener(listener) }
}
// TODO:
// Replace simple event list with lifecycle-aware event bus.
// Prevent leaked listeners.