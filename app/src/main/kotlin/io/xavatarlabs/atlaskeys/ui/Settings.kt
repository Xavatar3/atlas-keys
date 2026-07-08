package io.xavatarlabs.atlaskeys.ui

//Composable Foundation
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

// Composable Basic
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import android.widget.Toast

//Compose UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext


@Composable
fun Settings(onClose: () -> Unit){
  val items = listOf(
    "Theme", "Layout", "Language", "Clipboard",
    "Emoji", "Incognito", "Suggestions", "Stats",
    "Resize", "Sound", "Vibration", "Shortcuts",
    "Account", "Backup", "About", "More"
  )
  val context = LocalContext.current
  
  Column(modifier = Modifier.fillMaxSize()) {
  Button(onClick=onClose) { Text("Back") }
  
  LazyVerticalGrid(
    columns = GridCells.Fixed(4),
    modifier = Modifier.fillMaxWidth().height(200.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ){
    items(items){ item ->
      Box(
        modifier = Modifier
          .aspectRatio(1f)
          .background(Color.DarkGray)
          .clickable {
            Toast.makeText(
              context, item, Toast.LENGTH_SHORT
            ).show()
          },
        contentAlignment = Alignment.Center
      ){ Text(item, color = Color.White) }
    }
  }
  }
}
