package kr.co.fastcampus.part1.chapter3_7

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter3_4.R
import kr.co.fastcampus.part1.chapter3_4.ui.theme.RowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RowTheme {
                RowEx()
            }
        }
    }
}

/**
 * Row는 컴포넌트를 가로로 이어서 배치한다.
 * modifier width를 정하지 않으면 컨텐츠 사이즈만큼 적용된다
 * Row는 기본적으로 vertical alignment 적용
 */
@Composable
fun RowEx() {
    Row(
        //horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .height(40.dp)
            .width(200.dp)
    ) {
        Text(
            text = "첫 번째!",
            modifier = Modifier
                .align(Alignment.Top)
                .weight(3f)
                .background(Color.Magenta),
            textAlign = TextAlign.End,
        )
//        Text(
//            text = "두 번째!",
//            modifier = Modifier
//                .weight(1f)
//            //modifier = Modifier.align(Alignment.CenterVertically)
//        )
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = "추",
            modifier = Modifier
                .weight(1f)
                .background(Color.Cyan),
        )
        Text(
            text = "세 번째!",
            modifier = Modifier
                .weight(3f)
                .background(Color.Blue),
            textAlign = TextAlign.Center,
            //modifier = Modifier.align(Alignment.Bottom)
        )
    }

    // 스텝 1: 각 Text의 modifier에 align을 설정합시다.
    // Alignment.Top, CenterVertically, Bottom을 지정해봅시다.

    // 스텝 2: Row에 verticalAlignment를 설정해봅시다.
    // Text에 align을 사용할 때와 쓰이는 값이 같습니다.

    // 스텝 3: Row의 width를 200dp 정도로 설정합시다.
    // Row에 horizontalArrangement에 Arrangement.Center를
    // 설정해봅시다. Start, End, SpaceAround, SpaceBetween
    // SpaceEvenly를 설정해봅시다.

    // 스텝 4: horizontalArrangement를 제거하고 각 Text에
    // Modifier.weight를 설정합시다. 각 항목의 weight 값을 바꾸어
    // 보세요.

    // 스텝 5: Text 대신 Icon을 하나 넣어봅시다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RowTheme {
        RowEx()
    }
}