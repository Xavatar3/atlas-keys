package io.xavatarlabs.atlaskeys

import android.os.Bundle
import android.view.Gravity
import android.graphics.Color
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Placeholder Settings(No UI yet)
		val dummyView = TextView(this).apply {
			text = "AtlasKeys Settings ⚙️\n(Placeholder)"
			textSize = 20f
			setTextColor(Color.WHITE)
			setBackgroundColor(Color.DKGRAY)
			setPadding(32, 32, 32, 32)
			gravity = Gravity.CENTER
		}

			setContentView(dummyView)

	}
}