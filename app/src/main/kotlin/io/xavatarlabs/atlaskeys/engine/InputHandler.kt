package io.xavatarlabs.atlaskeys.engine

// Android
import android.view.KeyEvent
import android.view.inputmethod.InputConnection

// Atlaskeys
import io.xavatarlabs.atlaskeys.keyboard.Key
import io.xavatarlabs.atlaskeys.keyboard.KeyType


class InputHandler(
  private val ic: () -> InputConnection?,
  private val state: KeyboardState,
  private val refresh: () -> Unit,
  private val switchSymbols: (Boolean) -> Unit,
  private val feedback: (Key) -> Unit
) {


  fun handleKeyPress(key: Key) {

    feedback(key)

    val conn = ic() ?: return


    when (key.type) {


      // Text

      KeyType.CHAR -> {

        val output =
          if (state.shift)
            key.label.uppercase()
          else
            key.label.lowercase()


        conn.commitText(output, 1)


        if (state.shift) {

          state.shift = false
          refresh()

        }
      }


      // Editing

      KeyType.DELETE -> {

        conn.deleteSurroundingText(
          1,
          0
        )

      }


      // Keyboard

      KeyType.SHIFT -> {

        state.shift = !state.shift

        refresh()

      }


      KeyType.SYMBOLS -> {
        state.symbols = true
        switchSymbols(true)
      }


      KeyType.ABC -> {
        state.symbols = false
        switchSymbols(false)
      }


      // Text Controls

      KeyType.SPACE -> {
        conn.commitText(
          " ",
          1
        )
      }


      KeyType.ENTER -> {

        conn.sendKeyEvent(
          KeyEvent(
            KeyEvent.ACTION_DOWN,
            KeyEvent.KEYCODE_ENTER
          )
        )

        conn.sendKeyEvent(
          KeyEvent(
            KeyEvent.ACTION_UP,
            KeyEvent.KEYCODE_ENTER
          )
        )

      }


      KeyType.TAB -> {
        conn.commitText(
          "\t",
          1
        )
      }


      // Panels

      KeyType.EMOJI -> {
        // TODO Open emoji panel
      }


      KeyType.SETTINGS -> {
        // TODO Open settings panel
      }


      KeyType.CLIPBOARD -> {
        // TODO Open clipboard history
      }


      // System

      KeyType.LANGUAGE -> {
        // TODO Switch language
      }


      KeyType.GLOBE -> {
        // TODO Switch input method
      }


      // Editing

      KeyType.COPY -> {
        // TODO Copy
      }


      KeyType.CUT -> {
        // TODO Cut
      }


      KeyType.PASTE -> {
        // TODO Paste
      }


      KeyType.SELECT_ALL -> {
        // TODO Select all
      }


      KeyType.UNDO -> {
        // TODO Undo
      }


      KeyType.REDO -> {
        // TODO Redo
      }


      // Navigation

      KeyType.ARROW_LEFT -> {
        // TODO Move cursor left
      }


      KeyType.ARROW_RIGHT -> {
        // TODO Move cursor right
      }


      KeyType.ARROW_UP -> {
        // TODO Move cursor up
      }


      KeyType.ARROW_DOWN -> {
        // TODO Move cursor down
      }


      KeyType.HOME -> {
        // TODO Move cursor beginning
      }


      KeyType.END -> {
        // TODO Move cursor end
      }


      KeyType.PAGE_UP -> {
        // TODO Page up
      }


      KeyType.PAGE_DOWN -> {
        // TODO Page down
      }


      // Other

      KeyType.CAPS_LOCK -> {
        // TODO Toggle caps lock
      }


      KeyType.NUMBERS -> {
        // TODO Switch numbers layout
      }


      KeyType.FUNCTION -> {
        // TODO Switch function layout
      }


      KeyType.BACK -> {
        // TODO Hide keyboard
      }


      KeyType.ACTION -> {
        // TODO Handle editor action
      }


      KeyType.NONE -> {
        // Intentionally does nothing
      }
    }
  }
}