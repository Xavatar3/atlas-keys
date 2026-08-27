package io.xavatarlabs.atlaskeys.keyboard

// Android
import android.view.View 
import android.content.Context 
import android.widget.FrameLayout 
import android.widget.LinearLayout 

// AtlasKeys
import io.xavatarlabs.atlaskeys.keyboard.body.Layout 
import io.xavatarlabs.atlaskeys.keyboard.body.Overlay 

class Body @JvmOverloads constructor(context: Context): FrameLayout(context){
  private val layout = Layout(context)
  private val overlay = Overlay(context)

  init {
    val xl = LayoutParams.MATCH_PARENT
    val params = LayoutParams(xl, xl)
    val pad = 4
    setPadding(pad, pad, pad, pad)
    overlay.visibility = GONE // move to overlay
    addView(layout, params)
    addView(overlay, params)
  }

  fun switch(view: View?){ layout.switch(view) }

  fun hideOverlay() {
    overlay.hide()
    layout.show()
  
  }

  fun showOverlay(view: View) {
    layout.hide()
    overlay.display(view)
    overlay.bringToFront()
  }
}