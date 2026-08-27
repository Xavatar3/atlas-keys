package io.xavatarlabs.atlaskeys.keyboard

import kotlin.reflect.KProperty 
import kotlin.properties.ReadWriteProperty 
import io.xavatarlabs.atlaskeys.keyboard.state.Events 
import io.xavatarlabs.atlaskeys.keyboard.state.events.News 
import io.xavatarlabs.atlaskeys.keyboard.state.events.Listener 

class State {
  // TODO:
  // Replace individual events with typed state changes.
  // Example:
  // ShiftChanged
  // LayoutChanged
  // ThemeChanged
   private val events = Events()

  // TODO:
  // Move state storage into KeyboardStateManager.
  // UI components should subscribe, not own state.
  var shift by manager(News.SHIFT, false)
  var symbols by manager(News.SYMBOLS, false)
  var emoji by manager(News.EMOJI, false)
  var language by manager(News.LANGUAGE, "en")
  private fun <T> manager(event: News, initial: T): ReadWriteProperty<State, T> {
    return object : ReadWriteProperty<State, T> {
      private var fact = initial
      
      override fun getValue(ref: State, property: KProperty<*>): T = fact
      
      override fun setValue(ref: State, property: KProperty<*>, news: T) {
        if (fact == news) return
        fact = news
        events.broadcast(event)
      }
    }
  }

  fun unsubscribe(listener: Listener) = events.unsubscribe(listener)

  fun subscribe(listener: Listener) = events.subscribe(listener)
}