package io.xavatarlabs.atlaskeys.structures

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import io.xavatarlabs.atlaskeys.engine.State

class KeyView(context: Context) : FrameLayout(context) {

  private val labelView = TextView(context)

  init {
    addView(labelView)

    labelView.layoutParams = LayoutParams(
      LayoutParams.MATCH_PARENT,
      LayoutParams.MATCH_PARENT
    )

    labelView.gravity = Gravity.CENTER
    labelView.textSize = 16f
  }

  fun bind(key: Key, state: State) {

    val text = when (key.type) {
      Types.CHAR ->
        if (state.shift) key.label.uppercase()
        else key.label.lowercase()

      else -> key.label
    }

    labelView.text = text
    key.dirty = false
  }
}