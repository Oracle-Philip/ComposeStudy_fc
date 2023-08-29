package kr.co.fastcampus.part1.chapter5_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.co.fastcampus.part1.chapter5_3.ui.theme.LiveDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveDataTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

class ToDoViewModel : ViewModel() {
    // 단계 2: text 상태를 라이브 데이터로 변경합니다.
    // 사용하는 측에서는 `text.observeAsState()`로 구독하세요.
//    val text = mutableStateOf("")
//    val text = MutableLiveData("")
    private val _text = MutableLiveData("")
    val text : LiveData<String> = _text

    val setText : (String) -> Unit = {
        _text.value = it
    }

    // 단계 3: toDoList 상태를 라이브 데이터로 변경합니다.
    // 모든 연산에서 List를 새로 만들어 라이브 데이터로 전달해야 합니다!!!
    // (초 비추!!)

    /**
     * Create a instance of [MutableList]<T> that is observable and can be snapshot.
     *
     * @sample androidx.compose.runtime.samples.stateListSample
     *
     * @see mutableStateOf
     * @see mutableListOf
     * @see MutableList
     * @see Snapshot.takeSnapshot
     */
    // fun <T> mutableStateListOf() = SnapshotStateList<T>()
    /**
     * SnapshotStateList 타입이다..
     *
     * 모든 연산을 새롭게 List를 만들어서 LiveData에 전달해야한다.
     * 예를 들어 항목을 하나 추가하면 List를 MutableList로 만들고 또 copy를해서
     * LiveData에 전달해야한다..
     *
     * 이유는 observeAsState의 특성 때문이다..
     * observeAsState는 DisposableEffect를 만들어서 observe를 한다..
     * */

    val toDoList = mutableStateListOf<ToDoData>()

    val onSubmit: (String) -> Unit = {
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, it))
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(text = newText)
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(done = checked)
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(i)
    }
}

@Composable
fun TopLevel(
    viewModel: ToDoViewModel = viewModel()
) {
    Scaffold {
        Column {
            ToDoInput(
                /**
                 * liveData를 observeAsState()로 하면 null이 발생할 수 있다.
                 * null 발생을 막기위해 기본값을 세팅해줘야 한다.
                 */

                /**
                 * Starts observing this [LiveData] and represents its values via [State]. Every time there would
                 * be new value posted into the [LiveData] the returned [State] will be updated causing
                 * recomposition of every [State.value] usage.
                 *
                 * The inner observer will automatically be removed when this composable disposes or the current
                 * [LifecycleOwner] moves to the [Lifecycle.State.DESTROYED] state.
                 *
                 * @sample androidx.compose.runtime.livedata.samples.LiveDataWithInitialSample
                 */
                //@Composable
                /*fun <R, T : R> LiveData<T>.observeAsState(initial: R): State<R> {
                    val lifecycleOwner = LocalLifecycleOwner.current
                    val state = remember { mutableStateOf(initial) }
                    DisposableEffect(this, lifecycleOwner) {
                        val observer = Observer<T> { state.value = it }
                        observe(lifecycleOwner, observer)
                        onDispose { removeObserver(observer) }
                    }
                    return state
                }

                DisposableEffect의 key로 this와 lifecycleOwner를 주고 있다.
                여기서 this는 liveData로.. liveData의 value가 바뀔때를 의미한다.

                따라서 list의 일부만 수정해가지고 update 불가능하다
                */



                        text = viewModel.text.observeAsState("").value,
                onTextChange =
                /**
                 * onTextChange = {}와 onTextChange = 의 차이는?
                 * onTextChange = { viewModel.setText }이 동작을 안하는 이유는?
                 * onTextChange = viewModel.setText로 할시에 viewModel.setText(it) 안해도
                 * 동작이 되는 이유는?
                 */
                    //viewModel.text.value = it

                /**
                 * mutableStateListOf -> 추가, 삭제, 갱신 -> UI가 갱신된다. 그러나 각 항목의 필드가 바뀌었을때는 갱신이 안된다.
                 */
                                                                               viewModel.setText
                                                                               //viewModel.setText(it)
                , onSubmit = viewModel.onSubmit
//                text = viewModel.text.value, onTextChange = {
//                    viewModel.text.value = it
//                }, onSubmit = viewModel.onSubmit
            )
            LazyColumn {
                items(items = viewModel.toDoList, key = { it.key }) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LiveDataTheme {
        TopLevel()
    }
}

@Composable
fun ToDoInput(
    text: String, onTextChange: (String) -> Unit, onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text, onValueChange = onTextChange, modifier = Modifier.weight(1f)
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
    LiveDataTheme {
        ToDoInput("테스트", {}, {})
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp), elevation = 8.dp
    ) {
        Crossfade(
            targetState = isEditing,
        ) {
            when (it) {
                false -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = toDoData.text, modifier = Modifier.weight(1f)
                        )
                        Text("완료")
                        Checkbox(checked = toDoData.done, onCheckedChange = { checked ->
                            onToggle(toDoData.key, checked)
                        })
                        Button(onClick = { isEditing = true }) {
                            Text("수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = { onDelete(toDoData.key) }) {
                            Text("삭제")
                        }
                    }
                }
                true -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (text, setText) = remember { mutableStateOf(toDoData.text) }
                        OutlinedTextField(
                            value = text, onValueChange = setText, modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            isEditing = false
                            onEdit(toDoData.key, text)
                        }) {
                            Text("완료")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    LiveDataTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

data class ToDoData(
    val key: Int, val text: String, val done: Boolean = false
)