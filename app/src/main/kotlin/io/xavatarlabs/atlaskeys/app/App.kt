package io.xavatarlabs.atlaskeys.app

// Android
import android.os.Bundle 

// Androidx
import androidx.activity.ComponentActivity 
import androidx.activity.compose.setContent 

// Compose
import androidx.compose.runtime.* 
import androidx.compose.ui.Modifier 
import androidx.compose.foundation.layout.* 
import androidx.compose.runtime.saveable.rememberSaveable 

// AtlasKeys
import io.xavatarlabs.atlaskeys.app.theme.Theme 
import io.xavatarlabs.atlaskeys.app.theme.AtlasTheme 


class App : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Main() }
    }
}

private enum class Display { SPLASH, ONBOARDING, HOME }

@Composable
fun Main() {
    val status = rememberStatus()
    var theme by remember { mutableStateOf(Theme.ATLAS) }
    var display by remember { mutableStateOf(Display.SPLASH) }
    var showOnboarding by rememberSaveable { mutableStateOf(!status.isDefault) }

    AtlasTheme(theme) {
        when (display) {
            Display.SPLASH -> SplashScreen(
                onFinished = {
                    display = if(showOnboarding) Display.ONBOARDING else Display.HOME
                }
            )
            Display.ONBOARDING -> OnboardingFlow(
                onFinished = {
                    showOnboarding = false
                    display = Display.HOME
                }
            )
            Display.HOME -> HomeScreen(
                theme = theme,
                onThemeChange = { theme = it },
                onReplayIntro = {
                    showOnboarding = true
                    display = Display.ONBOARDING
                }
            )
        }
    }
}
