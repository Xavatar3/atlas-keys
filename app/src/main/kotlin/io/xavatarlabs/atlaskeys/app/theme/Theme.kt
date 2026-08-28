package io.xavatarlabs.atlaskeys.app.theme

// UI
import androidx.compose.ui.unit.dp 
import androidx.compose.ui.unit.sp 
import androidx.compose.ui.text.TextStyle 
import androidx.compose.ui.text.font.FontWeight 

// Material 3
import androidx.compose.material3.Shapes 
import androidx.compose.material3.Typography 
import androidx.compose.material3.ColorScheme 
import androidx.compose.material3.MaterialTheme 

// Rest
import androidx.compose.runtime.Composable 
import androidx.compose.foundation.shape.RoundedCornerShape 


enum class Theme(val label: String, val scheme: ColorScheme) {
    ONYX("Onyx", OnyxScheme),
    GREY("Grey", GreyScheme),
    DARK("Dark", DarkScheme),
    LIGHT("Light", LightScheme),
    ATLAS("Atlas", AtlasScheme),
    AMOLED("AMOLED", AmoledScheme)
}

val AtlasShapes = Shapes(
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(16.dp)
)

val AtlasTypography = Typography(
    titleLarge = TextStyle(fontWeight = FontWeight.SemiBold),
    bodyLarge  = TextStyle(fontSize = 16.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.Medium)
)

@Composable
fun AtlasTheme(theme: Theme = Theme.ATLAS, content: @Composable () -> Unit) {
    MaterialTheme(
        content = content,
        shapes = AtlasShapes,
        colorScheme = theme.scheme,
        typography = AtlasTypography
    )
}
