package kr.co.fastcampus.part1.chapter4_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter4_7.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CanvasEx()
                }
            }
        }
    }
}

@Composable
fun CanvasEx() {
    /**
     * @Composable
    fun Canvas(modifier: Modifier, onDraw: DrawScope.() -> Unit) =
        Spacer(modifier.drawBehind(onDraw))
     이처럼, Canvas는 default modifier가 없어서 만들어줘야한다.
     */
    Canvas(modifier = Modifier.size(20.dp)) {
        /**
         * Canvas는 DrawScope라는 후행람다를 제공한다.
         */
        drawLine(
            color = Color.Red,
            //시작offset
            start = Offset(30f, 10f,),
            end = Offset(50f, 40f)
        )

        drawCircle(
            color = Color.Yellow,
            radius = 10f,
            center = Offset(15f, 40f))

        drawRect(
            color = Color.Magenta,
            topLeft = Offset(30f, 30f),
            size = Size(10f, 10f)
        )

        // 단계 1: `drawLine`을 사용해봅시다. 파라미터로 색상, 시작(`Offset`)
        // 끝(`Offset` 타입)을 받습니다.

        // 단계 2: `drawCircle`을 사용해보세요. 색상, 반지름, 중앙(`Offset`)

        // 단계 3: 아래의 규칙으로 그려진 아이콘 `Icons.Filled.Send`를
        // `drawLine`으로 변경해봅시다.

        //Icons.Filled.Send
        /**
         * _send = materialIcon(name = "Filled.Send") {
            materialPath {
            moveTo(2.01f, 21.0f) <--그리지 않고 커서를 이동시키는거
            lineTo(23.0f, 12.0f) <--커서 위치에서 선을 긋는거
            lineTo(2.01f, 3.0f)
            lineTo(2.0f, 10.0f)
            lineToRelative(15.0f, 2.0f)
            lineToRelative(-15.0f, 2.0f)
            close() <--처음 위치로 가는거
            }
        }
         */
        drawLine(
            color = Color.Green,
            start = Offset(2.01f, 21.0f),
            end = Offset(23.0f, 12.0f)
        )

        drawLine(
            color = Color.Green,
            start = Offset(23.0f, 12.0f),
            end = Offset(2.01f, 3.0f)
        )

        drawLine(
            color = Color.Green,
            start = Offset(2.01f, 3.0f),
            end = Offset(2.0f, 10.0f)
        )

        drawLine(
            color = Color.Green,
            start = Offset(2.0f, 10.0f),
            end = Offset(17.0f, 12.0f)
        )

        drawLine(
            color = Color.Green,
            start = Offset(17.0f, 12.0f),
            end = Offset(2.0f, 14.0f)
        )

        drawLine(
            color = Color.Green,
            start = Offset(2.0f, 14.0f),
            end = Offset(2.01f, 21.0f)
        )


        // ImageVector에서는 한붓 그리기 처럼 연속으로 그려집니다.
        // `moveTo`로 2.01f, 21.0f로 이동한 후 거기에서
        // 23.0f, 12.0f로 선이 그어지는 식입니다.

        //        moveTo(2.01f, 21.0f)
        //        lineTo(23.0f, 12.0f)
        //        lineTo(2.01f, 3.0f)
        //        lineTo(2.0f, 10.0f)
        //        lineToRelative(15.0f, 2.0f)
        //        lineToRelative(-15.0f, 2.0f)
        //        close()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CanvasTheme {
        CanvasEx()
    }
}