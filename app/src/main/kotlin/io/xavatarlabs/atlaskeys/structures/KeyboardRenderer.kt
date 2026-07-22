package io.xavatarlabs.atlaskeys.structures

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import io.xavatarlabs.atlaskeys.engine.State

class KeyboardRenderer(
  private val state: State,
  private val onKeyView: (Key) -> KeyView
){
  fun dp(parent: ViewGroup, value:Int): Int{
    return (value * parent.resources.displayMetrics.density).toInt()
  }
  
  fun render(target: FrameLayout, layout: List<KeyRow>) {
  target.removeAllViews()

  val vertical = LinearLayout(target.context).apply {
    orientation = LinearLayout.VERTICAL
  }

  layout.forEach { row ->
    vertical.addView(
      createRow(target, row.keys, row.height) { key ->
        val view = onKeyView(key)
        view.layoutParams = LinearLayout.LayoutParams(
          0,
          dp(target, row.height),
          key.width
        )
        view
      }
    )
  }

  target.addView(vertical)
}

  fun xrefresh(root: FrameLayout) {
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
  fun refresh(target: FrameLayout) {
    val vertical = target.getChildAt(0) as? LinearLayout ?: return

    for (i in 0 until vertical.childCount) {
        val row = vertical.getChildAt(i) as? LinearLayout ?: continue

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