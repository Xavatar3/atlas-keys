package io.xavatarlabs.atlaskeys.keyboard.input

// Android
import android.view.inputmethod.InputConnection 

// AtlasKeys
import io.xavatarlabs.atlaskeys.engine.runtime.RKey 
import io.xavatarlabs.atlaskeys.keyboard.layout.models.KType 


object InputEngine {
  private var connection: InputConnection? = null
  private var actionListener: ((Actions) -> Unit)? = null

  private fun toAction(key: RKey): Actions{
    return when(key.ktype) {
      KType.CHAR    ->  Actions.TypeWrite(key.label)
      KType.DELETE  ->  Actions.Delete
      KType.SPACE   ->  Actions.Space
      KType.ENTER   ->  Actions.Enter
      KType.EMOJI   ->  Actions.Emoji
      KType.SHIFT   ->  Actions.Shift
      KType.SYMBOLS ->  Actions.Symbols
      KType.ACTION  ->  Actions.Custom(key.id)
    }
  }

  private fun commit(action: Actions){
    when(action){
      is Actions.TypeWrite -> connection?.commitText(action.text, 1)
      Actions.Space        -> connection?.commitText(" ", 1)
      Actions.Delete       -> connection?.deleteSurroundingText(1, 0)
      Actions.Enter        -> connection?.sendKeyEvent(android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_ENTER))
      else -> { /* kater */}
    }
  }

  fun setActionListener(listener: (Actions) -> Unit) { actionListener = listener }

  fun attach(connection: InputConnection?){ this.connection = connection }

  fun detach(){ connection = null }

  fun dispatch(key: RKey) { // later return true or error
    connection ?: return
    val action = toAction(key)
    when(action) {
      is Actions.TypeWrite, Actions.Space,
      Actions.Delete, Actions.Enter -> { commit(action) }
      else -> { actionListener?.invoke(action) }
    }
  }
}