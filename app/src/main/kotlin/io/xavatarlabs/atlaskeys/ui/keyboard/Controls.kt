package io.xavatarlabs.atlaskeys.ui

import android.view.Gravity
import android.widget.Button
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

class Controls @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): LinearLayout(context, attrs) {
  
  private var onSettingsClick: (() -> Unit)? = null
  
  init {
    // Attributes
    orientation = HORIZONTAL
    gravity = Gravity.CENTER_VERTICAL
    //layoutParams = LayoutParams(WRAP_CONTENT, MATCH_PARENT)
    
    // Children
    setControls(listOf("🌐", "🙂", "⇧", "⚙"))
  }
  
  fun setOnSettingsClick(listener: () -> Unit) {
    onSettingsClick = listener
  }
  fun setControls(controls: List<String>) {
    removeAllViews()
    controls.forEachIndexed { index, icon ->
      val button = Button(context).apply {
        text = icon
        if (icon == "⚙") {
          setOnClickListener {
            onSettingsClick?.invoke()
          }
        }
        //if (icon == "⚙"){ id = R.id.btn_settings }
      }
      addView(
        button,
        LinearLayout.LayoutParams(
          WRAP_CONTENT,
          MATCH_PARENT
        ).apply {
          if (index != controls.lastIndex){
            marginEnd = dpToPx(6)
          }
        }
      )
    }
  }
  
  private fun dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
}
