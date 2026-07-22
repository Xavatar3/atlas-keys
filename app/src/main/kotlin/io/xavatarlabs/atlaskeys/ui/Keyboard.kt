package io.xavatarlabs.atlaskeys.ui

import android.content.Context 
import android.util.AttributeSet 
import android.widget.FrameLayout 
import android.widget.LinearLayout 
import android.view.ViewGroup.LayoutParams.MATCH_PARENT 
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT 

class Keyboard @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null
): LinearLayout(context, attrs){
  
  lateinit var controls: Controls
  lateinit var suggestions: Suggestions
  lateinit var layout: LayoutX
  lateinit var overlay: Overlay
  private lateinit var stage: FrameLayout
  
  init {
    // Attributes
    orientation = VERTICAL
    setPadding(1, 1, 1, 1)
    //layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    
    // Views
    controls = Controls(context)
    suggestions = Suggestions(context)
    layout = LayoutX(context)
    overlay = Overlay(context)
    
    stage = FrameLayout(context)
    stage.addView(
      layout,
      FrameLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT)
    )
    stage.addView(
      overlay,
      FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
    )

    // Add Views
    addView(controls, LayoutParams(MATCH_PARENT, WRAP_CONTENT))
    addView(suggestions, LayoutParams(MATCH_PARENT, WRAP_CONTENT))
    addView(stage, LayoutParams(MATCH_PARENT, 0, 1f))
  }

  fun setHeight(ratio: Float) {
    val screenHeight = resources.displayMetrics.heightPixels
    layoutParams = layoutParams.apply {
      height = (screenHeight * ratio).toInt()
    }
    requestLayout()
  }
  
  fun setLayout(source: List<List<String>>){
    //layout.setLayout(source)
  }
  
  fun setWidth(ratio: Float) {
    val screenWidth = resources.displayMetrics.widthPixels
    layoutParams = layoutParams.apply {
      width = (screenWidth * ratio).toInt()
    }
    requestLayout()
  }
}

//later use percentages, px dp, etc