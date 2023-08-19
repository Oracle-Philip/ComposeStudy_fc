package kr.co.fastcampus.part1.chapter3_17

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter3_17.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldEx()
                }
            }
        }
    }
}

@Composable
fun CheckBoxWithContent(
    checked: Boolean,
    toggleState: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    /**
     * Row 컴포저블을 클릭할때 처리하는 Event는 toggleState: () -> Unit으로 간다.
     * toggleState()가 Checkbox의 onCheckedChange에서도 사용되고 있는데
     * Checkbox를 클릭할때도 onCheckedChange 안에 toggleState()를 가지고 있는 이유는
     * Row 안에 있는 Checkbox가 Event를 처리해버리기 때문이다. 그래서 별도로 toggleState()를 호출해주고 있다.
     * Checkbox가 check Event를 가로채므로
     */
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { toggleState() }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                toggleState()
          },
        )
        content()
    }
}

@Composable
fun ScaffoldEx() {
    var checked by remember { mutableStateOf(false) }

    /**
     * Scaffold는 parameter를 기준으로, 필요로하는 부분에 내용을 넣을 수 있는
     * Ui 컴포저블 기본 구성을 제공해준다.
     * 예) TopAppBar, FloatingButton 등...
     */
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {}) {
                    Image(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "뒤로가기"
                    )
                }
            },
            title = {
                Text( text = "Scaffold App")
            },

        )
        // 스텝 1: `topBar`를 `TopAppBar`로 채워 봅시다.

    },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                
            }
        }
    ) {
        Surface(modifier = Modifier.padding(8.dp)) {
            // 스텝 2: 아래에 CheckBoxWithContent를 넣어봅시다.
            CheckBoxWithContent(
                checked = checked,
                toggleState = { checked = !checked }
            ){
                /**
                 * RowScope 영역
                 */
                Text(
                    text = "컴포즈를 좋아합니다."
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldTheme {
        ScaffoldEx()
    }
}