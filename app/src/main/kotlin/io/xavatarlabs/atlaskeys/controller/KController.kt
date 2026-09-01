package io.xavatarlabs.atlaskeys.controller

// Android
import android.view.View 
import android.view.inputmethod.InputConnection 

// AtlasKeys
import io.xavatarlabs.atlaskeys.Keyboard 
import io.xavatarlabs.atlaskeys.core.Atlas 
import androidx.compose.runtime.Composable 
import io.xavatarlabs.atlaskeys.settings.Panel 
import io.xavatarlabs.atlaskeys.keyboard.State 
import io.xavatarlabs.atlaskeys.engine.KEngine 
import androidx.compose.material3.MaterialTheme 
import io.xavatarlabs.atlaskeys.settings.Settings 
import io.xavatarlabs.atlaskeys.keyboard.KeyStore 
import io.xavatarlabs.atlaskeys.keyboard.input.Actions 
import io.xavatarlabs.atlaskeys.keyboard.layout.LayoutId 
import io.xavatarlabs.atlaskeys.keyboard.layout.Category 
import io.xavatarlabs.atlaskeys.keyboard.input.InputEngine 
import io.xavatarlabs.atlaskeys.keyboard.emoji.EmojiProvider 
import io.xavatarlabs.atlaskeys.keyboard.layout.models.KType 

class KController {
  private var shift = false
  private val state = State()
  private val kengine = KEngine()
  private val context = Atlas.context
  private val inputEngine = InputEngine
  private var ic: InputConnection? = null
  private val keyboard = Keyboard(context)
  private var composeViewFactory: ((@Composable () -> Unit) -> View)? = null

  init{
    keyboard.attachState(state)
    keyboard.attachEngine(kengine)
    InputEngine.setActionListener(::handleAction)
    keyboard.setOnControlClick { handleControl(it) }
  }

  private fun refresh() { /* keyboard.refresh() */ }

  private fun handleAction(action: Actions) {
    when(action) {
      Actions.Shift -> {
        shift = !shift
        KeyStore.get(Category.QWERTY).keys().forEach { (_, key) ->
          if (key.ktype == KType.CHAR) {    
            key.updateLabel(    
              if (shift)    
                key.label.uppercase()    
              else    
                key.label.lowercase()    
            )    
          }    
        }
      }
      Actions.Symbols -> {
        keyboard.switch(LayoutId("symbols"))
        KeyStore.get(Category.QWERTY).get("emoji")?.updateLabel(EmojiProvider.random())
        KeyStore.get(Category.SYMBOLS).get("emoji")?.updateLabel(EmojiProvider.random())
      }
      Actions.Emoji   -> {
        keyboard.switch(LayoutId("emoji"))
        KeyStore.get(Category.QWERTY).get("emoji")?.updateLabel(EmojiProvider.random())
        KeyStore.get(Category.SYMBOLS).get("emoji")?.updateLabel(EmojiProvider.random())
      }
      is Actions.Custom -> {
        when(action.id){
          "clear"    -> clear()
          "math"     -> keyboard.switch(LayoutId("math"))
          "abc"      -> keyboard.switch(LayoutId("qwerty"))
          "numpad"   -> keyboard.switch(LayoutId("numpad"))
          "symbols2" -> keyboard.switch(LayoutId("symbols2"))
        }
      }
      else -> { /* fater */}
    }
  }

  private fun handleControl(id: String){
    when(id){
      "🌐" -> showPanel(Panel.KEYBOARD)
      "📋" -> { /* clipboard kater */ }
      "↶" -> { /* undo zater */ }
      "↷" -> { /* redo yater */ }
      "⚙" -> showPanel(Panel.SETTINGS)
    }
  }

  /*private fun clear() {
    ic?.let { ic ->
      val limits = listOf(10000, 5000, 1000, 500, 100, 10)
      for (limit in limits) { if (clearRange(ic, limit)) { break } }
    }
  }*/
  private fun clear() {
  val connection = ic ?: return

  connection.beginBatchEdit()

  try {
    repeat(30) {
      connection.deleteSurroundingText(10000, 10000)
      val before = connection.getTextBeforeCursor(1, 0)
      val after = connection.getTextAfterCursor(1, 0)
      //if (before == 0) return
      if (before.isNullOrEmpty() && after.isNullOrEmpty()) { return }
    }
  } finally {
    connection.endBatchEdit()
  }
}

  private fun clearRange(ic: InputConnection, limit: Int): Boolean {
    return try {
      val before = ic.getTextBeforeCursor(limit, 0)?.length ?: 0
      val after = ic.getTextAfterCursor(limit, 0)?.length ?: 0
      if (before == 0 && after == 0) return true
      ic.deleteSurroundingText(before, after)
      val remainingBefore = ic.getTextBeforeCursor(limit, 0)?.length ?: 0
      val remainingAfter = ic.getTextAfterCursor(limit, 0)?.length ?: 0
      remainingBefore == 0 && remainingAfter == 0
    } catch (e: Exception) { false }
  }

  private fun detachInput() {
    ic = null
    inputEngine.detach()
  }

  /* Later experiment with fun bugs like duplicate commits,
  double letters, or ghost key presses. 👻 as a result of
  duplicate or old input connection */
  private fun attachInput(connection: InputConnection?) {
    detachInput()
    ic = connection
    inputEngine.attach(connection)
  }
  private fun attachFactory(factory: (@Composable () -> Unit) -> View){ composeViewFactory = factory }
  private fun showPanel(panel: Panel){
    when(panel){
      Panel.KEYBOARD -> { keyboard.showKeyboard() }
      Panel.SETTINGS -> {
        val composeView = composeViewFactory?.invoke{
          MaterialTheme{ Settings(onClose = { showPanel(Panel.KEYBOARD) }) }
        } ?: return
        keyboard.showOverlay(composeView)
      }
      else -> {}
    }
  }

  fun create(factory: (@Composable () -> Unit) -> View){ attachFactory(factory) }
  fun createView(): View = keyboard

  fun startInput() { /* Jater */ }

  fun startInputView(connection: InputConnection?) {
    attachInput(connection)
    refresh()
  }

  fun finishInput() { detachInput() }

  fun destroy() {
    detachInput()
    KeyStore.clear()
  }
}