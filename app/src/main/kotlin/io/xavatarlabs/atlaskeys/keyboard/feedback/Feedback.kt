package io.xavatarlabs.atlaskeys.keyboard

// Android
import android.os.Build 
import android.view.View 
import android.os.Vibrator 
import kotlin.random.Random 
import android.content.Context 
import android.media.SoundPool 
import android.os.VibrationEffect 
import android.media.AudioAttributes 
import android.view.HapticFeedbackConstants 

// Atlaskeys
import io.xavatarlabs.atlaskeys.R 

class Feedback(context: Context) {
  private val soundPool = SoundPool.Builder()
    .setMaxStreams(4)
    .setAudioAttributes(
      AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
        .build()
    ).build()

  private val vibrator = context.getSystemService(Vibrator::class.java)
  private val keyClick = soundPool.load(context, R.raw.key_mech, 1)
  private var loaded = false

  init{
    soundPool.setOnLoadCompleteListener { _,_,_ ->
      loaded = true
    }
  }

  fun key() {
    // Haptic (framework)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      vibrator?.vibrate(
        VibrationEffect.createOneShot(29, VibrationEffect.DEFAULT_AMPLITUDE)
      )
    } else {
      @Suppress("DEPRECATION")
      vibrator?.vibrate(29)
    }
    
    // Audio
    if(loaded){
      soundPool.play(
        keyClick, // Sound ID
        0.01f, // Left Volume
        0.01f, // Right Volume
        0, // Priority
        0, // Loop
        Random.nextFloat() * 0.1f + 0.4f // Rate
        //(0.95f..1.05f).random()
      )
    }
  }

  fun release() { soundPool.release() }
}
