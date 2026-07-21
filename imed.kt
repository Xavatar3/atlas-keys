package io.xavatarlabs.atlaskeys

import android.widget.FrameLayout
import android.widget.Button
import android.view.inputmethod.EditorInfo

import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.engine.InputHandler
import io.xavatarlabs.atlaskeys.layout.qwertyMatrix
import io.xavatarlabs.atlaskeys.structures.KeyView
import io.xavatarlabs.atlaskeys.structures.KeyboardRenderer
import io.xavatarlabs.atlaskeys.ui.Settings

import android.inputmethodservice.InputMethodService
import android.view.View
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

class IMEService: InputMethodService(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner{

private lateinit var root: FrameLayout
private lateinit var body: FrameLayout
private lateinit var overlay: FrameLayout
private var showingSettings = false
private val state = State()
private lateinit var renderer: KeyboardRenderer
private lateinit var inputHandler: InputHandler
private lateinit var settingsBtn: Button

private val lifecycleRegistry = LifecycleRegistry(this)
private val internalViewModelStore = ViewModelStore()
private val savedStateController = SavedStateRegistryController.create(this)
override val lifecycle: Lifecycle
get() = lifecycleRegistry
override val viewModelStore: ViewModelStore
get() = internalViewModelStore
override val savedStateRegistry: SavedStateRegistry
get() = savedStateController.savedStateRegistry

override fun onCreate() {
super.onCreate()
savedStateController.performRestore(null)
lifecycleRegistry.currentState = Lifecycle.State.CREATED
}

override fun onCreateInputView(): View{
window?.window?.decorView?.let{
it.setViewTreeLifecycleOwner(this)
it.setViewTreeViewModelStoreOwner(this)
it.setViewTreeSavedStateRegistryOwner(this)
}

val view = layoutInflater.inflate(R.layout.keyboard_root, null)  
  
settingsBtn = view.findViewById(R.id.btn_settings)  
root = view.findViewById(R.id.keyboard_root)  
body = view.findViewById(R.id.keyboard_body)  
overlay = view.findViewById(R.id.keyboard_overlay)  
settingsBtn.setOnClickListener { showSettingsPanel() }  
  
renderer = KeyboardRenderer(state) { key -> KeyView(this).apply {  
    tag = key  
    bind(key, state)  
    setOnClickListener { inputHandler.handleKeyPress(key) }  
  }  
}  
  
inputHandler = InputHandler(  
  { currentInputConnection },  
  state  
) {  
  renderer.refresh(root)  
}  
  
renderer.render(  
  body,  
  qwertyMatrix  
)  
return root  

/*return ComposeView(this).apply {  
  setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(lifecycle))  
  setContent {  
    MaterialTheme {  
      Text("COMPOSE TEST")  
    }  
  }  
}*/

}

private fun showSettingsPanel() {
val composeView = androidx.compose.ui.platform.ComposeView(this)

composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(lifecycle))  
composeView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT)  
  
composeView.setContent {  
  MaterialTheme {  
    Settings(  
      onClose = {  
        showKeyboardPanel()  
      }  
    )  
  }  
}  
  
overlay.removeAllViews()  
overlay.addView(composeView)  
overlay.visibility = View.VISIBLE  
overlay.bringToFront()  
showingSettings = true

}

private fun showKeyboardPanel() {
showingSettings = false
overlay.visibility = View.GONE
overlay.removeAllViews()
renderer.render(body, qwertyMatrix)
}

override fun onDestroy() {
lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
internalViewModelStore.clear()
super.onDestroy()
}

override fun onStartInputView(info: EditorInfo?, restarting: Boolean){
super.onStartInputView(info, restarting)
state.shift = false
if (!showingSettings) {renderer.refresh(root)}
}
}

give me the new imeservice.kt and order and group imports well