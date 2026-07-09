package io.xavatarlabs.atlaskeys

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.FrameLayout

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

import io.xavatarlabs.atlaskeys.engine.InputHandler
import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.layout.qwertyMatrix
import io.xavatarlabs.atlaskeys.structures.KeyView
import io.xavatarlabs.atlaskeys.structures.KeyboardRenderer
import io.xavatarlabs.atlaskeys.ui.Settings

class IMEService : InputMethodService() {

  private lateinit var root: FrameLayout
  private lateinit var body: FrameLayout
  private lateinit var overlay: FrameLayout
  private lateinit var settingsBtn: Button

  private lateinit var renderer: KeyboardRenderer
  private lateinit var inputHandler: InputHandler

  private lateinit var imeLifecycleOwner: ImeLifecycleOwner

  private val state = State()

  private var showingSettings = false

  override fun onCreate() {
    super.onCreate()

    imeLifecycleOwner = ImeLifecycleOwner()
    imeLifecycleOwner.onCreate()
  }

  override fun onCreateInputView(): View {

    imeLifecycleOwner.onStart()
    imeLifecycleOwner.onResume()

    val view = layoutInflater.inflate(
      R.layout.keyboard_root,
      null
    )

    root = view.findViewById(R.id.keyboard_root)
    body = view.findViewById(R.id.keyboard_body)
    overlay = view.findViewById(R.id.keyboard_overlay)
    settingsBtn = view.findViewById(R.id.btn_settings)

    root.setViewTreeLifecycleOwner(imeLifecycleOwner)
    root.setViewTreeViewModelStoreOwner(imeLifecycleOwner)
    root.setViewTreeSavedStateRegistryOwner(imeLifecycleOwner)

    settingsBtn.setOnClickListener {
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
    ) {
      renderer.refresh(root)
    }

    renderer.render(
      body,
      qwertyMatrix
    )

    return root
  }

  private fun showSettingsPanel() {

    val composeView = ComposeView(this)

    composeView.setViewTreeLifecycleOwner(
      imeLifecycleOwner
    )

    composeView.setViewTreeViewModelStoreOwner(
      imeLifecycleOwner
    )

    composeView.setViewTreeSavedStateRegistryOwner(
      imeLifecycleOwner
    )

    composeView.setViewCompositionStrategy(
      ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
        imeLifecycleOwner.lifecycle
      )
    )

    composeView.layoutParams =
      FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
      )

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

    renderer.render(
      body,
      qwertyMatrix
    )
  }

  override fun onDestroy() {

    imeLifecycleOwner.onDestroy()

    super.onDestroy()
  }

  override fun onStartInputView(
    info: EditorInfo?,
    restarting: Boolean
  ) {

    super.onStartInputView(
      info,
      restarting
    )

    state.shift = false

    if (!showingSettings) {
      renderer.refresh(root)
    }
  }
}