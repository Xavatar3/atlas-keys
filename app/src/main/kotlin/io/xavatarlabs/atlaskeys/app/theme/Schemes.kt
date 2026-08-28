package io.xavatarlabs.atlaskeys.app.theme

import androidx.compose.ui.graphics.Color 
import androidx.compose.material3.darkColorScheme 
import androidx.compose.material3.lightColorScheme 


internal val OnyxScheme = darkColorScheme(
    primary          = Palette.AccentBright,
    surface          = Palette.OnyxSurface,
    secondary        = Palette.Accent,
    background       = Palette.OnyxBackground,
    surfaceVariant   = Palette.OnyxVariant,
    onPrimary        = Palette.TextDark,
    onSurface        = Palette.TextPrimary,
    onBackground     = Palette.TextPrimary,
    onSurfaceVariant = Palette.TextSecondary
)

internal val GreyScheme = darkColorScheme(
    primary          = Palette.AccentBright,
    surface          = Palette.GreySurface,
    secondary        = Palette.Accent,
    background       = Palette.GreyBackground,
    surfaceVariant   = Palette.GreyVariant,
    onPrimary        = Color(0xFF10151C),
    onSurface        = Color(0xFFF2F4F7),
    onBackground     = Color(0xFFF2F4F7),
    onSurfaceVariant = Color(0xFFD5DAE1)
)

internal val DarkScheme = darkColorScheme(
    primary          = Palette.Accent,
    surface          = Palette.DarkSurface,
    secondary        = Palette.AccentBright,
    background       = Palette.DarkBackground,
    surfaceVariant   = Palette.DarkVariant,
    onPrimary        = Palette.White,
    onSurface        = Color(0xFFE8EAED),
    onBackground     = Color(0xFFE8EAED),
    onSurfaceVariant = Color(0xFFB6BCC4)
)

internal val LightScheme = lightColorScheme(
    primary          = Palette.Accent,
    surface          = Palette.White,
    secondary        = Palette.AccentBright,
    background       = Palette.LightBackground,
    surfaceVariant   = Palette.LightVariant,
    onPrimary        = Palette.White,
    onSurface        = Palette.AtlasSurface,
    onBackground     = Palette.AtlasSurface, // Keyboard's dark bg as ink
    onSurfaceVariant = Palette.GreySurface
)

internal val AtlasScheme = darkColorScheme(
    primary          = Palette.AccentBright,
    surface          = Palette.AtlasSurface, // colors.xml: kb_root
    secondary        = Palette.Accent,
    background       = Palette.AtlasBackground,
    surfaceVariant   = Palette.AtlasVariant, // colors.xml: key_bg/kb_ctrls_bg
    onPrimary        = Palette.AtlasBackground,
    onSurface        = Palette.TextPrimary,
    onBackground     = Palette.TextPrimary, // colors.xml: key_txt
    onSurfaceVariant = Palette.TextSecondary
)

internal val AmoledScheme = darkColorScheme(
    primary          = Palette.AccentBright,
    secondary        = Palette.Accent,
    surface          = Palette.AmoledSurface,
    background       = Palette.AmoledBackground,
    surfaceVariant   = Palette.AmoledVariant,
    onPrimary        = Palette.TextDark,
    onSurface        = Palette.TextPrimary,
    onBackground     = Palette.White,
    onSurfaceVariant = Palette.TextSecondary
)