package io.xavatarlabs.atlaskeys.app

// Android
import android.os.Bundle 
import android.content.Context 

// Androidx
import androidx.activity.ComponentActivity 
import androidx.activity.compose.setContent 

// Compose
import androidx.compose.runtime.* 
import androidx.compose.ui.platform.LocalContext 
import androidx.compose.runtime.saveable.rememberSaveable 

// AtlasKeys
import io.xavatarlabs.atlaskeys.app.theme.Theme 
import io.xavatarlabs.atlaskeys.app.theme.AtlasTheme 


private enum class Display { SPLASH, ONBOARDING, HOME }

class App : ComponentActivity() {
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContent { Main() }
    }
}

@Composable
fun Main() {
    val status = rememberStatus()
    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences("prefs", Context.MODE_PRIVATE) }  
    var theme by remember(preferences) {
        val name = preferences.getString("theme", null) ?: Theme.ATLAS.name
         mutableStateOf(Theme.entries.firstOrNull { it.name == name } ?: Theme.ATLAS) 
    }
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
                onThemeChange = { 
                    theme = it
                    preferences.edit().putString("theme", it.name).apply()
                },
                onReplayIntro = {
                    showOnboarding = true
                    display = Display.ONBOARDING
                }
            )
        }
    }
}
