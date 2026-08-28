package io.xavatarlabs.atlaskeys.app

// Kotlinx
import kotlinx.coroutines.delay 

// Compose
import androidx.compose.runtime.* 
import androidx.compose.animation.core.* 

// UI
import androidx.compose.ui.unit.dp 
import androidx.compose.ui.Modifier 
import androidx.compose.ui.Alignment 

// Foundation
import androidx.compose.foundation.layout.* 
import androidx.compose.foundation.background 
import androidx.compose.foundation.shape.RoundedCornerShape 

// Material 3
import androidx.compose.material3.* 
import androidx.compose.material.icons.Icons 
import androidx.compose.material.icons.rounded.Keyboard 

// AtlasKeys
import io.xavatarlabs.atlaskeys.BuildConfig 


/**
 * This is a plain Compose splash, not the platform SplashScreen API
 * (androidx.core.splashscreen). That API needs matching theme attributes
 * wired up in themes.xml *and* a specific installSplashScreen() call
 * before super.onCreate()
 */

private const val SPLASH_DURATION = 1100

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = SPLASH_DURATION,
                easing = LinearEasing
            )
        )
        onFinished()
    }

    val backgroundModifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)

    Box(modifier = backgroundModifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val shape = RoundedCornerShape(28.dp)
            val color = MaterialTheme.colorScheme.surfaceVariant
            val surfaceModifier = Modifier.size(88.dp)
            Surface(shape = shape, color = color, modifier = surfaceModifier) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Icon(
                        imageVector = Icons.Rounded.Keyboard,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(44.dp)
                    )
                }
            }
            Text(
                text = "AtlasKeys",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 20.dp)
            )
            
            val progressModifier = Modifier
                .padding(top = 24.dp)
                .size(width = 120.dp, height = 3.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(2.dp))
            
            Box(modifier = progressModifier) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(fraction = progress.value)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(2.dp))
                )
            }
            
            val date = BuildConfig.VERSION_DATE
            val version = "v${BuildConfig.VERSION_SEMANTIC} \u00b7 "
            val commits = "${BuildConfig.VERSION_COMMIT_COUNT} commits \u00b7 "
            Text(
                text = version + commits + date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.55f),
                modifier = Modifier.padding(top = 28.dp)
            )
        }
    }
}
