package kr.co.fastcampus.part1.chapter3_16

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part1.chapter3_16.ui.theme.SlotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SlotEx()
                }
            }
        }
    }
}

// 스텝 1: `Row`를 `@Composable` 함수로 분리합시다.
// `checked`의 값, `Text`의 `text`를 인자로 전달합시다.

// 스텝 2: `@Composable` 함수에서 `@Composable () -> Unit` 타입으로
// `content`를 받아옵시다. `Row`의 `Text`를 뺴고 `content()`를 넣읍시다.
// `Row`에 `Modifier.clickable`를 넣어 전체를 클릭가능하게 합시다.

// 스텝 3: `content`의 타입을 `@Composable RowScope.() -> Unit`로
// 바꿉시다. 이렇게 다른 콤포저블 컨텐트를 넣는 방식을 Slot API라 합니다.

// 스텝 4: 상태를 바꾸는 람다를 `@Composable` 함수의 인자로 뺍시다.
// 인자에서 MutableState대신 boolean 값으로 변경합시다.

/**
 * Composable 메소드명은 대문자로 한다.
 */
@Composable
fun CheckboxWithText(checked : MutableState<Boolean>, text : String){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        Text(
            text = text,
            modifier = Modifier.clickable { checked.value = !checked.value }
        )
    }
}

//@Composable
//fun CheckboxWithSlot(
//    checked : MutableState<Boolean>,
//    content: @Composable () -> Unit){
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }
//    ) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it },
////            modifier = Modifier.clickable {
////
////            }
//        )
////        Text(
////            text = text,
////            modifier = Modifier.clickable { checked.value = !checked.value }
////        )
//        content()
//    }
//}

/**
 * RowScope.()를 사용해서 수신자를 바꾸는게 좋다.
 * 람다 내에서 RowScope 안에 있는것처럼 사용 할 수 있다.
 */
//@Composable
//fun CheckboxWithSlot(
//    checked : MutableState<Boolean>,
//    content: @Composable RowScope.() -> Unit){
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }
//    ) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it },
//        )
//        content()
//    }
//}
@Composable
fun CheckboxWithSlot(
//    checked : MutableState<Boolean>,
    checked : Boolean,
    /**
     * CheckboxWithSlot 안에서 상태를 바꾸고 싶지 않을경우
     * lambda를 받게한다!
     */
    onCheckedChangedLambda : () -> Unit,
    content: @Composable RowScope.() -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChangedLambda()
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChangedLambda() },
        )
        content()
    }
}

@Composable
fun SlotEx() {
//    val checked1 = remember { mutableStateOf(false) }
//    val checked2 = remember { mutableStateOf(false) }

    /**
     * .value를 사용않기 위해 by에게 위임하고, property로써 사용해보자
     * 위임할 수 있는 것은 밖으로 다 위임하는 형태로 만들어서 재사용성을 높히자.
     */
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }

    /**
     * Column
     * @Composable
    inline fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
    ) {
     와 같이 Composable로 되어 있는 람다를 받을 수 있다..
     Composable 함수가 다른 Composable 함수나 컴포넌트를 포함할 수 있는걸 Slot Api라고 한다.
     */

    Column {
//        CheckboxWithText(checked1, "텍스트1")
//        CheckboxWithText(checked2, "텍스트2")

//        CheckboxWithSlot(checked1, {Text("텍스트1")})
//        CheckboxWithSlot(checked2, {Text("텍스트2")})
        /**
         * rambda argument는 {} 밖으로 갈 수 있다.
         */
        CheckboxWithSlot(
            checked = checked1,
            onCheckedChangedLambda = { checked1 = !checked1 }) {
            Text(
                text = "텍스트1",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Color.Red,
                //Arrangement = Arrangement.Bottom
                /**
                 * Arrangement는 Row, Column, Box와 같은 레이아웃 컴포저블 내에서 자식 컴포저블의 배열을 제어한다.
                 * Text 컴포저블 자체에는 Arrangement 속성이 없으므로, Row 또는 Column 내에서 사용해야 한다.
                 */
            )
        }
        CheckboxWithSlot(
            checked = checked2,
            onCheckedChangedLambda = {
                checked2 = !checked2
            }
        ) {
            Text("텍스트2")
        }


//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Checkbox(
//                checked = checked1.value,
//                onCheckedChange = { checked1.value = it }
//            )
//            Text(
//                text = "텍스트 1",
//                modifier = Modifier.clickable { checked1.value = !checked1.value }
//            )
//        }

//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Checkbox(
//                checked = checked2.value,
//                onCheckedChange = { checked2.value = it }
//            )
//            Text(
//                text = "텍스트 2",
//                modifier = Modifier.clickable { checked2.value = !checked2.value }
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SlotTheme {
        SlotEx()
    }
}