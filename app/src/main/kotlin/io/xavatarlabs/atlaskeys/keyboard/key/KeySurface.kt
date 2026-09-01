package io.xavatarlabs.atlaskeys.keyboard.key

// Android
import android.view.Gravity 
import android.content.Context 
import android.widget.TextView 
import android.widget.ImageView 
import android.graphics.Typeface 
import android.widget.FrameLayout 
import android.graphics.drawable.GradientDrawable 

// Androidx
import androidx.core.content.ContextCompat 

// AtlasKeys
import io.xavatarlabs.atlaskeys.R  
import io.xavatarlabs.atlaskeys.engine.kengine.runtime.RKey 
import io.xavatarlabs.atlaskeys.keyboard.layout.models.KType 


class KeySurface @JvmOverloads constructor(context: Context) : FrameLayout(context) {
  private val hint = TextView(context) 
  private val icon = ImageView(context)
  private val label = TextView(context)
  private val bg = GradientDrawable()

  private fun customise(key: RKey){
    label.typeface = Typeface.DEFAULT_BOLD
    label.textSize = resources.getDimension(if(key.ktype != KType.CHAR) R.dimen.key_fs_sp else R.dimen.key_fs)
    bg.apply {
      cornerRadius = resources.getDimension(if(key.ktype != KType.CHAR) R.dimen.key_cr_sp else R.dimen.key_cr)
    }
  }


  init {
    val wrap = LayoutParams.WRAP_CONTENT
    val match = LayoutParams.MATCH_PARENT
    
    background = bg
    label.gravity = Gravity.CENTER
    bg.shape = GradientDrawable.RECTANGLE
    elevation = resources.getDimension(R.dimen.key_el)
    bg.setColor(ContextCompat.getColor(context, R.color.key_bg))
    //bg.setTextColor(ContextCompat.getColor(context, R.color.key_txt))
    bg.setStroke(resources.getDimensionPixelSize(R.dimen.key_bw), ContextCompat.getColor(context, R.color.key_bd))
    addView(label, LayoutParams(match, match))
    addView(icon, LayoutParams(wrap, wrap, Gravity.CENTER))
    addView(hint, LayoutParams(wrap, wrap, Gravity.TOP or Gravity.END))
  }

  fun bind(key: RKey) {
    // TODO
    // Load icon
    customise(key)
    label.text = key.label.ifEmpty { key.id }
    hint.text = key.hint.firstOrNull() ?: ""
    if (key.icon.isNullOrEmpty()) { icon.visibility = GONE } else { icon.visibility = VISIBLE }
  }

  fun setPressedKey(pressed: Boolean) {
    if (pressed) {
      // TODO
      // Press background
    } else {
      // TODO
      // Normal background
    }
  }
}