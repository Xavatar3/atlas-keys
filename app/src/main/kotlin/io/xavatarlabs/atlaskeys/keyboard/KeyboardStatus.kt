package io.xavatarlabs.atlaskeys.keyboard

// Android
import android.content.Context 
import android.provider.Settings 
import android.content.ComponentName 
import android.view.inputmethod.InputMethodManager 

// AtlasKeys
import io.xavatarlabs.atlaskeys.services.IMEService 

class KeyboardStatus(private val context: Context) {
    private val imm = context.getSystemService(InputMethodManager::class.java)
    val isEnabled = imm.enabledInputMethodList.any { it.packageName == context.packageName }
    
    val isDefault: Boolean get() {
        val defaultId = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.DEFAULT_INPUT_METHOD
        ) ?: return false
        val defaultIme = ComponentName.unflattenFromString(defaultId)
        val atlasKeysIme = ComponentName(context, IMEService::class.java)
        return defaultIme == atlasKeysIme
    }
}
