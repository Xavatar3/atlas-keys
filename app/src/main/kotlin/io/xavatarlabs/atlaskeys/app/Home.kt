package io.xavatarlabs.atlaskeys.app

// Compose
import androidx.compose.runtime.* 
import androidx.compose.material3.* 
import androidx.compose.material.icons.Icons 
import androidx.compose.material.icons.filled.* 

// Foundation
import androidx.compose.foundation.layout.* 
import androidx.compose.foundation.background 
import androidx.compose.foundation.shape.CircleShape 
import androidx.compose.foundation.shape.RoundedCornerShape 

// UI
import androidx.compose.ui.unit.dp 
import androidx.compose.ui.Modifier 
import androidx.compose.ui.Alignment 
import androidx.compose.ui.graphics.Color 

// AtlasKeys
import io.xavatarlabs.atlaskeys.app.theme.Theme 
import io.xavatarlabs.atlaskeys.keyboard.KeyboardStatus 
import io.xavatarlabs.atlaskeys.settings.Settings as SettingsScreen


@Composable
fun HomeScreen(theme: Theme, onThemeChange: (Theme) -> Unit, onReplayIntro: () -> Unit) {
    val status = rememberStatus()
    var showSettings by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        HomeStatusCard(status = status, modifier = Modifier.align(Alignment.Center))
        DotMenuButton(
            theme = theme,
            onThemeChange = onThemeChange,
            onSettings = { showSettings = true },
            onReplayIntro = onReplayIntro,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        if (showSettings) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.55f))) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) { SettingsScreen(onClose = { showSettings = false }) }
            }
        }
    }
}

@Composable
private fun HomeStatusCard(status: KeyboardStatus, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(24.dp),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Box(modifier = Modifier.padding(horizontal = 28.dp, vertical = 22.dp)) {
            Text(
                text = if (status.isDefault) {
                    "AtlasKeys is your active keyboard."
                } else {
                    "AtlasKeys is installed but isn't your active keyboard yet."
                },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DotMenuButton(
    theme: Theme,
    onThemeChange: (Theme) -> Unit,
    onSettings: () -> Unit,
    onReplayIntro: () -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var themeMenuExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.padding(16.dp)) {
        IconButton(onClick = { menuExpanded = true }, modifier = Modifier.size(48.dp)
        ) {
            Icon(
                contentDescription = "Menu",
                imageVector = Icons.Default.MoreVert,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
            DropdownMenuItem(
                text = { Text("Home") },
                leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                onClick = { menuExpanded = false }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                leadingIcon = { Icon(Icons.Default.Settings, contentDescription = null) },
                onClick = { onSettings(); menuExpanded = false }
            )
            DropdownMenuItem(
                text = { Text("Theme: ${theme.label}") },
                leadingIcon = { Icon(Icons.Default.Palette, contentDescription = null) },
                onClick = { themeMenuExpanded = true }
            )
            DropdownMenuItem(
                text = { Text("Replay intro") },
                leadingIcon = { Icon(Icons.Default.Refresh, contentDescription = null) },
                onClick = { onReplayIntro(); menuExpanded = false }
            )
        }

        DropdownMenu(expanded = themeMenuExpanded, onDismissRequest = { themeMenuExpanded = false }) {
            Theme.entries.forEach { theme ->
                DropdownMenuItem(
                    text = { Text(theme.label) },
                    onClick = {
                        onThemeChange(theme)
                        themeMenuExpanded = false
                        menuExpanded = false
                    }
                )
            }
        }
    }
}
