package io.xavatarlabs.atlaskeys.engine

import android.view.KeyEvent
import android.view.inputmethod.InputConnection
import io.xavatarlabs.atlaskeys.structures.Key
import io.xavatarlabs.atlaskeys.structures.Types

class InputHandler(
  private val ic: () -> InputConnection?,
  private val state: State,
  private val refresh: () -> Unit,
  private val switchSymbols: (Boolean) -> Unit
  //private val onLayoutChanged: () -> Unit
){

  fun handleKeyPress(key: Key) {

  val conn = ic() ?: return

  when (key.type) {
    Types.CHAR -> {
      val output =
        if (state.shift) key.label.uppercase()
        else key.label.lowercase()
      conn.commitText(output, 1)
      if (state.shift) {
        state.shift = false
        refresh()
      }
    }

    Types.SHIFT -> {
      state.shift = !state.shift
      refresh()
    }

    Types.DELETE ->
      conn.deleteSurroundingText(1, 0)

    Types.SPACE ->
      conn.commitText(" ", 1)

    Types.ENTER -> {
      conn.sendKeyEvent(
        KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
      )
      conn.sendKeyEvent(
        KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER)
      )
    }

    Types.TAB ->
      conn.commitText("\t", 1)

    Types.SYMBOLS -> {
      state.symbols = true
      //onLayoutChanged()
      switchSymbols(true)
      refresh()
    }

    Types.ABC -> {
      state.symbols = false
      refresh()
      //onLayoutChanged()
      switchSymbols(false)
    }
    
    Types.LANGUAGE -> {
      // TODO Switch to next enabled keyboard
    }

    Types.EMOJI -> {
      // TODO Open emoji panel
    }

    Types.SETTINGS -> {
      // TODO Open AtlasKeys settings
    }

    Types.CLIPBOARD -> {
      // TODO Open clipboard history
    }

    Types.MIC -> {
      // TODO Launch voice input
    }

    Types.GLOBE -> {
      // TODO Switch input method
    }

    Types.CAPS_LOCK -> {
      // TODO Toggle Caps Lock
    }

    Types.NUMBERS -> {
      // TODO Switch to numbers layout
    }

    Types.FUNCTION -> {
      // TODO Switch to function layout
    }

    Types.ARROW_LEFT -> {
      // TODO Move cursor left
    }

    Types.ARROW_RIGHT -> {
      // TODO Move cursor right
    }

    Types.ARROW_UP -> {
      // TODO Move cursor up
    }

    Types.ARROW_DOWN -> {
      // TODO Move cursor down
    }

    Types.HOME -> {
      // TODO Move to beginning
    }

    Types.END -> {
      // TODO Move to end
    }

    Types.PAGE_UP -> {
      // TODO Page up
    }

    Types.PAGE_DOWN -> {
      // TODO Page down
    }

    Types.SELECT_ALL -> {
      // TODO Select all
    }

    Types.COPY -> {
      // TODO Copy
    }

    Types.CUT -> {
      // TODO Cut
    }

    Types.PASTE -> {
      // TODO Paste
    }

    Types.UNDO -> {
      // TODO Undo
    }

    Types.REDO -> {
      // TODO Redo
    }

    Types.BACK -> {
      // TODO Hide keyboard
    }

    Types.NONE -> {
      // Intentionally does nothing
    }
    
    Types.ACTION -> {
    // TODO Handle editor action
    }
  }
}
}
  /*fun handleKeyPress(key: Key) {

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
  }*/



/*
     * TODO
     *
     * Types.CAPS_LOCK
     *   - Toggle persistent uppercase.
     *
     * Types.LANGUAGE
     *   - Switch to next enabled keyboard.
     *
     * Types.EMOJI
     *   - Open emoji keyboard/panel.
     *
     * Types.SETTINGS
     *   - Open AtlasKeys settings activity.
     *
     * Types.CLIPBOARD
     *   - Open clipboard history.
     *
     * Types.MIC
     *   - Launch voice input.
     *
     * Types.GLOBE
     *   - Switch input method.
     *
     * Types.NUMBERS
     *   - Switch to numeric layout.
     *
     * Types.FUNCTION
     *   - Switch to function layout.
     *
     * Types.ARROW_LEFT
     * Types.ARROW_RIGHT
     * Types.ARROW_UP
     * Types.ARROW_DOWN
     *   - Move cursor.
     *
     * Types.HOME
     * Types.END
     *   - Cursor navigation.
     *
     * Types.PAGE_UP
     * Types.PAGE_DOWN
     *   - Scroll/page navigation.
     *
     * Types.SELECT_ALL
     * Types.COPY
     * Types.CUT
     * Types.PASTE
     * Types.UNDO
     * Types.REDO
     *   - Text editing actions.
     *
     * Types.BACK
     *   - Hide keyboard.
     *
     * Types.NONE
     *   - No action.
     */
