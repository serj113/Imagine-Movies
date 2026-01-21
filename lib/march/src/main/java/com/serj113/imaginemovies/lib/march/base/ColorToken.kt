package com.serj113.imaginemovies.lib.march.base

import androidx.compose.ui.graphics.Color

object ColorToken {
    // Primary Colors
    val Primary = Color(0xFF154854)
    val PrimaryLight = Color(0xFF457480)
    val PrimaryDark = Color(0xFF00202B)

    // Secondary Colors
    val Secondary = Color(0xFF3D7F8F)
    val SecondaryLight = Color(0xFF6EAEBF)
    val SecondaryDark = Color(0xFF005262)

    // Tertiary Colors (Complementary warm accent to balance the cool teal palette)
    val Tertiary = Color(0xFF8D6E4F)
    val TertiaryLight = Color(0xFFB89A7E)
    val TertiaryDark = Color(0xFF634826)
    val OnTertiary = Color(0xFFFFFFFF)

    // Background Colors
    val BackgroundLight = Color(0xFFF5F8F9)
    val BackgroundDark = Color(0xFF0A1214)
    val SurfaceLight = Color(0xFFFFFFFF)
    val SurfaceDark = Color(0xFF1A2428)

    // Text Colors - Light Theme
    val TextPrimary = Color(0xFF0D2126)
    val TextSecondary = Color(0xFF4A5F66)
    val TextTertiary = Color(0xFF6B7F87)
    val OnPrimary = Color(0xFFFFFFFF)
    val OnSecondary = Color(0xFFFFFFFF)
    val OnBackground = Color(0xFF0D2126)
    val OnSurface = Color(0xFF0D2126)

    // Text Colors - Dark Theme
    val TextPrimaryDark = Color(0xFFE1E8EB)
    val TextSecondaryDark = Color(0xFFB8C5CB)
    val OnBackgroundDark = Color(0xFFE1E8EB)
    val OnSurfaceDark = Color(0xFFE1E8EB)

    // Additional UI Colors - Light Theme
    val Error = Color(0xFFBA1A1A)
    val ErrorContainer = Color(0xFFFFDAD6)
    val OnError = Color(0xFFFFFFFF)
    val OnErrorContainer = Color(0xFF410002)

    val Success = Color(0xFF2E7D32)
    val SuccessContainer = Color(0xFFC8E6C9)
    val Warning = Color(0xFFF57C00)
    val WarningContainer = Color(0xFFFFE0B2)

    val Outline = Color(0xFF6B7F87)
    val OutlineVariant = Color(0xFFBDC9CF)

    // Additional UI Colors - Dark Theme
    val ErrorDark = Color(0xFFFFB4AB)
    val ErrorContainerDark = Color(0xFF93000A)
    val OnErrorDark = Color(0xFF690005)
    val OnErrorContainerDark = Color(0xFFFFDAD6)

    val OutlineDark = Color(0xFF8A9699)
    val OutlineVariantDark = Color(0xFF3F4B4F)

    // Surface Variants - Light Theme
    val SurfaceVariant = Color(0xFFDCE4E8)
    val OnSurfaceVariant = Color(0xFF3F4B4F)

    // Surface Variants - Dark Theme
    val SurfaceVariantDark = Color(0xFF3F4B4F)
    val OnSurfaceVariantDark = Color(0xFFBFC9CD)

    // Surface Tints and Containers
    val SurfaceTint = Primary
    val SurfaceTintDark = PrimaryLight

    // Inverse Colors - Light Theme
    val InverseSurface = Color(0xFF2D3639)
    val InverseOnSurface = Color(0xFFEFF1F3)
    val InversePrimary = Color(0xFF6EAEBF)

    // Inverse Colors - Dark Theme
    val InverseSurfaceDark = Color(0xFFE1E8EB)
    val InverseOnSurfaceDark = Color(0xFF2D3639)
    val InversePrimaryDark = Color(0xFF154854)

    // Scrim (for modal overlays)
    val Scrim = Color(0xFF000000)
}
