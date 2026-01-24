package com.serj113.imaginemovies.lib.march

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.serj113.imaginemovies.lib.march.base.ColorToken.BackgroundDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.BackgroundLight
import com.serj113.imaginemovies.lib.march.base.ColorToken.Error
import com.serj113.imaginemovies.lib.march.base.ColorToken.ErrorContainer
import com.serj113.imaginemovies.lib.march.base.ColorToken.ErrorContainerDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.ErrorDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.InverseOnSurface
import com.serj113.imaginemovies.lib.march.base.ColorToken.InverseOnSurfaceDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.InversePrimary
import com.serj113.imaginemovies.lib.march.base.ColorToken.InversePrimaryDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.InverseSurface
import com.serj113.imaginemovies.lib.march.base.ColorToken.InverseSurfaceDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnBackground
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnBackgroundDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnError
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnErrorContainer
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnErrorContainerDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnErrorDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnPrimary
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnSecondary
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnSurface
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnSurfaceDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnSurfaceVariant
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnSurfaceVariantDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.OnTertiary
import com.serj113.imaginemovies.lib.march.base.ColorToken.Outline
import com.serj113.imaginemovies.lib.march.base.ColorToken.OutlineDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.OutlineVariant
import com.serj113.imaginemovies.lib.march.base.ColorToken.OutlineVariantDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.Primary
import com.serj113.imaginemovies.lib.march.base.ColorToken.PrimaryDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.PrimaryLight
import com.serj113.imaginemovies.lib.march.base.ColorToken.Scrim
import com.serj113.imaginemovies.lib.march.base.ColorToken.Secondary
import com.serj113.imaginemovies.lib.march.base.ColorToken.SecondaryDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.SecondaryLight
import com.serj113.imaginemovies.lib.march.base.ColorToken.SurfaceDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.SurfaceLight
import com.serj113.imaginemovies.lib.march.base.ColorToken.SurfaceTint
import com.serj113.imaginemovies.lib.march.base.ColorToken.SurfaceTintDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.SurfaceVariant
import com.serj113.imaginemovies.lib.march.base.ColorToken.SurfaceVariantDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.Tertiary
import com.serj113.imaginemovies.lib.march.base.ColorToken.TertiaryDark
import com.serj113.imaginemovies.lib.march.base.ColorToken.TertiaryLight

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryDark,
    onPrimaryContainer = PrimaryLight,

    secondary = SecondaryLight,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryDark,
    onSecondaryContainer = SecondaryLight,

    tertiary = TertiaryLight,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryDark,
    onTertiaryContainer = TertiaryLight,

    background = BackgroundDark,
    onBackground = OnBackgroundDark,

    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    surfaceTint = SurfaceTintDark,

    inverseSurface = InverseSurfaceDark,
    inverseOnSurface = InverseOnSurfaceDark,
    inversePrimary = InversePrimaryDark,

    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,

    outline = OutlineDark,
    outlineVariant = OutlineVariantDark,

    scrim = Scrim
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryLight,
    onPrimaryContainer = PrimaryDark,

    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryLight,
    onSecondaryContainer = SecondaryDark,

    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryLight,
    onTertiaryContainer = TertiaryDark,

    background = BackgroundLight,
    onBackground = OnBackground,

    surface = SurfaceLight,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceTint = SurfaceTint,

    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,
    inversePrimary = InversePrimary,

    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    outline = Outline,
    outlineVariant = OutlineVariant,

    scrim = Scrim
)

@Composable
fun MarchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
