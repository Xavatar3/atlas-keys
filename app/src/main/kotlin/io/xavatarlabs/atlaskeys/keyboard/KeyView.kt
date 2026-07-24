package io.xavatarlabs.atlaskeys.keyboard

// Android
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.TextView

// Androidx
import androidx.core.content.ContextCompat

// Atlaskeys
import io.xavatarlabs.atlaskeys.R
import io.xavatarlabs.atlaskeys.engine.KeyboardState


class KeyView(context: Context) : FrameLayout(context) {

  private val labelView = TextView(context)

  private val normalSize =
    resources.getDimension(R.dimen.key_fs) /
    resources.displayMetrics.scaledDensity

  private val specialSize =
    resources.getDimension(R.dimen.key_fs_sp) /
    resources.displayMetrics.scaledDensity


  init {

    elevation = resources.getDimension(R.dimen.key_el)

    addView(labelView)


    labelView.isClickable = false
    labelView.isFocusable = false
    labelView.isFocusableInTouchMode = false


    labelView.layoutParams = LayoutParams(
      LayoutParams.MATCH_PARENT,
      LayoutParams.MATCH_PARENT
    )


    labelView.gravity = Gravity.CENTER


    labelView.setTextColor(
      ContextCompat.getColor(
        context,
        R.color.key_txt
      )
    )


    labelView.setPadding(
      resources.getDimensionPixelSize(R.dimen.key_pad),
      resources.getDimensionPixelSize(R.dimen.key_pad),
      resources.getDimensionPixelSize(R.dimen.key_pad),
      resources.getDimensionPixelSize(R.dimen.key_pad)
    )


    isClickable = true
    isFocusable = true


    setOnTouchListener { _, event ->

      when(event.action){

        MotionEvent.ACTION_DOWN -> {
          alpha = 0.4f
        }


        MotionEvent.ACTION_UP,
        MotionEvent.ACTION_CANCEL -> {
          // performClick() // suspected double click cause
          alpha = 1f
        }

      }

      false
    }
  }


  override fun performClick(): Boolean {
    super.performClick()
    return true
  }


  fun bind(
    key: Key,
    state: KeyboardState
  ) {


    labelView.text =
      when(key.type){

        KeyType.CHAR ->
          if(state.shift)
            key.label.uppercase()
          else
            key.label.lowercase()


        else ->
          key.label
      }


    val isSpecial = key.type != KeyType.CHAR


    labelView.textSize =
      if(isSpecial)
        specialSize
      else
        normalSize


    labelView.typeface =
      if(isSpecial)
        Typeface.DEFAULT_BOLD
      else
        Typeface.DEFAULT


    background =
      createBackground(key)
  }


  private fun createBackground(key: Key): GradientDrawable {

    return GradientDrawable().apply {

      shape = GradientDrawable.RECTANGLE


      cornerRadius =
        resources.getDimension(
          if(key.type != KeyType.CHAR)
            R.dimen.key_cr_sp
          else
            R.dimen.key_cr
        )


      setColor(
        ContextCompat.getColor(
          context,
          R.color.key_bg
        )
      )


      setStroke(
        resources.getDimensionPixelSize(
          R.dimen.key_bw
        ),
        ContextCompat.getColor(
          context,
          R.color.key_bd
        )
      )
    }
  }
}