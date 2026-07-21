package io.xavatarlabs.atlaskeys.engine

import android.view.KeyEvent
import android.view.inputmethod.InputConnection
import io.xavatarlabs.atlaskeys.structures.Key
import io.xavatarlabs.atlaskeys.structures.Types

class InputHandler(
  private val ic: () -> InputConnection?,
  private val state: State,
  private val refresh: () -> Unit
) {

  fun handleKeyPress(key: Key) {

    val conn = ic() ?: return

    when (key.type) {

      Types.SHIFT -> {
        state.shift = !state.shift
        refresh()
      }

      Types.DELETE ->
        conn.deleteSurroundingText(1, 0)

      Types.SPACE ->
        conn.commitText(" ", 1)

      Types.ENTER ->
        conn.sendKeyEvent(
          KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
        )

      else -> {
        val output =
          if (state.shift) key.label.uppercase()
          else key.label.lowercase()

        conn.commitText(output, 1)

        if (state.shift) {
          state.shift = false
          refresh()
        }
      }
    }
  }
}