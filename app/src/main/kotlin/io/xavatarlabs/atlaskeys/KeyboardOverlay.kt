package io.xavatarlabs.atlaskeys.keyboard

import android.view.View
import android.widget.FrameLayout

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

import io.xavatarlabs.atlaskeys.ImeLifecycleOwner
import io.xavatarlabs.atlaskeys.ui.Settings

class KeyboardOverlay(
  private val overlay: FrameLayout,
  private val lifecycleOwner: ImeLifecycleOwner,
  private val onClose: () -> Unit
) {

  fun showSettings() {

    val composeView = ComposeView(
      overlay.context
    )

    composeView.setViewTreeLifecycleOwner(
      lifecycleOwner
    )

    composeView.setViewTreeViewModelStoreOwner(
      lifecycleOwner
    )

    composeView.setViewTreeSavedStateRegistryOwner(
      lifecycleOwner
    )

    composeView.setViewCompositionStrategy(
      ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
        lifecycleOwner.lifecycle
      )
    )

    composeView.setContent {

      MaterialTheme {

        Settings(
          onClose = onClose
        )

      }
    }

    overlay.removeAllViews()

    overlay.addView(composeView)

    overlay.visibility = View.VISIBLE

    overlay.bringToFront()
  }


  fun hide() {

    overlay.visibility = View.GONE

    overlay.removeAllViews()
  }
}