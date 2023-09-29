package kr.co.fastcampus.part1.chapter5_5.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

// 단계 2: `onSurface` 값을 할당합니다.
// `surface` 값도 할당해봅시다.

// 단계 3: `primary`와 `onPrimary` 값도 바꾸어봅시다.
private val LightColorPalette = lightColors(
    //primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = Color.Black,
    onSurface = Color.White, //onSuface -> 전경색상

    /**
     * xxx와 onXXX가 짝을 이루게 되어 있다.
     */

    /**
     * primary -> 제일 중요한 컨텐츠 의미
     * 예) 기본버튼에 경우 primary 색상으로 만들어져 있음
     */
    primary = Color.Magenta,
    onPrimary = Color.Cyan


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ThemeTestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = /*if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }*/ LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}