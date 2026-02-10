package io.xavatarlabs.atlaskeys

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

import androidx.compose.runtime.*
import androidx.compose.material.*

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.Row

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.autoMirrored.Filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp

//import androidx.compose.material3.ripple.ripple
/*import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ripple.RippleAlpha
import androidx.compose.material3.ripple.RippleTheme
import androidx.compose.material3.ripple.*/

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp

class Core : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main {
                DotMenuButton()
            }
        }
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
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            // Floating circular button
            Box(
                modifier = Modifier
                  .size(56.dp)
                  .padding(16.dp)
                  .clip(CircleShape)
                  .background(Color.White)
                  //.background(Color.White, CircleShape)
                  .clickable(
                    onClick = { menuExpanded = true },
                    interactionSource = remember { MutableInteractionSource() },
                    //indication = (color = Color.Gray)
                    //indication = rememberRipple(color = Color.Gray, bounded = false, radius = 28.dp)
                    //indication = rememberRipple(color = Color.Gray)
                  ),
                  //.(
                    //color = Color.Gray,
                    //bounded = false
                  //),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = Color.Black
                )

                // Dropdown menu
                Menu(
                    expanded = menuExpanded,
                    onDismiss = { menuExpanded = false }
                )
            }
        }
    }

    @Composable
    fun Menu(expanded: Boolean, onDismiss: () -> Unit) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss,
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(12.dp)),
            //elevation = 12.dp
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
                        imageVector = Icons.Default.ExitToApp, //Icons.AutoMirrored.Filled.ExitToApp, 
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