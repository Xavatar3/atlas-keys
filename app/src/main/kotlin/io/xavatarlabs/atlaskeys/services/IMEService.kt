package io.xavatarlabs.atlaskeys.services

// Android
import android.view.View
import android.view.inputmethod.EditorInfo

// Androidx
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

// AtlasKeys
import io.xavatarlabs.atlaskeys.Keyboard
import io.xavatarlabs.atlaskeys.core.Atlas
import io.xavatarlabs.atlaskeys.controller.KController


class IMEService: ComposeIMEService(){
  private lateinit var keyboard: View
  private lateinit var controller: KController 

  override fun onCreate() {
    super.onCreate()
    Atlas.init(this)
    controller = KController()
    controller.create { content -> createComposeView(content) }
    keyboard = controller.createView()
  }

  override fun onCreateInputView(): View{
    window?.window?.decorView?.let{
      it.setViewTreeLifecycleOwner(this)
      it.setViewTreeViewModelStoreOwner(this)
      it.setViewTreeSavedStateRegistryOwner(this)
    }
    attachComposeOwners(keyboard)
    return keyboard
  }
  
  override fun onStartInput(info: EditorInfo?, restarting: Boolean){
    super.onStartInput(info, restarting)
  }

  override fun onStartInputView( info: EditorInfo?, restarting: Boolean){
    super.onStartInputView(info, restarting)
    controller.startInputView(currentInputConnection)
  }

  override fun onDestroy(){
    super.onDestroy()
    controller.destroy()
  }
}
// TODO
// Make Keyboardcome up after switching
// Also do stuff with those empty overrides