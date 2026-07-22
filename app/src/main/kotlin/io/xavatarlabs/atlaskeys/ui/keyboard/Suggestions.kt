package io.xavatarlabs.atlaskeys.ui

import android.graphics.Color
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup.LayoutParams.MATCH_PARENT


class Suggestions @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): RecyclerView(context, attrs) {
  
  init {
    //layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 30.dp)
    setBackgroundColor(Color.parseColor("#99222222")) // Color.parseColor("#99222222")
    overScrollMode = OVER_SCROLL_NEVER
    setPadding(8.dp, 0, 8.dp, 0)
    clipToPadding = false
    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT,30.dp)
  }
  
  //private fun Int.dp(): Int = (this * resources.displayMetrics.density).toInt()
  private val Int.dp: Int get() = (this * resources.displayMetrics.density).toInt()
  
  fun showSuggestions(words: List<String>){
    //adapter = SuggestionAdapter(words)
  }
}
