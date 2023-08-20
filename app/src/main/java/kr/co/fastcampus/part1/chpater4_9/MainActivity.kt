package kr.co.fastcampus.part1.chpater4_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kr.co.fastcampus.part1.chpater4_9.ui.theme.CustomDialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomDialogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CustomDialog()
                }
            }
        }
    }
}

@Composable
fun CustomDialog() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = {
            openDialog = true
        }) {
            Text("다이얼로그 열기")
        }
        Text("카운터: $counter")
    }

    /**
     * AlertDialog는 기본적인 형태를 제공해주지만,
     * Dialog는 직접 요소들을 구현해야한다...
     *
     * @Composable
    fun Dialog(
        onDismissRequest: () -> Unit,
        properties: DialogProperties = DialogProperties(),
        content: @Composable () -> Unit
    ) {
     */
    if (openDialog) {
        Dialog(
            onDismissRequest = {
            // 단계 1: 디스미스 처리를 합니다.
            openDialog = false
        }) {
            /**
             * Surface로 만들지 않으면... 의도치않게
             * dialog content 영역까지 dim 효과가 나타날 수 있다...
             * Surface로 Dialog를 감쌈으로써 기본 흰색 배경으로 Dialog를 만들 수 있다..
             */
            Surface(
                modifier = Modifier
                    .size(width = 300.dp, height = 200.dp) // 가로, 세로 길이 조정
                    .padding(16.dp) // 여백 추가
                    .clip(RoundedCornerShape(16.dp)) // 라운드 처리
            ) {
                // 단계 2: 컬럼을 만들고 설명을 적어봅시다.
                Column(
                    modifier = Modifier
                        //.background(color = Color.White)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "버튼을 클릭해 주세요.\n * +1을 누르면 값이 증가됩니다.\n * -1을 누르면 값이 감소합니다.",
                    )
                    Row(
                        modifier = Modifier.align(Alignment.End),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Button(
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                openDialog = false
                            }
                        ){
                            Text(
                                text = "취소"
                            )
                        }
                        Button(
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                openDialog = false
                                counter -= 1
                            }
                        ){
                            Text(
                                text = "-1"
                            )
                        }
                        Button(
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                openDialog = false
                                counter += 1
                            }
                        ){
                            Text(
                                text = "+1"
                            )
                        }
                    }
                }

                // 단계 3: 컬럼 안에 로우를 만들어 수평 방향으로 버튼을 배열합니다.
                // 버튼은 +1, -1, 취소로 구성하겠습니다.

                // +1은 counter를 증가시키고 -1은 감소, 취소는 다이얼로그를 닫습니다.
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomDialogTheme {
        CustomDialog()
    }
}