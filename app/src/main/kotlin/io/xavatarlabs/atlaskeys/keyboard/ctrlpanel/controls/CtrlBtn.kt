package io.xavatarlabs.atlaskeys.keyboard.ctrlpanel.controls

// Android
import android.view.Gravity
import android.content.Context

// Androidx
import androidx.core.content.ContextCompat
import androidx.appcompat.widget.AppCompatTextView

// Atlaskeys
import io.xavatarlabs.atlaskeys.R

class CtrlBtn @JvmOverloads constructor(context: Context) : AppCompatTextView(context) {

  init {
    minimumWidth = 0
    minimumHeight = 0
    setPadding(0, 0, 0, 0)
    gravity = Gravity.CENTER
    // TODO:
    // Move styling to ThemeRenderer.
    // Button should receive appearance data.
    setTextColor(ContextCompat.getColor(context, R.color.kb_ctrls_icon))
    background = ContextCompat.getDrawable(context, R.drawable.kb_ctrls_btn_bg)
    textSize = resources.getDimension(R.dimen.kb_ctrls_icon_size) / resources.displayMetrics.scaledDensity
  }

  fun setIcon(icon: String) { text = icon }
}