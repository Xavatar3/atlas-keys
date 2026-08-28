package io.xavatarlabs.atlaskeys.app

// Android
import android.content.Context 

// Androidx
import androidx.lifecycle.Lifecycle 
import androidx.lifecycle.LifecycleEventObserver 

// Compose
import androidx.compose.runtime.* 
import androidx.compose.ui.platform.LocalContext 
import androidx.compose.ui.platform.LocalLifecycleOwner 

// Atlaskeys
import io.xavatarlabs.atlaskeys.keyboard.KeyboardStatus 


@Composable
fun rememberStatus(): KeyboardStatus {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var status by remember { mutableStateOf(KeyboardStatus(context)) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                status = KeyboardStatus(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
    return status
}
