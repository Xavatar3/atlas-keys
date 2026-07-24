package io.xavatarlabs.atlaskeys

// Android
import android.view.View
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
import io.xavatarlabs.atlaskeys.ui.Settings
import io.xavatarlabs.atlaskeys.ui.Keyboard
import io.xavatarlabs.atlaskeys.keyboard.Renderer
import io.xavatarlabs.atlaskeys.engine.Feedback
import io.xavatarlabs.atlaskeys.engine.InputHandler
import io.xavatarlabs.atlaskeys.layout.QwertyLayout
import io.xavatarlabs.atlaskeys.layout.SymbolsLayout
import io.xavatarlabs.atlaskeys.core.BaseComposeIMEService


class IMEService: BaseComposeIMEService(){

  private var currentPanel = Panel.KEYBOARD

  private lateinit var root: FrameLayout
  private lateinit var keyboard: Keyboard
  private lateinit var overlay: Overlay
  private lateinit var feedback: Feedback
  private lateinit var inputHandler: InputHandler


  override fun onCreateInputView(): View{
    window?.window?.decorView?.let{
      it.setViewTreeLifecycleOwner(this)
      it.setViewTreeViewModelStoreOwner(this)
      it.setViewTreeSavedStateRegistryOwner(this)
    }


    val view =
      layoutInflater.inflate(
        R.layout.keyboard,
        null
      )


    root = view.findViewById(R.id.root)
    attachComposeOwners(root)
    keyboard = view.findViewById(R.id.keyboard)
    overlay = keyboard.overlay

    feedback = Feedback(this)

   inputHandler =
    InputHandler(
        { currentInputConnection },
        keyboard.state,
        {
          keyboard.layout.refresh(keyboard.state)
        },
        { renderKeyboard() },
        { feedback.key() }
    )

    keyboard.layout.setup(
      Renderer(this),
      keyboard.state,
      inputHandler::handleKeyPress
    )

    keyboard.controls.setOnSettingsClick {
      showPanel(Panel.SETTINGS)
    }

    keyboard.layout.render(QwertyLayout)
    
    return root
  }

  private fun renderKeyboard(){
    keyboard.layout.render(
      if(keyboard.state.symbols)
        SymbolsLayout
      else
        QwertyLayout
    )
  }

  private fun showPanel(
    panel: Panel
  ){
    currentPanel = panel
    overlay.clear()

    when(panel){
      Panel.KEYBOARD -> {

        keyboard.layout.visibility =
          View.VISIBLE

        overlay.hide()

        keyboard.layout.refresh(
          keyboard.state
        )

      }

      Panel.SETTINGS -> {
        val composeView =
          createComposeView {
            MaterialTheme {
              Settings(
                onClose = {
                  showPanel(
                    Panel.KEYBOARD
                  )
                }
              )
            }
          }

        keyboard.layout.visibility = View.GONE
        overlay.display(
          composeView
        )
        overlay.bringToFront()
      }
      
      else -> {}
    }
  }

  override fun onStartInputView(
    info: EditorInfo?,
    restarting: Boolean
  ){
    super.onStartInputView(
      info,
      restarting
    )
    
    keyboard.state.shift = false

    if(currentPanel == Panel.KEYBOARD){
      keyboard.layout.refresh(
        keyboard.state
      )
    }
  }

  override fun onDestroy(){
    feedback.release()
    super.onDestroy()
  }
}