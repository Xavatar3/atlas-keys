package io.xavatarlabs.atlaskeys

import android.os.Bundle
import android.view.Gravity
import android.graphics.Color
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity

class Core: AppCompatActivity() {
	override fun onCreate( savedInstanceState: Bundle? ) {

		super.onCreate(savedInstanceState)

		// Root layout
		val rootLayout = LinearLayout(this).apply {
			gravity = Gravity.CENTER
			orientation = LinearLayout.VERTICAL
			setBackgroundColor(ContextCompat.getColor(this@Core, R.color.background))
			setPadding(32, 32, 32, 32)
		}

		// Title
		val titleView = TextView(this).apply {
			text = "AtlasKeys 🗝️"
			textSize = 24f
			setTextColor(ContextCompat.getColor(this@Core, R.color.on_surface))
			gravity = Gravity.CENTER
		}

		// Placeholder Info
		val infoView = TextView(this).apply {
			text = "Core Activity Placeholder\nKeyboard Alive!"
			textSize = 16f
			setTextColor(ContextCompat.getColor(this@Core, R.color.on_surface_variant))
			gravity = Gravity.CENTER
			setPadding(0, 16, 0, 0)
		}

		// Add views to root
		rootLayout.addView(titleView)
		rootLayout.addView(infoView)
		setContentView(rootLayout)

	}
}