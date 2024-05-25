package com.github.pedrodimoura.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object EasyWeatherUI {
    object Colors {
        val Purple80 = Color(0xFFD0BCFF)
        val PurpleGrey80 = Color(0xFFCCC2DC)
        val Pink80 = Color(0xFFEFB8C8)

        val Purple40 = Color(0xFF6650a4)
        val PurpleGrey40 = Color(0xFF625b71)
        val Pink40 = Color(0xFF7D5260)

        val White = Color(0xFFF1EAEA)
        val Black = Color(0xFF1A1919)

        object Temperature {
            val Colder = Color(0xFF4C44AD)
            val Cold = Color(0xFF4485AD)
            val Cool = Color(0xFF48A9A6)
            val Warm = Color(0xFFD4B483)
            val Hot = Color(0xFFCB8D77)
            val Hotter = Color(0xFFC1666B)
        }

        object Schemes {
            val DarkColorScheme = darkColorScheme(
                primary = Purple80,
                secondary = PurpleGrey80,
                tertiary = Pink80
            )

            val LightColorScheme = lightColorScheme(
                primary = Purple40,
                secondary = PurpleGrey40,
                tertiary = Pink40

                /* Other default colors to override
                background = Color(0xFFFFFBFE),
                surface = Color(0xFFFFFBFE),
                onPrimary = Color.White,
                onSecondary = Color.White,
                onTertiary = Color.White,
                onBackground = Color(0xFF1C1B1F),
                onSurface = Color(0xFF1C1B1F),
                */
            )
        }
    }

    object Typography {
        val defaults = Typography(
            /* Other default text styles to override
            titleLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
            ),
            labelSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp
            )
            */
        )
    }

    object Shapes {

    }

    object Margin {
        val x1 = 4.dp
        val x2 = 8.dp
        val x3 = 16.dp
    }

}
