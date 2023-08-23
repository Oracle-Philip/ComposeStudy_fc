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

    // 단계 1: remember를 이용해 상태를 만들고 평 값을 입력하면
    // 제곱미터가 출력되도록 화면을 구성하시오.
    // 평을 제곱미터로 바꾸기 위해서는 3.306을 곱하면 됩니다.
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = {
                //setValue.toString()
                            pyeong = it
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateTheme {
        PyeongToSquareMeter()
    }
}