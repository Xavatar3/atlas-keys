package io.xavatarlabs.atlaskeys.app

// Android
import android.os.Bundle 
import android.content.Context 

// Androidx
import androidx.activity.ComponentActivity 
import androidx.activity.compose.setContent 

// Navigation
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Compose
import androidx.compose.runtime.* 
import androidx.compose.ui.platform.LocalContext 
import androidx.compose.runtime.saveable.rememberSaveable 

// AtlasKeys
import io.xavatarlabs.atlaskeys.app.theme.Theme 
import io.xavatarlabs.atlaskeys.app.theme.AppTheme 


class App : ComponentActivity() {
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContent { Main() }
    }
}

private object Routes {
    const val HOME = "home"
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
}

@Composable
fun Main() {
    val status = rememberStatus()
    val context = LocalContext.current
    val navController = rememberNavController()
    var showOnboarding by rememberSaveable { mutableStateOf(!status.isDefault) }
    val preferences = remember { context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE) }  
    var theme by rememberSaveable{
        mutableStateOf(
            preferences.getString("theme", null)
                .let { name -> Theme.entries.find { it.name == name } ?: Theme.ATLAS }
        )
    }

    AppTheme(theme) {
        // Later, use @Serializable as type-safe navigation arguments instead of this hacky approach
        NavHost(navController = navController, startDestination = Routes.SPLASH) {
            composable(Routes.SPLASH) {
                SplashScreen(
                    onFinished = {
                        navController.navigate(
                            if (showOnboarding) Routes.ONBOARDING else Routes.HOME
                        ) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.ONBOARDING) {
                OnboardingFlow(
                    onFinished = {
                        showOnboarding = false
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.ONBOARDING) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.HOME) {
                HomeScreen(
                    theme = theme,
                    onThemeChange = {
                        theme = it
                        preferences.edit().putString("theme", it.name).apply()
                    },
                    onReplayIntro = {
                        showOnboarding = true
                        navController.navigate(Routes.ONBOARDING) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
