package io.xavatarlabs.atlaskeys.keyboard.key

// Android
import android.content.Context 
import android.view.MotionEvent 
import android.widget.FrameLayout 

// AtlasKeys
import io.xavatarlabs.atlaskeys.R 
import io.xavatarlabs.atlaskeys.core.Atlas 
import io.xavatarlabs.atlaskeys.engine.runtime.RKey 
import io.xavatarlabs.atlaskeys.keyboard.layout.models.KType 


class KeyView @JvmOverloads constructor(context: Context) : FrameLayout(context) {
  private lateinit var key: RKey
  private val surface = KeySurface(context)
  private var onKeyPressed: ((RKey) -> Unit)? = null
  lateinit var ktype: KType
  lateinit var label: String

  init {
    val pad = resources.getDimensionPixelSize(R.dimen.key_pad)
    val match = LayoutParams.MATCH_PARENT
    
    isClickable = true
    isFocusable = true
    setPadding(pad, pad, pad, pad)
    addView(surface, LayoutParams(match, match))
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    when(event.action) {
      MotionEvent.ACTION_DOWN -> {
        Atlas.feedback.key()
        press()
      }
      
      MotionEvent.ACTION_CANCEL -> {
        
        release()
      }
      
      MotionEvent.ACTION_UP -> {
        key.let { onKeyPressed?.invoke(it) }
        stopLongPressTimer()
        stopRepeatTimer()
        performClick()
        alpha = 1f
        release()
      }
    }
    return true
  }

  override fun performClick(): Boolean {
    super.performClick()
    return true
  }

  private fun startLongPressTimer() { /* Kater */ }

  private fun stopLongPressTimer() { /* Sater */ }

  private fun startRepeatTimer() { /* mater */ }

  private fun stopRepeatTimer() { /* jater */ }

  private fun release() {
    surface.setPressed(false)
    stopLongPressTimer()
    stopRepeatTimer()
  }

  private fun press() {
    surface.setPressed(true)
    startLongPressTimer()
    startRepeatTimer()
  }

  fun refresh() {
    if (!::key.isInitialized) return
    surface.bind(key)
    visibility = if (key.visible) VISIBLE else GONE
    isEnabled = key.enabled
  }

  fun updateLabel(label: String) { // maybe override setLabel later
    if (!::key.isInitialized) return
    key.label = label
    surface.bind(key)
  }

  fun showPopup() { /* TODO */ }
  fun hidePopup() { /* KODO */ }
  fun applyTheme() { /* DODO */ }
  fun showPreview() { /* SODO */ }
  fun hidePreview() { /* RODO */ }
  fun animatePress() { /* YODO */ }
  fun animateRelease() { /* LODO */ }
  fun setActivatedKey(active: Boolean) { /* TODO */ }

  fun bind(key: RKey){
    this.key = key
    this.ktype = key.ktype
    this.label = key.label
    refresh()
  }

  fun setOnKeyClick(listener: (RKey) -> Unit) { this.onKeyPressed = listener }
}
// TODO:
// Accessibility.
// Content descriptions.
// TalkBack support.

// TODO:
// Sound effects.
// Delegate to AudioManager.

// TODO:
// Haptic feedback.
// Delegate to FeedbackManager.

// TODO:
// Swipe gestures.
// Example:
// swipe up
// swipe left
// swipe right

// TODO:
// Multi-touch.
// Support multiple simultaneous keys.

// RESEARCH: 
// experiment with a key having commitText Injected
// and keys with badges counting clicks
// experiment with request focus to simulate auto typing
