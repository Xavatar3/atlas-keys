package io.xavatarlabs.atlaskeys.keyboard.state.events

interface Listener{
  fun receive(info: News) = reaction(info)
  fun reaction(info: News)
}