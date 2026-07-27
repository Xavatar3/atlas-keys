package io.xavatarlabs.atlaskeys.keyboard.body

// Android
import android.view.View 
import android.content.Context 
import android.widget.FrameLayout 
import android.widget.FrameLayout.LayoutParams 
import android.view.ViewGroup.LayoutParams.MATCH_PARENT 

class Overlay(context: Context): FrameLayout(context){
  fun hide() {
    visibility = GONE
    removeAllViews()
  }

  fun display(view: View) {
    removeAllViews()
    addView(view, LayoutParams(MATCH_PARENT, MATCH_PARENT))
    visibility = VISIBLE
  }
}