package io.xavatarlabs.atlaskeys

import android.inputmethodservice.InputMethodService
import android.view.inputmethod.EditorInfo
import android.view.View
import android.widget.TextView
//import android.graphics.Color
//import android.view.Gravity
import android.view.LayoutInflater
import android.view.KeyEvent

class IMEService : InputMethodService() {

   // --- Keyboard state ---
   private var shiftOn = false
      
   // --- Inflate keyboard and bind keys ---
   override fun onCreateInputView(): View {
      val view = layoutInflater.inflate(R.layout.keyboard, null, false)
      bindKeys(view)
      return view
   }
   
   override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
		super.onStartInputView(info, restarting)
		
        // Reset shift state on new input field
         shiftOn = false
         updateLetterCase()
	}
	
	override fun onFinishInput() {
		super.onFinishInput()
	}
	
	// --- Bind every TextView key ---
   private fun bindKeys(root: View) {
      val keys = root.getTouchables()
      for (key in keys) {
         if (key is TextView) {
            key.setOnClickListener {
               handleKeyPress(key)
            }
         }
      }
   }
   
   // --- Handle key press actions ---
   private fun handleKeyPress(key: TextView) {
      val inputConnection = currentInputConnection ?: return
      val tag = key.tag as? String
      val label = key.text.toString()
      
      when (tag) {
         "SHIFT" -> {
            shiftOn = !shiftOn
            updateLetterCase()
         }
         "BACKSPACE" -> {
            inputConnection.deleteSurroundingText(1, 0)
         }
         "SPACE" -> {
            inputConnection.commitText(" ", 1)
            }
         "ENTER" -> {
            inputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
         }
         else -> {
            // Regular letter keys
            val output = if (shiftOn) label.uppercase() else label.lowercase()
            inputConnection.commitText(output, 1)

            // shift off after one key
                if (shiftOn) {
                    shiftOn = false
                    updateLetterCase()
                }
            }
        }
    }
	
	// --- Update visual letters according to shift state ---
   private fun updateLetterCase() {
      val root = window.window?.decorView ?: return
      val keys = root.getTouchables()
      for (key in keys) {
         if (key is TextView && key.tag == null) {
            val t = key.text.toString()
            if (t.length == 1 && t[0].isLetter()) {
               key.text = if (shiftOn) t.uppercase() else t.lowercase()
                }
            }
      }
   }
}
// Set transparent theme later
// Improve onStartInputView later. and add privateImeOptions
// Auto pop up key board on switch etc
// Later clean up in onFinishInput...
// later add shift locking
// Have a more scalable bindKeys function
// Improve memory between fields and sessions.
//Easier text selection and cursor movement
//Word delete → delete until space
//Swipe delete → repeated calls
//Selection delete → handled automatically by Android
//Messaging apps → send message
//Search fields → submit search
//Forms → next field
//Code editors → new line with indentation
//Enter  - ic.performEditorAction(EditorInfo.IME_ACTION_DONE)
// Backspace key event setup
// Shift lock
//symbolic and numeric view
//Row backgrounds (#222222) are optional; can style per theme later.




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