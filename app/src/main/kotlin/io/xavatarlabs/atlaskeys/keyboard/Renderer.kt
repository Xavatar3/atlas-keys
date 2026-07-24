package io.xavatarlabs.atlaskeys.keyboard

// Android
import android.content.Context
import android.widget.LinearLayout

// AtlasKeys
import io.xavatarlabs.atlaskeys.R
import io.xavatarlabs.atlaskeys.engine.KeyboardState


class Renderer(
  private val context: Context
) {


fun render(
    rows: List<KeyRow>,
    state: KeyboardState,
    onClick: (Key) -> Unit
): LinearLayout {
    val keyboard =
      LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
      }

    rows.forEach { row ->
  keyboard.addView(
    createRow(
      row,
      state,
      onClick
    )
  )
}
    return keyboard
}


  fun refresh(
    view: LinearLayout,
    state: KeyboardState
  ) {

    for (i in 0 until view.childCount) {

      val row =
        view.getChildAt(i)

      if (row !is LinearLayout) continue


      for (j in 0 until row.childCount) {

        val keyView =
          row.getChildAt(j)

        if (keyView is KeyView) {

          val key =
            keyView.tag as? Key ?: continue

          keyView.bind(
            key,
            state
          )

        }
      }
    }
  }

  private fun createRow(
    row: KeyRow,
    state: KeyboardState,
    onClick: (Key)->Unit
): LinearLayout {
    val layout =
      LinearLayout(context)

    row.keys.forEach { key ->
      val view =
        KeyView(context).apply {
          tag = key
          bind(
            key,
            state
          )

          setOnClickListener {
            onClick(key)
          }
        }
      view.layoutParams =
        LinearLayout.LayoutParams(
          0,
          dp(row.height),
          key.width
        )
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