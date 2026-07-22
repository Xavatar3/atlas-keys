package io.xavatarlabs.atlaskeys

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class KeyboardView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
    }

    fun setLayout(layout: List<List<String>>) {
        removeAllViews()

        layout.forEach { row ->
            val rowLayout = LinearLayout(context).apply {
                orientation = HORIZONTAL
            }

            row.forEach { label ->
                val key = KeyView(context)
                key.text = label
                rowLayout.addView(key)
            }

            addView(rowLayout)
        }
    }
}
