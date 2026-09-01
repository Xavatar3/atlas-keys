package io.xavatarlabs.atlaskeys

// Android
import android.view.View 
import android.content.Context 
import android.widget.LinearLayout 
import android.view.ViewGroup.LayoutParams.MATCH_PARENT 

// AtlasKeys
import io.xavatarlabs.atlaskeys.R 
import io.xavatarlabs.atlaskeys.keyboard.Body 
import io.xavatarlabs.atlaskeys.engine.KEngine 
import io.xavatarlabs.atlaskeys.keyboard.State 
import io.xavatarlabs.atlaskeys.keyboard.CtrlPanel 
import io.xavatarlabs.atlaskeys.keyboard.layout.LayoutId 
import io.xavatarlabs.atlaskeys.keyboard.emoji.EmojiProvider 


class Keyboard @JvmOverloads constructor(context: Context): LinearLayout(context){
  private var state: State? = null
  private val body = Body(context)
  private var engine: KEngine? = null
  private val ctrls = CtrlPanel(context)


  init {
    orientation = VERTICAL
    setBackgroundColor(context.getColor(R.color.kb_root)) // move it to themes
    addView(ctrls, LayoutParams(MATCH_PARENT, 0, 1.7f))
    addView(body,  LayoutParams(MATCH_PARENT, 0, 8.3f))
  }
  // TODO:
  // Move keyboard sizing to KeyboardMetrics.
  // Keyboard should only consume calculated dimensions.
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int){
    val heightSpec = MeasureSpec.makeMeasureSpec(
      (resources.displayMetrics.heightPixels * 0.35f).toInt(),
      MeasureSpec.EXACTLY
    )
    super.onMeasure(widthMeasureSpec, heightSpec)
  }

  fun switch(layout: LayoutId){ body.switch(engine?.render(layout)); refresh() }

  fun showKeyboard() {
    //ctrls.visibility = VISIBLE
    body.hideOverlay()
  }

  fun showOverlay(view: View) {
    //ctrls.visibility = GONE
    body.showOverlay(view)
  }

  fun setOnControlClick(listener: (String)->Unit){ ctrls.setOnControlClick(listener) }

  fun attachEngine(engine: KEngine) {
    this.engine = engine
    switch(LayoutId("qwerty"))
  }

  fun attachState(state: State) { this.state = state }

  fun refresh() { /*themeManager?.apply(this) */ }
}


// add scroll view scroll to texteditor