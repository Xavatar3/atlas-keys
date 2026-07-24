package io.xavatarlabs.atlaskeys.ui

// Android
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

// AtlasKeys
import io.xavatarlabs.atlaskeys.R
import io.xavatarlabs.atlaskeys.engine.KeyboardState


class Keyboard @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): LinearLayout(context, attrs){

  
  // Views
  private val suggestions = Suggestions(context)
  val controls = Controls(context)
  val layout = LayoutX(context)
  val overlay = Overlay(context)
  val state = KeyboardState()


  private val stage = FrameLayout(context)


  init {

    // Attributes
    orientation = VERTICAL
    setPadding(1, 1, 1, 1)


    // Stage
    stage.addView(
      layout,
      FrameLayout.LayoutParams(
        MATCH_PARENT,
        MATCH_PARENT
      )
    )


    stage.addView(
      overlay,
      FrameLayout.LayoutParams(
        MATCH_PARENT,
        MATCH_PARENT
      )
    )


    // Add Views
    addView(
      controls,
      LayoutParams(
        MATCH_PARENT,
        resources.getDimensionPixelSize(
          R.dimen.kb_ctrls_height
        )
      )
    )


    addView(
      suggestions,
      LayoutParams(
        MATCH_PARENT,
        WRAP_CONTENT
      )
    )


    addView(
      stage,
      LayoutParams(
        MATCH_PARENT,
        0,
        1f
      )
    )
  }


  fun setControlsClick(listener: () -> Unit) {
    controls.setOnSettingsClick(listener)
  }


  fun setHeight(ratio: Float) {

    val screenHeight =
      resources.displayMetrics.heightPixels


    layoutParams = layoutParams.apply {
      height = (screenHeight * ratio).toInt()
    }

    requestLayout()
  }


  fun setWidth(ratio: Float) {

    val screenWidth =
      resources.displayMetrics.widthPixels


    layoutParams = layoutParams.apply {
      width = (screenWidth * ratio).toInt()
    }

    requestLayout()
  }
}


// Later use percentages, px, dp, etc.