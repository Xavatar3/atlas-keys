package io.xavatarlabs.atlaskeys.structures

import android.widget.FrameLayout
import android.widget.LinearLayout
import io.xavatarlabs.atlaskeys.engine.State

class KeyboardRenderer(
  private val state: State,
  private val onKeyView: (Key) -> KeyView
) {

  fun render(target: FrameLayout, layout: List<List<Key>>) {

    target.removeAllViews()

    val vertical = LinearLayout(target.context).apply {
      orientation = LinearLayout.VERTICAL
    }

    layout.forEach { row ->
      vertical.addView(createRow(target, row) { key ->
        val view = onKeyView(key)

        view.layoutParams = LinearLayout.LayoutParams(
          0,
          LinearLayout.LayoutParams.WRAP_CONTENT,
          key.widthWeight
        )

        view
      })
    }

    target.addView(vertical)
  }

  fun refresh(root: FrameLayout) {

    for (i in 0 until root.childCount) {
      val row = root.getChildAt(i)

      if (row !is LinearLayout) continue

      for (j in 0 until row.childCount) {
        val view = row.getChildAt(j)

        if (view is KeyView) {
          val key = view.tag as? Key ?: continue
          view.bind(key, state)
        }
      }
    }
  }
}