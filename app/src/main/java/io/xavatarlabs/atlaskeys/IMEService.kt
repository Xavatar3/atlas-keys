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
   // Auto pop up key board on switch etc
   override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
		super.onStartInputView(info, restarting)
	}
	
   // Later clean up here...
	override fun onFinishInput() {
		super.onFinishInput()
	}
	
}



/*
class IMEService : InputMethodService() {

    override fun onCreateInputView(): View {
        // Inflate your XML keyboard
        val keyboardView = layoutInflater.inflate(R.layout.keyboard, null, false)

        // Letters Row 1: Q-P
        val row1Keys = arrayOf("Q","W","E","R","T","Y","U","I","O","P")
        for (key in row1Keys) {
            val tv = keyboardView.findViewWithTag<TextView>(key)
            tv?.setOnClickListener { currentInputConnection.commitText(key, 1) }
        }

        // Letters Row 2: A-L
        val row2Keys = arrayOf("A","S","D","F","G","H","J","K","L")
        for (key in row2Keys) {
            val tv = keyboardView.findViewWithTag<TextView>(key)
            tv?.setOnClickListener { currentInputConnection.commitText(key, 1) }
        }

        // Letters Row 3 + Shift/Backspace
        val row3Keys = arrayOf("⇧","Z","X","C","V","B","N","M","⌫")
        for (key in row3Keys) {
            val tv = keyboardView.findViewWithTag<TextView>(key)
            tv?.setOnClickListener {
                when(key) {
                    "⇧" -> toggleShift()
                    "⌫" -> currentInputConnection.deleteSurroundingText(1,0)
                    else -> {
                        val output = if (isShifted) key.uppercase() else key.lowercase()
                        currentInputConnection.commitText(output,1)
                    }
                }
            }
        }

        // Bottom Row: Sym / Space / Enter
        val bottomKeys = arrayOf("sym"," ","⏎")
        for (key in bottomKeys) {
            val tv = keyboardView.findViewWithTag<TextView>(key)
            tv?.setOnClickListener {
                when(key) {
                    "⏎" -> currentInputConnection.sendKeyEvent(
                        android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_ENTER)
                    )
                    else -> currentInputConnection.commitText(key,1)
                }
            }
        }

        return keyboardView
    }

    // Shift state
    private var isShifted = false
    private fun toggleShift() {
        isShifted = !isShifted
    }
}
*/