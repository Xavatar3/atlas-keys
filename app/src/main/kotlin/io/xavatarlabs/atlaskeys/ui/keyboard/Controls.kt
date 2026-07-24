package io.xavatarlabs.atlaskeys.ui

// Android
import android.view.Gravity
import android.content.Context
import android.widget.TextView
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams.MATCH_PARENT

// Androidx
import androidx.core.content.ContextCompat

// AtlasKeys
import io.xavatarlabs.atlaskeys.R


class Controls @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): LinearLayout(context, attrs) {


  private var onControlClick: ((String) -> Unit)? = null


  init {

    // Attributes
    orientation = HORIZONTAL
    gravity = Gravity.CENTER_VERTICAL

    background =
      ContextCompat.getDrawable(
        context,
        R.drawable.kb_ctrls
      )

    elevation =
      resources.getDimension(
        R.dimen.kb_ctrls_el
      )


    setPadding(
      resources.getDimensionPixelSize(
        R.dimen.kb_ctrls_pad
      ),
      resources.getDimensionPixelSize(
        R.dimen.kb_ctrls_pad
      ),
      resources.getDimensionPixelSize(
        R.dimen.kb_ctrls_pad
      ),
      resources.getDimensionPixelSize(
        R.dimen.kb_ctrls_pad
      )
    )


    setControls(
      listOf(
        "🌐",
        "📋",
        "↶",
        "↷",
        "←",
        "→",
        "⚙"
      )
    )

  }

fun setOnSettingsClick(
    listener: () -> Unit
) {

    setOnControlClick { icon ->

        if(icon == "⚙") {
            listener()
        }

    }

}

  fun setOnControlClick(
    listener: (String) -> Unit
  ) {

    onControlClick = listener

  }


  fun setControls(
    controls: List<String>
  ) {

    removeAllViews()


    controls.forEachIndexed { index, icon ->


      val button =
        TextView(context).apply {


          text = icon


          background =
            ContextCompat.getDrawable(
              context,
              R.drawable.kb_ctrls_btn_bg
            )


          setTextColor(
            ContextCompat.getColor(
              context,
              R.color.kb_ctrls_icon
            )
          )


          textSize =
            resources.getDimension(
              R.dimen.kb_ctrls_icon_size
            ) /
            resources.displayMetrics.scaledDensity


          setPadding(
            0,
            0,
            0,
            0
          )


          gravity = Gravity.CENTER


          minimumWidth = 0
          minimumHeight = 0


          setOnClickListener {

            onControlClick?.invoke(icon)

          }

        }


      addView(
        button,
        LinearLayout.LayoutParams(
          0,
          MATCH_PARENT,
          1f
        ).apply {

          if(index != controls.lastIndex) {

            marginEnd =
              dpToPx(6)

          }

        }
      )
    }
  }


  private fun dpToPx(dp: Int): Int {

    return (
      dp * resources.displayMetrics.density
    ).toInt()

  }
}