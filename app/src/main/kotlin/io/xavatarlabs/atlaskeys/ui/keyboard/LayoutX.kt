package io.xavatarlabs.atlaskeys.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class LayoutX @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): FrameLayout(context, attrs) {
  
  init {
    //layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
  }
  
  fun setLayout(keys: List<List<String>>){//List<KeyRow>
    removeAllViews()
    // build rows here
  }
}
