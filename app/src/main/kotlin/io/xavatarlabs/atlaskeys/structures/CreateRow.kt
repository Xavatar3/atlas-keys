package io.xavatarlabs.atlaskeys.structures

import android.view.ViewGroup
import android.widget.LinearLayout

fun createRow(
  parent: ViewGroup, row: List<Key>,
  height: Int, createKey: (Key) -> ViewGroup
): LinearLayout {

  return LinearLayout(parent.context).apply {

    orientation = LinearLayout.HORIZONTAL

    layoutParams = LinearLayout.LayoutParams(
      LinearLayout.LayoutParams.MATCH_PARENT,
      LinearLayout.LayoutParams.WRAP_CONTENT
    )

    row.forEach { key ->
      addView(createKey(key))
    }
  }
}