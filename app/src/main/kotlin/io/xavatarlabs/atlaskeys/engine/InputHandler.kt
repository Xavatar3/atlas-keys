package io.xavatarlabs.atlaskeys.engine

import android.view.KeyEvent
import android.view.inputmethod.InputConnection
import io.xavatarlabs.atlaskeys.structures.Key
import io.xavatarlabs.atlaskeys.structures.Types

class InputHandler(
  private val ic: InputConnection,
  private val state: State,
  private val refresh: () -> Unit
) {

  fun handleKeyPress(key: Key) {

    when (key.type) {

      Types.SHIFT -> {
        state.shift = !state.shift
        refresh()
      }

      Types.DELETE ->
        ic.deleteSurroundingText(1, 0)

      Types.SPACE ->
        ic.commitText(" ", 1)

      Types.ENTER ->
        ic.sendKeyEvent(
          KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
        )

      else -> {
        val output =
          if (state.shift) key.label.uppercase()
          else key.label.lowercase()

        ic.commitText(output, 1)

        if (state.shift) {
          state.shift = false
          refresh()
        }
      }
    }
  }
}