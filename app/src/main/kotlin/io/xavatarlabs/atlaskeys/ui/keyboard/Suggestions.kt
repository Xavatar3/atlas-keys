package io.xavatarlabs.atlaskeys.ui

// Android
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

// Androidx
import androidx.recyclerview.widget.RecyclerView

// Android
import android.graphics.Color

// Android Layout
import android.view.ViewGroup.LayoutParams.MATCH_PARENT


class Suggestions @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): RecyclerView(context, attrs) {


  init {

    // Attributes
    setBackgroundColor(
      Color.parseColor(
        "#99222222"
      )
    )

    overScrollMode =
      OVER_SCROLL_NEVER


    setPadding(
      8.dp,
      0,
      8.dp,
      0
    )


    clipToPadding = false


    layoutParams =
      LinearLayout.LayoutParams(
        MATCH_PARENT,
        30.dp
      )

  }


  private val Int.dp: Int
    get() =
      (
        this *
        resources.displayMetrics.density
      ).toInt()


  fun showSuggestions(
    words: List<String>
  ){

    // TODO Adapter
  }
}