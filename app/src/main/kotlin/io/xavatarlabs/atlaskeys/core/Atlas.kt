package io.xavatarlabs.atlaskeys.core

// Android 
import android.content.Context 

// atlaskeys
import io.xavatarlabs.atlaskeys.keyboard.Feedback 


object Atlas {
  lateinit var context: Context
    private set
  lateinit var feedback: Feedback
    private set
  var isPasswordField: Boolean = false

  fun init(context: Context) {
    this.context = context.applicationContext
    feedback = Feedback(this.context)
  }
}