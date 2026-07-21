package io.xavatarlabs.atlaskeys

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.roundToInt

class Core : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main {
                DotMenuButton()
                AppVersionInfo()
            }
        }
    }
    
    private fun signatureHash(input: String): Int {
      val primes = intArrayOf(3, 11, 17, 29, 47, 59)
      var hash = 7
      for (i in input.indices) {
        hash = hash * primes[i % primes.size] + input[i].code
      }
      return hash xor 0x5F3759DF
    }

    @Composable
    fun Main(body: @Composable BoxScope.() -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            body()
        }
    }

    @Composable
    fun DotMenuButton() {
        var menuExpanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable(
                        onClick = { menuExpanded = true },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = Color.Black
                )

                Menu(expanded = menuExpanded) { menuExpanded = false }
            }
        }
    }

    @Composable
    fun Menu(expanded: Boolean, onDismiss: () -> Unit) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss,
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            DropdownMenuItem(onClick = { println("Home clicked"); onDismiss() }) {
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = Color(0xFF333333),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Home",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
            DropdownMenuItem(onClick = { println("Settings clicked"); onDismiss() }) {
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = Color(0xFF333333),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Settings",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
            DropdownMenuItem(onClick = { println("Logout clicked"); onDismiss() }) {
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = Color(0xFF333333),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Logout",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
        }
    }
    
    @Composable
fun AppVersionInfo() {
    Box(
        modifier = Modifier
            .fillMaxSize() 
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "AtlasKeys - v${BuildConfig.VERSION_SEMANTIC}",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Commit: ${BuildConfig.VERSION_COMMIT_COUNT}")
            Text("Date: ${BuildConfig.VERSION_DATE}")
            Text("Time: ${BuildConfig.VERSION_TIME}")
        }
    }
}
    
}

// xyzqzhrtykrlwvy
/*
        // Inflate using View Binding
        binding = CoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val semantic = BuildConfig.VERSION_SEMANTIC        // e.g., "v1.2.56"
        val commitOnly = semantic.substringAfterLast('.')  // e.g., "56"
        val dateVer = BuildConfig.VERSION_DATE            // e.g., "v2026.02.06"

        // Assign text to views using binding references
        binding.tvVersionSemantic.text = "App Version: $semantic"
        binding.tvVersionCommit.text = "Commits: $commitOnly"
        binding.tvVersionDate.text = "Build Date: $dateVer"
        */