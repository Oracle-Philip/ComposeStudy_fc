package kr.co.fastcampus.part1.chapter4_15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.VectorConverter
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter4_15.ui.theme.Animation2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Animation2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Animation2Ex()
                }
            }
        }
    }
}

@Composable
fun Animation2Ex() {
    var isDarkMode by remember { mutableStateOf(false) }

    /**
     *     animateColorAsState(targetValue =), animateFloatAsState(targetValue = )
     *     에 경우 관리가 잘 안되는 경우가 있어서 updateTransition을 사용하겠다..
     */

//    val isDarkMode by remember {
//        updateTransition(
//            targetState =
//        )
//    }

    val transition = updateTransition(
        targetState = isDarkMode,
        label = "다크 모드 트랜지션"
    )

    /*@Composable
    inline fun <S> Transition<S>.animateColor(
        noinline transitionSpec:
        @Composable Transition.Segment<S>.() -> FiniteAnimationSpec<Color> = { spring() },
        label: String = "ColorAnimation",
        targetValueByState: @Composable() (state: S) -> Color
    ): State<Color> {
        val colorSpace = targetValueByState(targetState).colorSpace
        val typeConverter = remember(colorSpace) {
            androidx.compose.ui.graphics.Color.VectorConverter(colorSpace)
        }

        return animateValue(typeConverter, transitionSpec, label, targetValueByState)
    }*/

    /**
     * by를 이용하면 backgroundColor 상태가 바뀌거나 값을 가져올 수 있다... property로써 사용하는거니까..
     */

    val backgroundColor by transition.animateColor(label = "다크 모드 배경색상 애니메이션") { state ->
        when(state){
            false -> Color.White
            true -> Color.Black
        }
    }

    // 단계 1: `updateTransition` 수행하고 `targetState`를 `isDarkMode`로
    // 설정합니다. `transition`으로 리턴을 받습니다.

    // 단계 2: `transition`에 대해 `animateColor`를 호출해 `backgroundColor`를 받습니다.
    // 배경색상을 만듭시다. false일 때 하얀 배경, true일 때 검은 배경.

    // 단계 3: 글자 색상을 만듭시다.

    val color by transition.animateColor(label = "다크 모드 글자 색상 애니메이션") { state ->
        when(state){
            false -> Color.Black
            true -> Color.White
        }
    }

    // 단계 4: `animateFloat`를 호출해서 알파 값을 만듭시다.
    val alpha by transition.animateFloat(label = "다크 모드 알파 애니메이션") { state ->
        when(state){
            false -> 0.7f
            true -> 1f
        }
    }

    // 단계 5: 컬럼에 배경과 알파를 적용합시다.
    Column (
        modifier = Modifier
            .background(backgroundColor)
            .alpha(alpha)
            ){
        // 단계 6: 라디오 버튼에 글자 색을 적용합시다.
        RadioButtonWithText(text = "일반 모드", color = color, selected = !isDarkMode) {
            isDarkMode = false
        }
        RadioButtonWithText(text = "다크 모드", color = color, selected = isDarkMode) {
            isDarkMode = true
        }

        // 단계 7: Crossfade를 이용해 `isDarkMode`가 참일 경우
        // `Row`로 항목을 표현하고 거짓일 경우 `Column`으로 표현해봅시다.
        /*Row {
            Box(modifier = Modifier
                .background(Color.Red)
                .size(20.dp)) {
                Text("1")
            }
            Box(modifier = Modifier
                .background(Color.Magenta)
                .size(20.dp)) {
                Text("2")
            }
            Box(modifier = Modifier
                .background(Color.Blue)
                .size(20.dp)) {
                Text("3")
            }
        }*/

        /**
         * [Crossfade] allows to switch between two layouts with a crossfade animation.
         *
         * @sample androidx.compose.animation.samples.CrossfadeSample
         *
         * @param targetState is a key representing your target layout state. Every time you change a key
         * the animation will be triggered. The [content] called with the old key will be faded out while
         * the [content] called with the new key will be faded in.
         * @param modifier Modifier to be applied to the animation container.
         * @param animationSpec the [AnimationSpec] to configure the animation.
         */
        /*@OptIn(ExperimentalAnimationApi::class)
        @Composable
        fun <T> Crossfade(
            targetState: T,
            modifier: Modifier = Modifier,
            animationSpec: FiniteAnimationSpec<Float> = tween(),
            content: @Composable (T) -> Unit
        ) {
            val transition = updateTransition(targetState)
            transition.Crossfade(modifier, animationSpec, content = content)
        }*/
        /**
         * 왜 if else가 아니라 Crossfade를 사용한걸까?
         * Crossfade는 그 안에 contents에 Animation 효과를 줄 수 있다! (전환과정에서)
         */
        Crossfade(targetState = isDarkMode) { state ->
            when(state){
                false -> {
                    Column {
                        Box(modifier = Modifier
                            .background(Color.Red)
                            .size(20.dp)) {
                            Text("1")
                        }
                        Box(modifier = Modifier
                            .background(Color.Magenta)
                            .size(20.dp)) {
                            Text("2")
                        }
                        Box(modifier = Modifier
                            .background(Color.Blue)
                            .size(20.dp)) {
                            Text("3")
                        }
                    }
                }
                true -> {
                    Row {
                        androidx.compose.material.Surface(
                            color = Color.White
                        ) {
                            Text(
                                text = "나라말\n Hello world!"
                            )
                        }
                        Box(modifier = Modifier
                            .background(Color.Red)
                            .size(20.dp)) {
                            Text("A")
                        }
                        Box(modifier = Modifier
                            .background(Color.Magenta)
                            .size(20.dp)) {
                            Text("B")
                        }
                        Box(modifier = Modifier
                            .background(Color.Blue)
                            .size(20.dp)) {
                            Text("C")
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Animation2Theme {
        Animation2Ex()
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonWithTextPreview() {
    Animation2Theme {
        RadioButtonWithText(
            text = "라디오 버튼",
            color = Color.Red,
            selected = true,
            onClick = {}
        )
    }
}

/**
 * RadioButton은 Event 자체를 가로채기 때문에
 * Row modifier에 selected와 onClick을 세팅해도 인식하지 않는다.
 * 그래서 Row의 selected와 onClick과
 * RadioButton의 selcted와 onClick을 동일하게 해준다..
 */
@Composable
fun RadioButtonWithText(
    text: String,
    color: Color = Color.Black,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = text, color = color)
    }
}