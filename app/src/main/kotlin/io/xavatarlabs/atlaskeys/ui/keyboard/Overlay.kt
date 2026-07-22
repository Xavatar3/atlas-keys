package io.xavatarlabs.atlaskeys.ui

import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
//import android.view.ViewGroup.LayoutParams.MATCH_PARENT

class Overlay @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): FrameLayout(context, attrs) {
  
  init {
    visibility = GONE
    isClickable = true
    isFocusable = true
    //layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
  }
  
  fun show() { visibility = View.VISIBLE }
  
  fun hide() {
    visibility = View.GONE
  }
  
  fun clear() { removeAllViews() }
  
  fun display(view: View) {
    removeAllViews()
    if(view.parent != null){ (view.parent as? ViewGroup)?.removeView(view) }
    //addView(view, LayoutParams(MATCH_PARENT, MATCH_PARENT))
    //addView(view, FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
    addView(
      view,
      FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
      )
    )
    show()
  }
}
