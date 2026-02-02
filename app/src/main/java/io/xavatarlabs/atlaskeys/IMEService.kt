package io.xavatarlabs.atlaskeys

import android.inputmethodservice.InputMethodService
import android.view.inputmethod.EditorInfo
import android.view.View
import android.widget.TextView
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater

class IMEService : InputMethodService() {

   // Set transparent theme later
   override fun onCreateInputView(): View {
   
      return layoutInflater.inflate(
         R.layout.keyboard,
        null,
        false
      )
      
      /*
      return TextView(this).apply {
         text = "AtlasKeys  📍 \n (Prototype)"
         textSize = 18f
         setTextColor(Color.WHITE)
         setBackgroundColor(Color.DKGRAY)
         gravity = Gravity.CENTER
         setPadding(32, 32, 32, 32)
      }
      */
      
   }
   
   // Improve this section later. and add privateImeOptions
   override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
		super.onStartInputView(info, restarting)
	}
	
   // Later clean up here...
	override fun onFinishInput() {
		super.onFinishInput()
	}
	
}