package io.xavatarlabs.atlaskeys

import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Button
import android.view.inputmethod.EditorInfo
import android.inputmethodservice.InputMethodService
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner


import io.xavatarlabs.atlaskeys.ui.Settings
import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.engine.InputHandler
import io.xavatarlabs.atlaskeys.layout.qwertyMatrix
import io.xavatarlabs.atlaskeys.structures.KeyView
import io.xavatarlabs.atlaskeys.structures.KeyboardRenderer

class IMEService : InputMethodService() {
  private lateinit var root: FrameLayout
  private lateinit var body: FrameLayout
  private lateinit var overlay: FrameLayout
  private lateinit var imeLifecycleOwner: ImeLifecycleOwner
  private var showingSettings = false
  private val state = State()
  
  private lateinit var renderer: KeyboardRenderer
  private lateinit var inputHandler: InputHandler
  private lateinit var settingsBtn: Button

  override fun onCreate() {
      super.onCreate()
      imeLifecycleOwner = ImeLifecycleOwner()
      imeLifecycleOwner.onCreate()
    }
  
  override fun onCreateInputView(): View {
    imeLifecycleOwner.onStart()
    imeLifecycleOwner.onResume()
    val view = layoutInflater.inflate(R.layout.keyboard_root, null)
    view.setViewTreeLifecycleOwner(imeLifecycleOwner)
    view.setViewTreeSavedStateRegistryOwner(imeLifecycleOwner)
    view.setViewTreeViewModelStoreOwner(imeLifecycleOwner)
    
    //imeLifecycleOwner = ImeLifecycleOwner()
    //imeLifecycleOwner.onCreate()
    settingsBtn = view.findViewById<Button>(R.id.btn_settings)
    root = view.findViewById(R.id.keyboard_root)
    body = view.findViewById<FrameLayout>(R.id.keyboard_body)
    overlay = view.findViewById(R.id.keyboard_overlay)
    settingsBtn.setOnClickListener{
      showSettingsPanel()
    }

    renderer = KeyboardRenderer(state) { key ->
      KeyView(this).apply {
        tag = key
        bind(key, state)
        
        setOnClickListener {
          inputHandler.handleKeyPress(key)
        }
      }
    }

    inputHandler = InputHandler(
      { currentInputConnection },
      state
    ){ renderer.refresh(root) }

    renderer.render(body, qwertyMatrix)
    
    return root
  }
  override fun onDestroy() {
    imeLifecycleOwner.onDestroy()
    super.onDestroy()
  }
  
  /*
  private fun showSettingsPanel() {
    composeView.setContent {
      Settings(
        onClose = {
            showKeyboardPanel()
        }
      )
    }
  }
  */
  /*
  private fun showSettingsPanel() {
    val textView = android.widget.TextView(this).apply {
      text = "HELLO"
      textSize = 30f
      setTextColor(android.graphics.Color.WHITE)
      layoutParams = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
      )
    }*/
    //settingsBtn.text = "😒"
    //val composeView = androidx.compose.ui.platform.ComposeView(this)
    
    //composeView.setBackgroundColor(android.graphics.Color.RED)
    /*composeView.setContent {
      Text("HELLO")
    }*/
    /*
    composeView.layoutParams = FrameLayout.LayoutParams(
      FrameLayout.LayoutParams.MATCH_PARENT,
      FrameLayout.LayoutParams.MATCH_PARENT
    )

  composeView.setViewCompositionStrategy(
    androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow
  )

  composeView.setContent {
    androidx.compose.material3.Surface(
      modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
      androidx.compose.material3.Text(
        text = "HELLO"
      )
    }
  }
  */
/*
  settingsBtn.text = "😒"
  
  overlay.removeAllViews()
  //overlay.addView(composeView)
  overlay.addView(textView)
  overlay.visibility = View.VISIBLE
  showingSettings = true
}
*/

private fun showSettingsPanel() {
    //val composeView = androidx.compose.ui.platform.ComposeView(this)
    val composeView = androidx.compose.ui.platform.ComposeView(overlay.context)

    composeView.layoutParams =
        FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

    /*
    composeView.setViewTreeLifecycleOwner(imeLifecycleOwner)
    composeView.setViewTreeSavedStateRegistryOwner(imeLifecycleOwner)
    composeView.setViewTreeViewModelStoreOwner(imeLifecycleOwner)
    */

    composeView.setContent {
      androidx.compose.foundation.layout.Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Red)
        )
    }

    overlay.removeAllViews()
    overlay.addView(composeView)
    overlay.visibility = View.VISIBLE
    composeView.createComposition()
    showingSettings = true
}
  
  private fun showKeyboardPanel() {
    showingSettings = false
    overlay.visibility = View.GONE
    overlay.removeAllViews()
    renderer.render(body, qwertyMatrix)
  }
  
  override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
    super.onStartInputView(info, restarting)

    //requestShowSelf(android.inputmethodservice.InputMethodService.SHOW_IMPLICIT)
    state.shift = false
    if (!showingSettings) {
      renderer.refresh(root)
    }
  }
}
