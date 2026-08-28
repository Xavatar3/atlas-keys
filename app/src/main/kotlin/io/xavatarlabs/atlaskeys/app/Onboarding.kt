package io.xavatarlabs.atlaskeys.app

// Android
import android.content.Intent 
import android.view.inputmethod.InputMethodManager 
import android.provider.Settings as AndroidSettings

// Kotlinx
import kotlinx.coroutines.launch 

// Compose
import androidx.compose.runtime.* 
import androidx.compose.material3.* 
import androidx.compose.material.icons.Icons 
import androidx.compose.material.icons.rounded.* 
import androidx.compose.animation.core.animateFloatAsState 

// Foundation
import androidx.compose.foundation.pager.* 
import androidx.compose.foundation.layout.* 
import androidx.compose.foundation.background 
import androidx.compose.foundation.shape.RoundedCornerShape 
import androidx.compose.foundation.ExperimentalFoundationApi 

// UI
import androidx.compose.ui.unit.dp 
import androidx.compose.ui.Modifier 
import androidx.compose.ui.Alignment 
import androidx.compose.ui.text.style.TextAlign 
import androidx.compose.ui.text.font.FontWeight 
import androidx.compose.ui.platform.LocalContext 
import androidx.compose.ui.graphics.vector.ImageVector 


private data class OnboardingPage(val icon: ImageVector, val title: String, val body: String)

private val pages = listOf(
    OnboardingPage(
        icon = Icons.Rounded.Keyboard,
        title = "Type at full speed",
        body = "AtlasKeys is a fast, focused keyboard - no bloat, " +
            "no lag, just accurate typing wherever you type."
    ),
    OnboardingPage(
        icon = Icons.Rounded.Language,
        title = "Switch layouts instantly",
        body = "Move between your full keyboard and symbols with one " +
            "tap. Multiple language layouts, including Luganda, are " +
            "built in."
    ),
    OnboardingPage(
        icon = Icons.Rounded.Lock,
        title = "What you type stays yours",
        body = "AtlasKeys never reads, stores, or sends what you type. " +
            "No internet permission, no analytics - just a keyboard."
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingFlow(onFinished: () -> Unit) {
    // pages.size marketing pages + one Enable page at the end.
    val pagerState = rememberPagerState(pageCount = { pages.size + 1 })
    val scope = rememberCoroutineScope()
    val lastPage = pages.size
    val onLastPage = pagerState.currentPage >= lastPage

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            if (page < lastPage) {
                OnboardingPageContent(pages[page])
            } else {
                EnablePage(onFinished = onFinished)
            }
        }

        if (!onLastPage) {
            TextButton(
                onClick = { scope.launch { pagerState.animateScrollToPage(lastPage) } },
                modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
            ) {
                Text("Skip", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
            }

            Row(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 140.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(pages.size + 1) { index ->
                    PageIndicatorDot(active = index == pagerState.currentPage)
                }
            }

            Button(
                onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 48.dp)
                    .fillMaxWidth(0.7f)
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp), // keycap radius, not a pill - matches Splash.kt's badge
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Next", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun PageIndicatorDot(active: Boolean) {
    val width by animateFloatAsState(if (active) 22f else 8f, label = "dot-width")
    Box(
        modifier = Modifier
            .height(8.dp)
            .width(width.dp)
            .background(
                if (active) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
                RoundedCornerShape(4.dp)
            )
    )
}

@Composable
private fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconBadge(page.icon)

        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 28.dp)
        )

        Text(
            text = page.body,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Composable
private fun EnablePage(onFinished: () -> Unit) {
    val context = LocalContext.current
    val status = rememberStatus()

    val (icon, title, body) = when {
        !status.isEnabled -> Triple(
            Icons.Rounded.Keyboard,
            "Turn on AtlasKeys",
            "Enable AtlasKeys in your keyboard settings to start typing with it."
        )
        !status.isDefault -> Triple(
            Icons.Rounded.SwapHoriz,
            "Switch to AtlasKeys",
            "AtlasKeys is enabled. Pick it as your active keyboard to finish."
        )
        else -> Triple(
            Icons.Rounded.CheckCircle,
            "You're all set",
            "AtlasKeys is your active keyboard."
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconBadge(icon)

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 28.dp)
        )

        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            onClick = {
                when {
                    !status.isEnabled ->
                        context.startActivity(Intent(AndroidSettings.ACTION_INPUT_METHOD_SETTINGS))
                    !status.isDefault ->
                        context.getSystemService(InputMethodManager::class.java).showInputMethodPicker()
                    else -> onFinished()
                }
            },
            modifier = Modifier.padding(top = 32.dp).fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = when {
                    !status.isEnabled -> "Enable Keyboard"
                    !status.isDefault -> "Switch to AtlasKeys"
                    else -> "Continue"
                },
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun IconBadge(icon: ImageVector) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.size(96.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}
