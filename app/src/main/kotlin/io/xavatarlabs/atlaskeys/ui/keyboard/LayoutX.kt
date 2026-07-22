package io.xavatarlabs.atlaskeys.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.structures.KeyView
import io.xavatarlabs.atlaskeys.layout.KeyboardLayout

class LayoutX @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): FrameLayout(context, attrs) {
  
  lateinit var state: State
  
  init {
    //layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
  }
  
  fun render(layout: KeyboardLayout) {
    removeAllViews()
    addView(layout.create())
  }
  
  fun refresh() {
    val vertical = getChildAt(0) ?: return
    updateViews(vertical)
  }
  
  private fun updateViews(view: android.view.View) {
    if (view is KeyView) {
      val key = view.tag as? io.xavatarlabs.atlaskeys.structures.Key ?: return
      
      view.bind(key, state)
    }
    
    if (view is android.view.ViewGroup) {
      for (i in 0 until view.childCount) {
        updateViews(view.getChildAt(i))
      }
    }
  }
}

/*
class LayoutX @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): FrameLayout(context, attrs) {
  
  
  fun render(layout: List<KeyRow>) {
    removeAllViews()
    
    val vertical = LinearLayout(target.context).apply {
      orientation = LinearLayout.VERTICAL
    }
    
    layout.forEach { row ->
      vertical.addView(
        createRow(target, row.keys, row.height) { key ->
          val view = onKeyView(key)
          view.layoutParams = LinearLayout.LayoutParams(
            0, dp(target, row.height), key.width
          )
          view
        }
      )
    }
    
    target.addView(vertical)
  }
  
  fun refresh() {
    val vertical = this.getChildAt(0) as? LinearLayout ?: return

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




class KeyboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var state: State

    var onKeyClick: ((Key) -> Unit)? = null

    fun render(layout: KeyboardLayout) {
        removeAllViews()

        val vertical = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        layout.rows.forEach { row ->
            vertical.addView(createRow(row))
        }

        addView(vertical)
    }


    private fun createRow(row: KeyRow): LinearLayout {
        TODO("Implement row creation")
    }

    private fun createKey(key: Key): KeyView {
        return KeyView(context).apply {
            tag = key
            bind(key, state)

            setOnClickListener {
                onKeyClick?.invoke(key)
            }
        }
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()
}
*/