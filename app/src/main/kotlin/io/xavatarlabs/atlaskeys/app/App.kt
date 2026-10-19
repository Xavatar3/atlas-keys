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

    private object Routes {
        const val HOME = "home"
        const val SPLASH = "splash"
        const val ONBOARDING = "onboarding"
    }
}

@Composable
fun Main() {
    var theme by remember(preferences) {
        val name = preferences.getString("theme", null) ?: Theme.ATLAS.name
        mutableStateOf(Theme.entries.firstOrNull { it.name == name } ?: Theme.ATLAS)
    }
    var showOnboarding by rememberSaveable {
        mutableStateOf(!status.isDefault)
    }

    val status = rememberStatus()
    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences("prefs", Context.MODE_PRIVATE) }  
    var theme by remember(preferences) {
        val name = preferences.getString("theme", null) ?: Theme.ATLAS.name
         mutableStateOf(Theme.entries.firstOrNull { it.name == name } ?: Theme.ATLAS) 
    }

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(
                    onFinished = {
                        navController.navigate(
                            if (showOnboarding) Routes.ONBOARDING else Routes.HOME
                        ) {
                            popUpTo(Routes.SPLASH) {
                                inclusive = true
                            }
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
                theme = themeName,
                onThemeChange = {
                    themeName = it
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

    AppTheme(themeName) {
        var display by remember { mutableStateOf(Display.SPLASH) }
        var showOnboarding by rememberSaveable { mutableStateOf(!status.isDefault) }
        
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
                    themeName = it
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





fun Main() {
    
    AtlasTheme(theme) {
        NavHost(
        ) {
            composable(Routes.SPLASH) {
                
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
