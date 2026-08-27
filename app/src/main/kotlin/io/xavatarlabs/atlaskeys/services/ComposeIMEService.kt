package io.xavatarlabs.atlaskeys.services

// Wildcard Imports
import androidx.lifecycle.* // will refactor imports later
import androidx.savedstate.*

// Function Extensions
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

// Android
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.inputmethodservice.InputMethodService
import android.view.inputmethod.EditorInfo

// Androidx
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

abstract class ComposeIMEService: InputMethodService(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner{
  
  private val lifecycleRegistry = LifecycleRegistry(this)
  private val internalViewModelStore = ViewModelStore()
  private val savedStateController = SavedStateRegistryController.create(this)
  
  override val lifecycle: Lifecycle
  get() = lifecycleRegistry
  override val viewModelStore: ViewModelStore
  get() = internalViewModelStore
  override val savedStateRegistry: SavedStateRegistry
  get() = savedStateController.savedStateRegistry
  
  override fun onCreate(){
    super.onCreate()
    savedStateController.performRestore(null)
    lifecycleRegistry.currentState = Lifecycle.State.CREATED
  }
  
  /*override fun onCreateInputView(): View {
    lifecycleRegistry.currentState = Lifecycle.State.STARTED
  }*/
  
  override fun onStartInputView(info: EditorInfo?, restarting: Boolean){
    super.onStartInputView(info, restarting)
    lifecycleRegistry.currentState = Lifecycle.State.RESUMED
  }
  
  override fun onFinishInputView(finishingInput: Boolean){
    lifecycleRegistry.currentState = Lifecycle.State.STARTED
    super.onFinishInputView(finishingInput)
  }

  override fun onDestroy() {
    lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    internalViewModelStore.clear()
    super.onDestroy()
  }
  
  protected fun attachComposeOwners(view: View){
    view.setViewTreeLifecycleOwner(this)
    view.setViewTreeViewModelStoreOwner(this)
    view.setViewTreeSavedStateRegistryOwner(this)
  }
  
  protected fun createComposeView(content: @Composable () -> Unit): ComposeView {
    return ComposeView(this).apply{
      layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
      setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
      setContent { content() }
    }
  }
  
  protected fun markInputViewCreated() {
    lifecycleRegistry.currentState = Lifecycle.State.STARTED
  }
  
  protected fun saveState(bundle: Bundle){
    savedStateController.performSave(bundle)
  }
}
