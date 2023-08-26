package kr.co.fastcampus.part1.chapter4_18

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter4_18.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

@Composable
fun TopLevel() {
    val (text, setText) = remember { mutableStateOf("") }
    val toDoList = remember { mutableStateListOf<ToDoData>() }

    // 단계 4: `onSubmit`, `onEdit`, `onToggle`, `onDelete`를
    // 만들어 `ToDo`에 연결합니다.


    Scaffold {
        Column {
            ToDoInput(text, setText, {})
            // 단계 3: `LazyColumn`으로 `toDoList`를 표시합시다.
            // `key`를 `toDoData`의 `key`를 사용합니다.
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoTheme {
        TopLevel()
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    ToDoTheme {
        ToDoInput("테스트", {}, {})
    }
}

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp
    ) {
        // 단계 1: `Row`를 만들고 `toDoData.text`를 출력하고
        // 완료를 체크하는 체크박스, 수정 버튼, 삭제 버튼을 만드세요.
        /*Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = toDoData.text,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "완료"
            )
            Checkbox(
                checked = toDoData.done,
                onCheckedChange = {
                    checked -> onToggle(toDoData.key, checked)
                }
            )
            Button(
                onClick = {}*//*onToggle(
                    key = 1, true
                )*//*
            ) {
                Text(
                    text = "수정버튼"
                )
            }
            Spacer(Modifier.size(4.dp))
            Button(
                onClick = {}*//*onDelete(

                )*//*
            ) {
                Text(
                    text = "삭제버튼"
                )
            }
        }*/

        Crossfade(targetState = isEditing) {
            when (it){
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "완료"
                        )
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = {
                                    checked -> onToggle(toDoData.key, checked)
                            }
                        )
                        Button(
                            onClick = {
                                isEditing = true
                            }/*onToggle(
                    key = 1, true
                )*/
                        ) {
                            Text(
                                text = "수정버튼"
                            )
                        }
                        Spacer(Modifier.size(4.dp))
                        Button(
                            onClick = {}/*onDelete(

                )*/
                        ) {
                            Text(
                                text = "삭제버튼"
                            )
                        }
                    }
                }
                true -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        OutlinedTextField(
                            value = toDoData.text,
                            onValueChange = {},
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.size(4.dp))
                        Button(
                            onClick = {}
                        ){
                            Text("완료")
                        }
                    }
                }
            }
            
        }

        // 단계 2: `Crossfade`를 통해 `isEditing`을 따라 다른
        // UI를 보여줍니다. `OutlinedTextField`와 `Button을
        // 넣어봅시다.
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ToDoTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)