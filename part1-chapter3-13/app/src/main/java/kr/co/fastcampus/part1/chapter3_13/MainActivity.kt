package kr.co.fastcampus.part1.chapter3_13

import android.os.Bundle
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part1.chapter3_13.ui.theme.CheckBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckBoxTheme {
                CheckBoxEx(){ isChecked ->
                    Toast.makeText(this, "checkd $isChecked", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

// CheckBoxEx composable 함수
// CheckBoxEx2 composable 함수
// setContent

//destruction ==> 다 같은 용어(통일되지 않았다) 비구조화, 반구조화, 구조분해
//val (a, b) = listOf(2, 3)
//구조를 분해한다...
//mutableState에서
/*
interface MutableState<T> : State<T> {
    override var value: T
    operator fun component1(): T //==> getter
    operator fun component2(): (T) -> Unit //==> setter
}
 */

/**
 * 다시 렌더링 되는..
 * 상태가 바뀌는 것을
 * recomposition이라 한다...
 */

/**
 * delegated properties --> 위임된 속성
 * val checkedState = rememberSaveable { mutableStateOf(false) } --->
 * val checkedState by rememberSaveable { mutableStateOf(false) }
 * 마치 checkedState가 rememberSaveable의 프로퍼티인것처럼 사용가능
 * 따라서, mutableState의 value 그 자체로 쓸 수 있어서 checkedState.value가 아닌
 * checkState 그 자체를 사용 가능하다!
 */

@Composable
fun CheckBoxEx(
    onChecked : (Boolean) -> Unit
) {
    /**
     * 일반적인 kotlin property로는 체크박스 상태 Ui를 바꿀 수 없다
     * recompasable을 위해 상태값을 갖는 mutableStateOf와 상태값을 저장 할 수 있도록 remember를 사용해야한다.
     * 회전에도 값을 저장하기 위해서는 rememberSaveable { mutableStateOf(false) } 로 해야한다.
     */
    //val checkd = false
    //val checkedState = rememberSaveable { mutableStateOf(false) }
    //var checkedState by rememberSaveable { mutableStateOf(false) }

    //구조분해 경우
    val (getter, setter) = rememberSaveable { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        // 스텝 1: Checkbox를 만들어봅시다. checked 속성은 false
        // onCheckedChange는 비워둡시다.
        Checkbox(
            //checked = checkedState.value,
            checked = getter,
            onCheckedChange = {
            //  checkedState.value = it
            //  onChecked(checkedState.value)
            //  checked = it
            //  onChecked(checkedState)
            //  setChecked(!checked)
                setter(it)
            }
        )

        // 스텝 2: onCheckedChange에서 boolean 값 변수를 바꾸고
        // checked에서 그 값을 반영해봅시다. (잘 되지 않습니다.)

        // 스텝 3: boolean 대신 remember { mutableStateOf(false) }를
        // 사용하여 상태를 도입합시다. (value 프로퍼티를 이용해야 합니다.)

        // 스텝 4: delegated properties로 변경해봅시다.

        // 스텝 5: destruction으로 상태를 받아서 사용해봅시다.

        // Checkbox를 앞에 넣어주세요.
        Text(
            text = "프로그래머입니까?",
            modifier = Modifier.clickable {
                setter(!getter)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CheckBoxTheme {
        CheckBoxEx(){

        }
    }
}