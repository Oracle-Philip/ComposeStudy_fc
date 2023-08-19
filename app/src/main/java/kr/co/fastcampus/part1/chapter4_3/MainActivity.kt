package kr.co.fastcampus.part1.chapter4_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kr.co.fastcampus.part1.chapter4_3.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConstraintLayoutEx()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutEx() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // 단계 2: createRefs()를 이용해서 아래 박스들의 레퍼런스를 가져옵시다.
        // createRefs는 여러개의 레퍼런스를 리턴하니 destruction으로 분해합시다.
        // red, magenta, green, yellow 박스가 있습니다.

        /**
         * createRefs()는 ConstraintLayout에서 사용할 수 있는 함수이고
         * 여러개의 children을 배치할 수 있다. <--> createRef()
         * inner class ConstrainedLayoutReferences internal constructor() {
                operator fun component1() = createRef()
                operator fun component2() = createRef()
                operator fun component3() = createRef()
                operator fun component4() = createRef()
                operator fun component5() = createRef()
                operator fun component6() = createRef()
                operator fun component7() = createRef()
                operator fun component8() = createRef()
                operator fun component9() = createRef()
                operator fun component10() = createRef()
                operator fun component11() = createRef()
                operator fun component12() = createRef()
                operator fun component13() = createRef()
                operator fun component14() = createRef()
                operator fun component15() = createRef()
                operator fun component16() = createRef()
        }
         */
        val (redbox, magentaBox, greenBox, yellowBox) = createRefs()

        Box(
            // 단계 3: constraintsAs 모디파이어를 추가하고 레퍼런스를 전달합시다.
            // 후행 람다로 top, start, end, bottom 앵커를 지정하고
            // linkTo 호출합니다.
            // 인자로는 parent의 앵커(top, start, end, bottom)을
            // 전달해봅시다.

            // 단계 4: linkTo의 키워드 인자 margin을 추가합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redbox){
                    //anchor가 top bottom 등이 있다.
                    /**
                     *     fun linkTo(
                                anchor: ConstraintLayoutBaseScope.HorizontalAnchor,
                                margin: Dp = 0.dp,
                                goneMargin: Dp = 0.dp
                    )
                     */
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 4.dp)
                } //constrainAs는 Box가 어디 배치되어야 할지 렌더링해주는 modifier이다.
        )
        Box(
            // 단계 5: 마젠타 박스를 parent의 start와 end에 연결합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox){
                    /**
                     * 참고로 .constainsAs(magnetaBox){  }
                     *  에서 {} 영역을 후행람다라 한다.. 즉, 매개변수 마지막이 함수일 경우
                     *  {} 밖으로 빼낼 수 있다..
                     *  constrainBlock: ConstrainScope.() -> Unit <--마지막 매개변수..
                     */
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                    /**
                     * 참고 vertical center
                     */
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
                }
        )
        Box(
            // 단계 6: 그린 박스를 linkTo를 이용해서 정 가운데로 연결해봅시다.

            // 단계 7: 앵커 메서드 linkTo 대신에 centerTo 함수를 사용해봅시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .constrainAs(greenBox){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Box(
            // 단계 8: 옐로 박스를 마젠타 박스 오른쪽 대각선 아래에 위치해봅시다.
            // linkTo를 쓰고 인자로 parent 대신 그린 박스의 레퍼런스를 사용합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox){
                    //centerTo(parent)
                    //centerVerticallyTo(parent)
                    //centerHorizontallyTo(parent)

                    start.linkTo(magentaBox.end)
                    top.linkTo(magentaBox.bottom)

                    /**
                     * centerHorizontally
                     * centerVertically 등도 있다..
                     */
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConstraintLayoutTheme {
        ConstraintLayoutEx()
    }
}