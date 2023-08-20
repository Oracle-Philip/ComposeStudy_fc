package kr.co.fastcampus.part1.chapter4_10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part1.chapter4_10.ui.theme.DropDownMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropDownMenuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DropDownMenuEx()
                }
            }
        }
    }
}

@Composable
fun DropDownMenuEx() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { expandDropDownMenu = true }) {
            Text("드롭다운 메뉴 열기")
        }
        Text("카운터: $counter")
    }

    /**
     *  @Suppress("ModifierParameter")
    @Composable
    fun DropdownMenu(
        expanded: Boolean,
        onDismissRequest: () -> Unit,
        modifier: Modifier = Modifier,
        offset: DpOffset = DpOffset(0.dp, 0.dp),
        properties: PopupProperties = PopupProperties(focusable = true),
        content: @Composable ColumnScope.() -> Unit
        ) {
            val expandedStates = remember { MutableTransitionState(false) }
            expandedStates.targetState = expanded

            if (expandedStates.currentState || expandedStates.targetState) {
            val transformOriginState = remember { mutableStateOf(TransformOrigin.Center) }
            val density = LocalDensity.current
            val popupPositionProvider = DropdownMenuPositionProvider(
            offset,
            density
            ) { parentBounds, menuBounds ->
            transformOriginState.value = calculateTransformOrigin(parentBounds, menuBounds)
            }

            Popup(
                onDismissRequest = onDismissRequest,
                popupPositionProvider = popupPositionProvider,
                properties = properties
                ) {
                DropdownMenuContent(
                expandedStates = expandedStates,
                transformOriginState = transformOriginState,
                modifier = modifier,
                content = content
                )
            }
        }
    }
     */

    // 단계 1: `DropdownMenu`를 만들고 `expanded`를 `expandDropDownMenu`로
    // 등록합시다.
    // `onDismissRequest`에 대해서는 `expandDropDownMenu`를 `false`로 바꿉니다.
    DropdownMenu(
        expanded = expandDropDownMenu,
        onDismissRequest = {
            expandDropDownMenu = false
        }
    ) {
        DropdownMenuItem(
            onClick = {
                counter += 1
            }
        ){
            Text(
                text = "증가"
            )
        }
        DropdownMenuItem(
            onClick = {
                counter -= 1
            }
        ){
            Text(
                text = "감소"
            )
        }
    }

    // 단계 2: 두개의 `DropdownMenuItem`을 등록합시다. `onClick`을 구현하고
    // 내용물은 `Text`로 채워봅시다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropDownMenuTheme {
        DropDownMenuEx()
    }
}