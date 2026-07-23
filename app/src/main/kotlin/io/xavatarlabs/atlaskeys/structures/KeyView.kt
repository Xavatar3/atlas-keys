package io.xavatarlabs.atlaskeys.structures

// Android
import android.os.Vibrator
import android.view.Gravity
import android.content.Context
import android.widget.TextView
import android.view.MotionEvent
import android.graphics.Typeface
import android.widget.FrameLayout
import android.os.VibrationEffect
import android.media.AudioManager
import android.view.HapticFeedbackConstants
import android.graphics.drawable.GradientDrawable

// Androidx
import androidx.core.content.ContextCompat

// Atlaskeys
import io.xavatarlabs.atlaskeys.R
import io.xavatarlabs.atlaskeys.engine.State

class KeyView(context: Context) : FrameLayout(context) {
  private val labelView = TextView(context)

  init {
    //this.background = createBackground()
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
    labelView.textSize = resources.getDimension(R.dimen.key_fs)/resources.displayMetrics.scaledDensity
    labelView.setTextColor(ContextCompat.getColor(context, R.color.key_txt))
    labelView.setPadding(
      resources.getDimensionPixelSize(R.dimen.key_pad),
      resources.getDimensionPixelSize(R.dimen.key_pad),
      resources.getDimensionPixelSize(R.dimen.key_pad),
      resources.getDimensionPixelSize(R.dimen.key_pad)
    )
    //labelView.textSize = 21f
    
    setWillNotDraw(false)
    isClickable = true
    isFocusable = true
    
    setOnTouchListener { _, event ->
      when (event.action) {
        MotionEvent.ACTION_DOWN -> {
          alpha = 0.4f
        }

        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> { alpha = 1f }
      }
      false
    }
  }
  
    //expected by lint
    override fun performClick(): Boolean {
      super.performClick() 
      return true
    }

  
  private fun updateStyle(key: Key){ background = createBackground(key) }
  
  private fun createBackground(key: Key): GradientDrawable {
    val isSpecial = when (key.type) {
      Types.ENTER, Types.ABC,
      Types.SHIFT, Types.DELETE,
      Types.SPACE, Types.SYMBOLS,
      Types.ACTION -> true
      else -> false
    }
    
    return GradientDrawable().apply {
      shape = GradientDrawable.RECTANGLE
      cornerRadius = resources.getDimension(if(isSpecial) R.dimen.key_cr_sp else R.dimen.key_cr)
      setColor(ContextCompat.getColor(context, R.color.key_bg))
      //setColor(ContextCompat.getColor(context, if(isSpecial) R.color.key_bg_sp else R.color.key_bg))
      setStroke(
        resources.getDimensionPixelSize(R.dimen.key_bw),
        ContextCompat.getColor(context, R.color.key_bd)
        //ContextCompat.getColor(context, if(isSpecial) R.color.key_bd_sp else R.color.key_bd)
      )
    }
  }
  
  fun bind(key: Key, state: State) {
    updateStyle(key)
    
    val isSpecial = when (key.type) {
      Types.ENTER, Types.ABC,
      Types.SHIFT, Types.DELETE,
      Types.SPACE, Types.SYMBOLS,
      Types.ACTION -> true
      else -> false
    }
    
    labelView.textSize = resources.getDimension(
      if (isSpecial)
         R.dimen.key_fs_sp
      else
        R.dimen.key_fs
    ) / resources.displayMetrics.scaledDensity
    
    if(isSpecial) labelView.typeface = Typeface.DEFAULT_BOLD
    
    val text = when (key.type) {
      Types.CHAR ->
        if (state.shift) key.label.uppercase()
        else key.label.lowercase()
      else -> key.label
    }

    labelView.text = text
    key.dirty = false
  }
}
