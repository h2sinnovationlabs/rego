package com.example.nativeandroidbasearchitecture.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.nativeandroidbasearchitecture.R

/**
 *
 * Thin	Thin Italic	100
 * Extra Light	Extra Light Italic	200
 * Light	Light Italic	300
 * Regular	Italic	400
 * Medium	Medium Italic	500
 * Semi Bold	Semi Bold Italic	600
 * Bold	Bold Italic	700
 * Extra Bold	Extra Bold Italic	800
 * Black	Black Italic	900
 *
 */

@Composable
fun fontLight() = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.poppins_light,
        )
    ),
)

@Composable
fun fontExtraLight() = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.poppins_extra_light,
        )
    ),
)

@Composable
fun fontMedium() = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.poppins_medium,
        )
    ),
)

@Composable
fun fontRegular() = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.poppins_regular,
        )
    ),
)

@Composable
fun fontSemiBold() = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.poppins_medium,
        )
    ),
)

@Composable
fun fontBold() = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.poppins_bold,
        )
    ),
)

@Composable
fun fontExtraBold() = TextStyle(
    fontFamily = FontFamily(
        Font(
            resId = R.font.poppins_extra_bold,
        )
    ),
)