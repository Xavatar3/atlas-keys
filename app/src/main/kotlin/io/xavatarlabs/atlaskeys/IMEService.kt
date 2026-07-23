package io.xavatarlabs.atlaskeys

// Android
import android.view.View
//import android.widget.Button
import android.widget.FrameLayout
import android.view.inputmethod.EditorInfo

// Androidx
import androidx.compose.material3.MaterialTheme

// Function Extensions
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

// AtlasKeys
import io.xavatarlabs.atlaskeys.ui.Panel
import io.xavatarlabs.atlaskeys.ui.Overlay
import io.xavatarlabs.atlaskeys.ui.LayoutX
import io.xavatarlabs.atlaskeys.ui.Settings
import io.xavatarlabs.atlaskeys.ui.Keyboard
import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.engine.Feedback
import io.xavatarlabs.atlaskeys.engine.InputHandler
import io.xavatarlabs.atlaskeys.layout.SymbolsLayout
import io.xavatarlabs.atlaskeys.layout.QwertyLayout
import io.xavatarlabs.atlaskeys.core.BaseComposeIMEService
//import io.xavatarlabs.atlaskeys.structures.KeyView
//import io.xavatarlabs.atlaskeys.layout.qwertyMatrix
//import io.xavatarlabs.atlaskeys.structures.KeyboardRenderer

class IMEService: BaseComposeIMEService(){
  private val state = State()
  private var currentPanel = Panel.KEYBOARD
  private lateinit var overlay: Overlay
  private lateinit var root: FrameLayout
  private lateinit var body: LayoutX
  private lateinit var feedback: Feedback
  private lateinit var keyboard: Keyboard
  private lateinit var inputHandler: InputHandler
  //private lateinit var renderer: KeyboardRenderer
  //private lateinit var body: FrameLayout
  //private lateinit var overlay: FrameLayout
  //private lateinit var settingsBtn: Button

  override fun onCreateInputView(): View{
    window?.window?.decorView?.let{
      it.setViewTreeLifecycleOwner(this)
      it.setViewTreeViewModelStoreOwner(this)
      it.setViewTreeSavedStateRegistryOwner(this)
    }
    
    val view = layoutInflater.inflate(R.layout.keyboard, null)
    
    root = view.findViewById(R.id.root)  
    attachComposeOwners(root)
    
    keyboard = view.findViewById(R.id.keyboard)
    feedback = Feedback(this)
    overlay = keyboard.overlay
    body = keyboard.layout
    body.state = state
    //overlay = view.findViewById(R.id.overlay)  
    //body = view.findViewById(R.id.keyboard_body)  
    //settingsBtn = view.findViewById(R.id.btn_settings)  
    //settingsBtn.setOnClickListener {showPanel(Panel.SETTINGS)}
    keyboard.controls.setOnSettingsClick{
      showPanel(Panel.SETTINGS)
    }
  
    /*renderer = KeyboardRenderer(state) { key -> KeyView(this).apply {  
        tag = key  
        bind(key, state)  
        setOnClickListener { inputHandler.handleKeyPress(key) }  
      }  
    }*/  
    
    /*
    inputHandler = InputHandler(
      { currentInputConnection }, state
    ){ body.refresh() }
    */
    /*inputHandler = InputHandler(
    { currentInputConnection }, state, { body.refresh() },
    {
        if (state.symbols) {
            body.render(SymbolsLayout(this, state) {
                inputHandler.handleKeyPress(it)
            })
        } else {
            body.render(QwertyLayout(this, state) {
                inputHandler.handleKeyPress(it)
            })
        }
    }
  )*/
    
    inputHandler = InputHandler(
      { currentInputConnection },
      state, { body.refresh() },
      { renderKeyboard() },
      { feedback.key(root) }
    )
    
    body.render(QwertyLayout(this, state){ inputHandler.handleKeyPress(it) })
    //renderer.render(body, qwertyMatrix)  
    return root  
  }
  
  private fun renderKeyboard() {
  body.render(
    if (state.symbols)
      SymbolsLayout(this, state) {
        inputHandler.handleKeyPress(it)
      }
    else
      QwertyLayout(this, state) { key ->
        inputHandler.handleKeyPress(key)
      }
      /*QwertyLayout(this, state) {
        inputHandler.handleKeyPress(it)
      }*/
  )
  }
  
  private fun showPanel(panel: Panel) {
    currentPanel = panel
    overlay.clear()
    when(panel) {
      Panel.KEYBOARD -> {
        body.visibility = View.VISIBLE
        overlay.hide()
        body.refresh()
      }
      
      Panel.SETTINGS -> {
        val composeView = createComposeView {
          MaterialTheme {
            Settings(onClose = {
              showPanel(Panel.KEYBOARD)
            })
          }
        }
        body.visibility = View.GONE
        overlay.display(composeView)
        overlay.bringToFront()
      }
      
      Panel.SYMBOLS -> {}

      Panel.EMOJIS -> {}
    }
  }

  override fun onStartInputView(info: EditorInfo?, restarting: Boolean){
    super.onStartInputView(info, restarting)
    state.shift = false
    if (currentPanel == Panel.KEYBOARD) {body.refresh()}
  }
  
  override fun onDestroy() {
    feedback.release()
    super.onDestroy()
  }
}
