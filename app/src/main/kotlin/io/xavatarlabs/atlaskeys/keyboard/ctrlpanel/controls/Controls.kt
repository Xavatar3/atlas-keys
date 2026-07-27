package io.xavatarlabs.atlaskeys.keyboard.ctrlpanel

// Android
import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams.MATCH_PARENT

// Androidx
import androidx.core.content.ContextCompat

// AtlasKeys
import io.xavatarlabs.atlaskeys.R 
import io.xavatarlabs.atlaskeys.keyboard.input.InputEngine  
import io.xavatarlabs.atlaskeys.keyboard.ctrlpanel.controls.CtrlBtn 


class Controls @JvmOverloads constructor(context: Context): LinearLayout(context) {
  private val padding = resources.getDimensionPixelSize(R.dimen.kb_ctrls_pad)
  private var onControlClick: ((String)->Unit)? = null

  init {
    orientation = HORIZONTAL
    gravity = Gravity.CENTER
    setPadding(padding, padding, padding, padding)
    elevation = resources.getDimension(R.dimen.kb_ctrls_el)
    // TODO:
    // Replace icon strings with ControlAction models.
    // Controls should render data instead of knowing button meanings.
    // Move control definitions to configuration.
    // Custom toolbars should be loaded dynamically.
    addControls(listOf("🌐", "📋", "↶", "↷", "←", "→", "⚙"))
    background = ContextCompat.getDrawable(context, R.drawable.kb_ctrls)
  }

  private fun addControls(controls: List<String>){
    controls.forEach { icon ->
      val ctn = LinearLayout(context)
      val btn = CtrlBtn(context)
      btn.setIcon(icon)
      btn.setOnClickListener{ onControlClick?.invoke(icon) }
      ctn.addView(btn, LayoutParams(MATCH_PARENT, MATCH_PARENT))
      addView(ctn, LayoutParams(0, MATCH_PARENT, 1f))
    }
  }

  fun setOnControlClick(listener: (String) -> Unit){ onControlClick = listener }
}