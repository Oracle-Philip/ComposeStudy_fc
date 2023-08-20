package kr.co.fastcampus.part1.chapter4_8

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part1.chapter4_8.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DialogEx()
                }
            }
        }
    }
}

@Composable
fun DialogEx() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = !openDialog }) {
            Text("다이얼로그 열기")
        }
        Text("카운터: $counter")
    }

    /**
     * @Composable
    fun AlertDialog(
        onDismissRequest: () -> Unit,
        confirmButton: @Composable () -> Unit,
        modifier: Modifier = Modifier,
        dismissButton: @Composable (() -> Unit)? = null,
        title: @Composable (() -> Unit)? = null,
        text: @Composable (() -> Unit)? = null,
        shape: Shape = MaterialTheme.shapes.medium,
        backgroundColor: Color = MaterialTheme.colors.surface,
        contentColor: Color = contentColorFor(backgroundColor),
        properties: DialogProperties = DialogProperties()
    ) {
     와 같이 AlertDialog는 confirmButton, dismissButton, title, text가
     slotApi로 되어있다.
     */

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
            // 단계 1: `openDialog`를 이용해 다이얼로그를 끌 수 있게 합니다.

            /**
             * onDismissRequest는 Dialog 밖을 클릭할때 어떤 처리 할지 부분이다.
             */
            openDialog = false
        }, confirmButton = {
            // 단계 2: "더하기" 버튼을 만들고 `counter`를 증가시킵니다.
            // 다이얼로그도 끕니다.
                Button(
                    onClick = {
                        counter += 1
                        openDialog = false
                    }
                ){
                    Text(
                        text = "더하기",
//                        modifier = Modifier.clickable {
//                            counter += 1
//                            openDialog = false
//                        }
                    )
                }
        }, dismissButton = {
            // 단계 3: "취소" 버튼을 만들고 다이얼로그를 끕니다.
                Button(
                    onClick = {
                        openDialog = false
                    }
                ){
                    Text(
                        text = "취소",
//                        modifier = Modifier.clickable {
//                            openDialog = false
//                        }
                    )
                }

        }, title = {
            // 단계 4: 타이틀을 만듭니다. "더하기" 정도로 해봅시다.
                Text(
                    text = "더하기"
                )
        }, text = {
            // 단계 5: 다이얼로그에서 설명할 문구를 출력합니다.
                Text(
                    text = "더하기 버튼을 누르면 카운터를 증가합니다.\n버튼을 눌러주세요."
                )
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DialogTheme {
        DialogEx()
    }
}