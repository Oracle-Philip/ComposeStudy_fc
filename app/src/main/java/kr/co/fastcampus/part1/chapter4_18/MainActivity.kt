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
    //MutableStateList가 추가, 삭제, 변경되었을 때만 UI가 갱신된다.
    //항목 하나의 값을 바꾸는 것보다 항목 자체를 바꾸는게 더 좋다.

    val onSubmit : (String) -> Unit = { text ->
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, text))
        setText("")
    }

    // 단계 4: `onSubmit`, `onEdit`, `onToggle`, `onDelete`를
    // 만들어 `ToDo`에 연결합니다.

    val onToggle : (Int, Boolean) -> Unit = { key, checked ->
        val i = toDoList.indexOfFirst { it.key == key }
        //toDoList.get(i).done = checked
        toDoList[i] = toDoList[i].copy ( done = checked )
    }

    val onDelete : (Int) -> Unit = { key ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(i)
    }

    //ToDo객체의 필드를 불변으로 바꾼 이유는
    //필드값을 바꾼다고 해서 Ui가 바뀌지 않기 때문이다.
    //꼼수로 ToDo의 일부 필드 타입을 State형으로 바꾸면 되지만..
    //좋은 방법이 아니다..
    val onEdit : (Int, String) -> Unit = { key, editText ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy( text = editText)
    }

    Scaffold {
        Column {
            ToDoInput(
                text = text,
                onTextChange = setText,
                onSubmit = onSubmit
            )
            // 단계 3: `LazyColumn`으로 `toDoList`를 표시합시다.
            // `key`를 `toDoData`의 `key`를 사용합니다.


            /**
             * !중요 <효과적인 렌더링을 위해서>
             * LazyColumn의 퍼포먼스를 위해서...
             * key를 설정해주자...
             * JetPack Compose는 혼란을 겪을 수 있다.
             * 어떤 항목이 재사용 가능한지 아닌지를 알 수 없어서..
             * 그래서 key를 세팅해주자.. ==> key가 같으면 재사용 할 수 있다고 알려주는 것이다..
             */
            LazyColumn {
                items(toDoList, key = {
                    //it 즉 ToDoData의 key를 가지고 렌더링을 위한 key로 사용한다..
                    it.key
                }){toDoData ->
                    //Text(toDoData.text)

                    //되도록 keyword Parameter로 바꾸자..
                    ToDo(
                        toDoData = toDoData,
                        onToggle = onToggle,
                        onDelete = onDelete,
                        onEdit = onEdit
                    )
                }
            }
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
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
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
                                //onEdit(toDoData.key, "123")
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
                            onClick = {
                                onDelete(toDoData.key)
                            }/*onDelete(

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
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ){
                        var newText by remember {
                            mutableStateOf(toDoData.text)
                        }

//                        var (newText, setNewText) = remember {
//                            mutableStateOf(toDoData.text)
//                        }

                        OutlinedTextField(
                            value = newText,
                            onValueChange = //setNewText
                            {
                                newText = it

                            },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.size(4.dp))
                        Button(
                            onClick = {
                                onEdit(toDoData.key, newText)
                                isEditing = false
                            }
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

//immutable
data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)