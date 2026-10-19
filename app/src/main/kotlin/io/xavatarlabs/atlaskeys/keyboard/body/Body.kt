package io.xavatarlabs.atlaskeys.keyboard

// Android
import android.view.View 
import android.content.Context 
import android.widget.FrameLayout 

// AtlasKeys
import io.xavatarlabs.atlaskeys.keyboard.body.Layout 
import io.xavatarlabs.atlaskeys.keyboard.body.Overlay 

class Body(context: Context): FrameLayout(context){
  private val layout = Layout(context)
  private val overlay = Overlay(context)

  init {
    val pad = 8
    val match = LayoutParams.MATCH_PARENT
    val params = LayoutParams(match, match)
    
    setPadding(pad, pad, pad, pad)
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
  }
}