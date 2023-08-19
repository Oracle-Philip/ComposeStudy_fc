package kr.co.fastcampus.part1.chapter3_10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part1.chapter3_10.ui.theme.ImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    Column {
        // 스텝 1: Image를 만들어봅시다.
        // painter 항목에 painterResource(id = R.drawable.wall)
        // contentDescription에 엔텔로프 캐년이라고 넣읍시다.
        Image(
            painter = painterResource(id = R.drawable.wall),
            contentDescription = "엔텔로프 캐년"
        )

        // Image

        // 스텝 2: 두 번째 Image를 만들어봅시다.
        // imageVector에 Icons.Filled.Settings를 설정해봅시다.
        Image(
            imageVector = Icons.Filled.Settings,
            contentDescription = "세팅"
        )

        // Image
        /**
         * Bitmap에 경우 Context가 있는 activity 등에서 가져와 주입을 시켜야지 다룰 수 있다.
         * 순수한 Composable에서는 잘 다루지 않는다.
         */
//        Image(
//            bitmap = ,
//            contentDescription = ""
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageTheme {
        Greeting()
    }
}