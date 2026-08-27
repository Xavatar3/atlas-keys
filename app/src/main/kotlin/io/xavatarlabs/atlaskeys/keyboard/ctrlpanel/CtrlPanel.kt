package io.xavatarlabs.atlaskeys.keyboard

// Android
import android.content.Context 
import android.widget.FrameLayout 
import android.view.ViewGroup.LayoutParams.MATCH_PARENT 

// AtlasKeys
import io.xavatarlabs.atlaskeys.R 
import io.xavatarlabs.atlaskeys.keyboard.State 
import io.xavatarlabs.atlaskeys.keyboard.ctrlpanel.Controls 

class CtrlPanel @JvmOverloads constructor(context: Context): FrameLayout(context) {
  // TODO:
  // Connect controls and suggestions through events.
  private var state: State? = null
  private val controls = Controls(context)
  private var onControlClick: ((String)->Unit)? = null

  init {
    controls.setOnControlClick { onControlClick?.invoke(it) }
    addView(controls, LayoutParams(MATCH_PARENT, MATCH_PARENT))
    //addView(Suggestions(context), LayoutParams(MATCH_PARENT, MATCH_PARENT))
  }
  fun setOnControlClick(listener: (String) -> Unit){ onControlClick = listener }
}
