package io.xavatarlabs.atlaskeys.layout

// Android
import android.content.Context
import android.widget.LinearLayout

// Atlaskeys
import io.xavatarlabs.atlaskeys.R
import io.xavatarlabs.atlaskeys.engine.State
import io.xavatarlabs.atlaskeys.structures.Key
import io.xavatarlabs.atlaskeys.structures.KeyRow
import io.xavatarlabs.atlaskeys.structures.KeyView


abstract class KeyboardLayout(
    protected val context: Context,
    protected val state: State,
    protected val onKeyClick: (Key) -> Unit
) {

    fun create(): LinearLayout {

        val keyboard = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        buildRows().forEach { row ->
            keyboard.addView(createRow(row))
        }

        return keyboard
    }


    protected abstract fun buildRows(): List<KeyRow>


    private fun createRow(row: KeyRow): LinearLayout {

        val layout = LinearLayout(context)

        row.keys.forEach { key ->

            val view = KeyView(context).apply {

                tag = key

                bind(key, state)

                layoutParams = LinearLayout.LayoutParams(
                  0,
                  dp(row.height),
                  key.width
                ).apply {
                  val margin = context.resources.getDimensionPixelSize(R.dimen.key_mgn)
                  setMargins(margin, margin, margin, margin)
                }

                setOnClickListener {
                    onKeyClick(key)
                }
            }

            layout.addView(view)
        }

        return layout
    }


    private fun dp(value: Int): Int {
        return (
            value * context.resources.displayMetrics.density
        ).toInt()
    }
}