package kr.co.fastcampus.part1.chapter4_13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter4_13.ui.theme.StateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    PyeongToSquareMeter()
                }
            }
        }
    }
}

/**
 * 심지어...(강의 마지막 내용)
 *     var pyeong by rememberSaveable {
mutableStateOf("23")
}

var squaremeterby by rememberSaveable {
mutableStateOf((23 * 3.306).toString())
} 와 같은 상태를
 아예 viewModel로 보내 관리 할 수 도 있다.

 state를 관리하는 code만 수정이 필요하고
 stateLess를 다루는 code는 수정이 불필요하다.

 ==> 확장성 있게 코드를 구현할 수 있다.
 */

@Composable
fun PyeongToSquareMeter() {

    /**
     * remember는 Compasable을 가져오기 위해서 cache를 사용한다.
     * 그 cache가 configuration이 바뀔때는 유지되지 않는다.
     * 즉, lotation 때문에 바뀔 수 있는데, 그렇다고 모든 값을 rememberSaveable로 하는건
     * 저장 공간상 좋은 방법은 아니다
     */

/*    @Composable
    inline fun <T> remember(calculation: @DisallowComposableCalls () -> T): T =
        currentComposer.cache(false, calculation)*/

    //구조분해방식..
/*    var (pyeong, setValue) = remember {
        mutableStateOf("23")
    }*/

    //by 위임방식
    var pyeong by rememberSaveable {
        mutableStateOf("23")
    }

    var squaremeterby by rememberSaveable {
        mutableStateOf((23 * 3.306).toString())
    }

//    var (squaremeter, setSquaremeter) = rememberSaveable {
//        mutableStateOf((23 * 3.306).toString())
//    }


/*    *//**
     * Creates a [ScaffoldState] with the default animation clock and memoizes it.
     *
     * @param drawerState the drawer state
     * @param snackbarHostState instance of [SnackbarHostState] to be used to show [Snackbar]s
     * inside of the [Scaffold]
     *//*
    @Composable
    fun rememberScaffoldState(
        drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
        snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    ): ScaffoldState = remember {
        ScaffoldState(drawerState, snackbarHostState)
    }*/

    /**
     * 참고
     * rememberScaffoldState()는 내부에서 remember를 처리하고 있다.
     */

    //rememberScaffoldState()

    /**
     * 일반적으로, 상태정보를 아래와 같이 OutlinedTextField까지
     * 내려보내지 않는다... 즉 아래방식은 권장방식이 아니다!
     * 상태전달 범위를 좁혀야 한다.
     *
     * !중요
     * 상태전달 범위를 좁히기 위해서 상태를 윗단으로 끌어올리는 것을
     * State Hoisting이라 한다.
     *
     * 정리를 해보자면, 이번 강의를 통해
     * 상태를 관리하는 Composable PyeongToSquareMeter()과
     * Ui를 관리하는 Composable PyeongToSquareMeterStateLess()로 나뉘어진 것이다.
     * ==> Ui는 StateLess하게 만드는게 좋다.
     */

    // 단계 1: remember를 이용해 상태를 만들고 평 값을 입력하면
    // 제곱미터가 출력되도록 화면을 구성하시오.
    // 평을 제곱미터로 바꾸기 위해서는 3.306을 곱하면 됩니다.
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = {
                if(it.isBlank()){
                    pyeong = ""
                    squaremeterby = ""
                    return@OutlinedTextField
                }
                /**
                 * Null값이 들어오면 강제종료 되게 처리
                 */
                val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
                //setValue.toString()
                            pyeong = it
                squaremeterby = (numericValue * 3.306).toString()
                //squaremeterby = "${(pyeong.toDouble() * 3.306)}"
            },
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squaremeterby,
            onValueChange = {
                squaremeterby = it
            },
            label = {
                Text("제곱미터")
            }
        )
    }

    // 단계 2: `Composable` 함수를 만들고 `Column`의 항목들을 옮기세요.
    // 단 상태는 옮기지 말아야 합니다.

    // 파라미터는 아래와 같이 구성합니다.
    // `pyeong: String, squareMeter: String, onPyeongChange: (String) -> Unit`
    PyeongToSquareMeterStateLess(pyeong, squaremeterby){
        //onPyeongChange : (String) -> Unit는 후행람다로 사용한다.
        if(it.isBlank()){
            pyeong = ""
            squaremeterby = ""
            return@PyeongToSquareMeterStateLess
        }

        val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateLess
        //setValue.toString()
        pyeong = it
        squaremeterby = (numericValue * 3.306).toString()
    }
}

/**
 * 상태가 없는..
 * pyeong이 바뀔때 호출되는 onPyeongChange : (String) -> Unit 컬백함수이다..
 * State를 가져오지 못하게 했으며 State를 바꿀 책임을 위 PyeongToSquareMeter 남겨두었다.
 * 즉, PyeongToSquareMeterStateLess는 상태를 바꾸지 못한다.
 *
 * 즉, 위 코드에서는 onValueChange = { }에서 상태정보를 바꾸었지만,
 * 아래 onValueChange = {}에서는 컬백함수 onPyeongChange : (String) -> Unit에게 위임한다.
 */

@Composable
fun PyeongToSquareMeterStateLess(
    pyeong : String,
    squareMeter : String,
    onPyeongChange : (String) -> Unit,
){
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChange,
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squareMeter,
            onValueChange = {},
            label = {
                Text("제곱미터")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateTheme {
        PyeongToSquareMeter()
    }
}