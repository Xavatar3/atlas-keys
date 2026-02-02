package io.xavatarlabs.atlaskeys

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.TextView
import android.graphics.Color
import android.view.Gravity

class IMEService : InputMethodService() {

    override fun onCreateInputView(): View {
        return TextView(this).apply {
            text = "AtlasKeys 😇"
            textSize = 18f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }
    }
}


/*
package io.xavatarlabs.atlaskeys

import android.view.View
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.widget.TextView

class IMEService : InputMethodService() {

override fun onCreateInputView(): View {
    return layoutInflater.inflate(
        R.layout.keyboard,
        null,
        false
    )
}

	/*override fun onCreateInputView(): View? {
		// Dummy Keyboard UI 
		val dummyView = TextView(this)
		dummyView.text = "AtlasKeys 📍 \n (Keyboard Alive)"
		dummyView.textSize = 18f
		dummyView.setTextColor(Color.WHITE)
		dummyView.setBackgroundColor(Color.DKGRAY)
		dummyView.setPadding(32, 32, 32, 32)
		dummyView.gravity = Gravity.CENTER
	[	return dummyView
	}*

	override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
		super.onStartInputView(info, restarting)
		/* Later
			1.And Proper Layout for the view
		*
	}

	override fun onFinishInput() {
		super.onFinishInput()
		// Later Clean Up here
	}

}*/