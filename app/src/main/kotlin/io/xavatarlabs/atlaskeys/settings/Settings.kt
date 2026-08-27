package io.xavatarlabs.atlaskeys.settings

//Composable Foundation
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape

// Composable Basic
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.widget.Toast

//Compose UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

// Material Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.ContentPaste
import androidx.compose.material.icons.rounded.EmojiEmotions
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material.icons.rounded.QueryStats
import androidx.compose.material.icons.rounded.OpenInFull
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material.icons.rounded.Vibration
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Backup
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun Settings(onClose: () -> Unit){

  val items = listOf(
    "Theme" to Icons.Rounded.Palette,
    "Layout" to Icons.Rounded.Keyboard,
    "Language" to Icons.Rounded.Language,
    "Clipboard" to Icons.Rounded.ContentPaste,
    "Emoji" to Icons.Rounded.EmojiEmotions,
    "Incognito" to Icons.Rounded.VisibilityOff,
    "Suggestions" to Icons.Rounded.Lightbulb,
    "Stats" to Icons.Rounded.QueryStats,
    "Resize" to Icons.Rounded.OpenInFull,
    "Sound" to Icons.Rounded.VolumeUp,
    "Vibration" to Icons.Rounded.Vibration,
    "Shortcuts" to Icons.Rounded.Bolt,
    "Account" to Icons.Rounded.Person,
    "Backup" to Icons.Rounded.Backup,
    "About" to Icons.Rounded.Info,
    "More" to Icons.Rounded.MoreHoriz
  )

  val context = LocalContext.current
  var search by remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .windowInsetsPadding(WindowInsets.statusBars)
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {

    Text(
      "Settings",
      style = MaterialTheme.typography.headlineSmall,
      fontWeight = FontWeight.Bold
    )

    OutlinedTextField(
      value = search,
      onValueChange = { search = it },
      modifier = Modifier.fillMaxWidth(),
      singleLine = true,
      leadingIcon = {
        Icon(
          Icons.Rounded.Search,
          contentDescription = null
        )
      },
      placeholder = {
        Text("Search settings")
      },
      shape = RoundedCornerShape(16.dp)
    )

    Button(
      onClick = onClose,
      modifier = Modifier.fillMaxWidth()
    ) { Text("Back") }

    HorizontalDivider()

    LazyVerticalGrid(
      columns = GridCells.Fixed(4),
      modifier = Modifier
        .fillMaxWidth()
        .height(340.dp),
      contentPadding = PaddingValues(4.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){

      items(items.filter { it.first.contains(search, true) } ){ (item, icon) ->

        var pressed by remember { mutableStateOf(false) }

        val scale by animateFloatAsState(if (pressed) 0.94f else 1f, label = "")

        Card(
          modifier = Modifier
            .aspectRatio(1f)
            .scale(scale)
            .clickable {
              pressed = true
              Toast.makeText(
                context,
                item,
                Toast.LENGTH_SHORT
              ).show()
              pressed = false
            },
          shape = RoundedCornerShape(20.dp),
          elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
          ),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
          )
        ){

          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(
                MaterialTheme.colorScheme.surfaceVariant
              ),
            contentAlignment = Alignment.Center
          ){

            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center
            ){

              Icon(
                imageVector = icon,
                contentDescription = item,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
              )

              Spacer(
                modifier = Modifier.height(10.dp)
              )

              Text(
                text = item,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }
      }
    }
  }
}