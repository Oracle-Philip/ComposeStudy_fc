package kr.co.fastcampus.part1.chapter3_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter3_4.ui.theme.ColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColumnTheme {
                ColumnEx()
            }
        }
    }
}

/**
 * - Row & Column은 1차원으로만 Alignment 할 수 있고,
 * - Box는 2차원 Alignment 가능하다.
 *   - Column에 경우 Horizontal하게만 Alignment를 할 수 있다!
 *   - Row는 Vertical하게만 Alignment를 할 수 있다!
 *   - Row는 가로배치이므로 Alignment는 세로로 적용된다!
 *   - Box 컴포넌트에서는 Alignment가 Vertical, Horizontal 둘다 된다!
 */
@Composable
fun ColumnEx() {
    Column(
        modifier = Modifier.size(100.dp),
        horizontalAlignment = Alignment.End,
        /**
         * Alignment는 레이아웃의 방향과 반대, Column이 세로배치이므로 Alignment는 가로로 적용된다.
         * Alignment는 2D, 1D에 Horizontal Center, vertical Center 등 고려할 것들이 있다.
         */

        verticalArrangement =  Arrangement.Center
        /**
         * Arrangement는 레이아웃의 진행방향과 같은 방향으로 적용된다.
         */


    ) {
        Text(
            text = "첫 번째",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "두 번째"
        )
        Text(
            text = "세 번째",
            modifier = Modifier.align(Alignment.Start)
        )
    }

    // 스텝 1: horizontalAlignment를 Column에 적용해봅시다.

    // 스텝 2: Column에 verticalArrangement를 지정해봅시다.
    // SpaceAround, SpaceEvenly, SpaceBetween도 해봅시다.

    // 스텝 3: Text에 Modifier.align을 사용해 봅시다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColumnTheme {
        ColumnEx()
    }
}