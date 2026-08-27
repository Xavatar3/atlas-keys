package io.xavatarlabs.atlaskeys.keyboard.body

// Android
import android.view.View 
import android.content.Context 
import android.widget.FrameLayout 

// AtlasKeys
import io.xavatarlabs.atlaskeys.keyboard.layout.Layouts 


class Layout @JvmOverloads constructor(context: Context): FrameLayout(context){
  fun switch(view: View?){
    removeAllViews()
    val match = LayoutParams.MATCH_PARENT
    addView(view, LayoutParams(match, match))
  }

  fun show() {
    visibility = VISIBLE
  }

  fun hide() {
    visibility = GONE
  }
}
// TODO:
// Renderer updates changes instead of purging layout