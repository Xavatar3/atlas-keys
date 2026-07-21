package io.xavatarlabs.atlaskeys

// Android
import android.widget.Button
import android.widget.FrameLayout
import android.view.View
import android.view.inputmethod.EditorInfo

// Androidx
import androidx.compose.material3.MaterialTheme

// Function Extensions
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

// AtlasKeys
import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.engine.InputHandler
import io.xavatarlabs.atlaskeys.layout.qwertyMatrix
import io.xavatarlabs.atlaskeys.structures.KeyView
import io.xavatarlabs.atlaskeys.structures.KeyboardRenderer
import io.xavatarlabs.atlaskeys.ui.Settings
import io.xavatarlabs.atlaskeys.core.BaseComposeIMEService
import io.xavatarlabs.atlaskeys.ui.Panel

class IMEService: BaseComposeIMEService(){
  private lateinit var root: FrameLayout
  private lateinit var body: FrameLayout
  private lateinit var overlay: FrameLayout
  private var currentPanel = Panel.KEYBOARD
  private val state = State()
  private lateinit var renderer: KeyboardRenderer
  private lateinit var inputHandler: InputHandler
  private lateinit var settingsBtn: Button

  override fun onCreateInputView(): View{
    window?.window?.decorView?.let{
      it.setViewTreeLifecycleOwner(this)
      it.setViewTreeViewModelStoreOwner(this)
      it.setViewTreeSavedStateRegistryOwner(this)
    }
    
    val view = layoutInflater.inflate(R.layout.keyboard_root, null)
    
    root = view.findViewById(R.id.keyboard_root)  
    attachComposeOwners(root)
    
    body = view.findViewById(R.id.keyboard_body)  
    overlay = view.findViewById(R.id.keyboard_overlay)  
    settingsBtn = view.findViewById(R.id.btn_settings)  
    settingsBtn.setOnClickListener {showPanel(Panel.SETTINGS)}
  
    renderer = KeyboardRenderer(state) { key -> KeyView(this).apply {  
        tag = key  
        bind(key, state)  
        setOnClickListener { inputHandler.handleKeyPress(key) }  
      }  
    }  
    
    inputHandler = InputHandler({currentInputConnection },state  ){ renderer.refresh(root) }  
    
    renderer.render(body, qwertyMatrix)  
    return root  
  }

  private fun showPanel(panel: Panel) {
    currentPanel = panel
    overlay.removeAllViews()
    when(panel) {
      Panel.KEYBOARD -> {
        body.visibility = View.VISIBLE
        overlay.visibility = View.GONE
        renderer.refresh(root)
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
        overlay.addView(composeView)
        overlay.visibility = View.VISIBLE
        overlay.bringToFront()
      }
      
      Panel.SYMBOLS -> {}

      Panel.EMOJIS -> {}
    }
  }

  override fun onStartInputView(info: EditorInfo?, restarting: Boolean){
    super.onStartInputView(info, restarting)
    state.shift = false
    if (currentPanel == Panel.KEYBOARD) {renderer.refresh(root)}
  }
}
