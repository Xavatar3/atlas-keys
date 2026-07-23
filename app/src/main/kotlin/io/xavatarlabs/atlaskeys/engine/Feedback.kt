package io.xavatarlabs.atlaskeys.engine

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

  fun key(view: View) {
    // Haptic (framework)
    //view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      vibrator.vibrate(
        VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
      )
    } else {
      @Suppress("DEPRECATION")
      vibrator.vibrate(15)
    }
    
    // Audio
    soundPool.play(
      keyClick, // Sound ID
      0.1f, // Left Volume
      0.1f, // Right Volumehcbic
      0, // Priority
      0, // Loop
      Random.nextFloat() * 0.5f + 1f // Rate
      //(0.95f..1.05f).random()
    )
  }

  fun release() { soundPool.release() }
}
