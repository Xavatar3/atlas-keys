package io.xavatarlabs.atlaskeys.ui

// Android
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout


class Overlay @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): FrameLayout(context, attrs) {


  private var cachedView: View? = null


  init {

    // Attributes
    visibility = GONE
    isClickable = true
    isFocusable = true

  }


  fun show() {
    visibility = View.VISIBLE
  }


  fun hide() {
    visibility = View.GONE
  }


  fun clear() {

    removeAllViews()

  }


  fun display(view: View) {


    if(cachedView === view && childCount > 0) {

      show()
      return

    }


    cachedView = view


    clear()


    (view.parent as? ViewGroup)?.removeView(view)


    addView(
      view,
      LayoutParams(
        LayoutParams.MATCH_PARENT,
        LayoutParams.MATCH_PARENT
      )
    )


    show()

  }
}