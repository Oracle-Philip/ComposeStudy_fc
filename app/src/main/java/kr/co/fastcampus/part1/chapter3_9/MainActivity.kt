package kr.co.fastcampus.part1.chapter3_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter3_9.ui.theme.BoxWithConstraintsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxWithConstraintsTheme {
                Outer()
            }
        }
    }
}

@Composable
fun Outer() {
    // 스텝 4: Column에 width를 지정해서 제한해 봅시다.
    Column(modifier = Modifier.width(150.dp)) {
        // 스텝 2: Inner의 인자로 Modifier.widthIn(min = 100.dp)를
        // 전달해봅시다. heightIn도 전달해봅시다. 각각 인자의 max값도
        // 전달해봅시다.

        /**
         * Inner 메소드의 시그니처를 수정했는데,
         * Modifier 객체는 Comanion Object이므로
         * Modifier만 전달해도 된다.
         *
         *     companion object : Modifier {
        override fun <R> foldIn(initial: R, operation: (R, Element) -> R): R = initial
        override fun <R> foldOut(initial: R, operation: (Element, R) -> R): R = initial
        override fun any(predicate: (Element) -> Boolean): Boolean = false
        override fun all(predicate: (Element) -> Boolean): Boolean = true
        override infix fun then(other: Modifier): Modifier = other
        override fun toString() = "Modifier"
        }
         */
//        Inner(Modifier)
        Inner(modifier = Modifier
            .width(200.dp)
            .height(160.dp)
//            .widthIn(min = 200.dp)
//            .heightIn(min = 50.dp, max = 160.dp)
        )
        Inner(modifier = Modifier
            .width(200.dp)
            .height(160.dp)
//            .widthIn(min = 200.dp)
//            .heightIn(min = 50.dp, max = 160.dp)
        )
    }
}

// 스텝 1: Inner 인자로 modifier를 전달해봅시다. 기본 값을 Modifier로 지정합시다.
// 파라미터로 받은 modifier를 BoxWithConstrains에 전달합시다.
@Composable
private fun Inner(
    modifier : Modifier = Modifier
) {
    /**
     * Box 컴포넌트와 다르게
     * maxWidth, maxHeight, minWidth, minHeight 제약을 설정 할 수 있다.
     */
    BoxWithConstraints(modifier) {
        // 스텝 3: maxHeight 값이 150dp가 넘을 때만 추가로 텍스트를 출력해봅시다.
        if(maxHeight > 150.dp){
            Text(
                text = "여기 꽤 길군요!",
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Text("maxW:$maxWidth maxH:$maxHeight minW: $minWidth minH:$minHeight")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoxWithConstraintsTheme {
        Outer()
    }
}