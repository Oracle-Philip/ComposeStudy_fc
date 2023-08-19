package kr.co.fastcampus.part1.chapter3_11

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import kr.co.fastcampus.part1.chapter3_11.ui.theme.CoilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoilTheme {
                CoilEx()
            }
        }
    }
}

@Composable
fun CoilEx() {
    // 스텝 3: rememberImagePainter를 이용해 Image의 painter를 설정합니다.
    // (Compose 한국어 문서의 추천, but Deprecated)
    // 이미지 URI: https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part1-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg
    val painter = rememberImagePainter(data = "https://i.pravatar.cc/150?img=3")

    //Spacer(modifier = Modifier.height(16.dp))
    // 스텝 4: AsyncImage를 이용해봅니다. model에 주소를 적으면 됩니다.

    Column(
        verticalArrangement = Arrangement.Bottom
    ){
        Image(
            painter = painter,
            contentDescription = "사람")

        AsyncImage(
            model = "https://i.pravatar.cc/150?img=9",
            contentDescription = "사람",
//        modifier = Modifier.align(Alignment.BottomEnd)
            //modifier = Modifier.padding(80.dp)
        )
        AsyncImage(
            model = "https://i.pravatar.cc/150?img=9",
            contentDescription = "사람",
//        modifier = Modifier.align(Alignment.BottomEnd)
            //modifier = Modifier.padding(80.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoilTheme {
        CoilEx()
    }
}