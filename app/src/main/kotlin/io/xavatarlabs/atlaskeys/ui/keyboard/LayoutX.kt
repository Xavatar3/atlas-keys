package io.xavatarlabs.atlaskeys.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout

import io.xavatarlabs.atlaskeys.engine.KeyboardState
import io.xavatarlabs.atlaskeys.keyboard.Key
import io.xavatarlabs.atlaskeys.keyboard.Renderer
import io.xavatarlabs.atlaskeys.layout.BaseLayout


class LayoutX @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

  private lateinit var state: KeyboardState
  private lateinit var renderer: Renderer
  private lateinit var onKeyClick: (Key) -> Unit

  private var keyboardView: LinearLayout? = null


  fun setup(
    renderer: Renderer,
    state: KeyboardState,
    onKeyClick: (Key) -> Unit
)
{
    this.renderer = renderer
    this.state = state
    this.onKeyClick = onKeyClick
}


  fun render(layout: BaseLayout) {

    removeAllViews()

    keyboardView =
    renderer.render(
        layout.rows,
        state,
        onKeyClick
    )

    addView(
      keyboardView,
      LayoutParams(
        LayoutParams.MATCH_PARENT,
        LayoutParams.MATCH_PARENT
      )
    )
  }


  fun refresh(state: KeyboardState) {

    keyboardView?.let {

      renderer.refresh(
        it,
        state
      )

    }

  }
}