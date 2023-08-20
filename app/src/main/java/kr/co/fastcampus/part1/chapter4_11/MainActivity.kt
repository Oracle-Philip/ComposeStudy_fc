package kr.co.fastcampus.part1.chapter4_11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.withLock
import kr.co.fastcampus.part1.chapter4_11.ui.theme.SnackbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackbarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SnackbarEx()
                }
            }
        }
    }
}

@Composable
fun SnackbarEx() {
    var counter by remember { mutableStateOf(0) }


    // 단계 3: couroutineScope를 만듭시다.
    // `rememberCoroutineScope`를 사용합니다.
    /**
     * Composable 안에서 쓸것이기 때문에 rememberCoroutineScope를 사용한다.
     * Snackbar를 사용하는데 coroutineScope를 사용하는 이유는...
     * Snackbar가.. suspend function이기 때문이다...
     */
    val coroutineScope = rememberCoroutineScope()

    // 단계 1: scaffoldState를 만들고 Scaffold에 설정합시다.
    // scaffoldState를 만들기 위해 `rememberScaffoldState`를 사용합니다.

    /**
     * rememberScaffoldState()는 scaffoldState를 만들고 기억을 한다.
     * remember와 scaffoldState가 합쳐진 메소드이다.
     * 이를 통해 아래 Scaffold의 인자인 scaffoldState에 전달 할 수 있다!
     * 참고) 아래 Scaffold에서 scaffoldState: ScaffoldState = rememberScaffoldState(), 이 부분이다!
     */
   /* @Composable
    fun Scaffold(
        modifier: Modifier = Modifier,
        scaffoldState: ScaffoldState = rememberScaffoldState(),
        topBar: @Composable () -> Unit = {},
        bottomBar: @Composable () -> Unit = {},
        snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
        floatingActionButton: @Composable () -> Unit = {},
        floatingActionButtonPosition: FabPosition = FabPosition.End,
        isFloatingActionButtonDocked: Boolean = false,
        drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
        drawerGesturesEnabled: Boolean = true,
        drawerShape: Shape = MaterialTheme.shapes.large,
        drawerElevation: Dp = DrawerDefaults.Elevation,
        drawerBackgroundColor: Color = MaterialTheme.colors.surface,
        drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
        drawerScrimColor: Color = DrawerDefaults.scrimColor,
        backgroundColor: Color = MaterialTheme.colors.background,
        contentColor: Color = contentColorFor(backgroundColor),
        content: @Composable (PaddingValues) -> Unit
    ) {*/
    val scaffoldState = rememberScaffoldState()

    /**
     * SnackbarHostState를 직접 만드려는 경우..
     * SnackbarHostState() 메소드를 통해 reference를 만들고 전달도 가능하다!
     */
    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState2 = rememberScaffoldState(snackbarHostState = snackbarHostState)

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        /**
         * !중요 -- SnackBar는 composition이 아니거나 LaunchedEffect에서 사용해야한다!
         * Button 컴포저블의 onClick이 아닌 컴포저블, coroutinScope 영역내 snackBar를 사용하려하면
         * Calls to launch should happen inside a LaunchedEffect and not composition 와 같은 에러가 뜬다.
         * 따라서 컴포저블에서 실행하고 싶으면 LaunchedEffect로 묶어줘야한다.
         * 아래 코드의 의미는 key값인 scaffoldState.snackbarHostState가 state 바뀌기 전까지는
         * showSnackbar()를 호출하지 않는다는 의미이다.
         *
         * 기본적으로, 다른 쓰레드에서 실행되는 여러 컴포저블이 있는 경우 side Effect가 발생될 수 있는 컴포저블은
         * LaunchedEffect 안에서 사용한다.
         *
         * LaunchedEffect는 처음 뜰때만 호출을 해주고 상태가 바뀌지 전까지는 호출하지 않는다는 특징을 갖고 있다.
         */

        /**
         * LaunchedEffect가 컴포지션에 진입하면 컴포지션의 CoroutineContext로 블록을 시작합니다. LaunchedEffect가 구성을 벗어나면 코루틴이 취소됩니다.
        하나 이상의 키 매개변수 없이 LaunchedEffect를 호출하는 것은 오류입니다.

        LaunchedEffect가 컴포지션에 진입하면 컴포지션의 CoroutineContext로 블록을 시작합니다. LaunchedEffect가 다른 key1로 재구성되면 코루틴이 취소되고 다시 시작됩니다.
        LaunchedEffect가 구성을 벗어나면 코루틴이 취소됩니다.
        이 함수는 key1에 전달된 MutableState에 콜백 데이터를 저장하는 방식으로 콜백 이벤트에 대한 응답으로 진행 중인 작업을
        (재)실행하는 데 사용해서는 안 됩니다. 대신, 이벤트 콜백에 대한 응답으로 컴포지션으로 범위가 지정된 진행 중인 작업을 시작하는 데
        사용할 수 있는 CoroutineScope를 얻으려면 rememberCoroutineScope를 참조하세요.
         */
        LaunchedEffect(
            key1 = scaffoldState.snackbarHostState,
            block = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "나는 LaunchedEffect에서 호출되는 Snackbar 입니다 :)",
                        actionLabel = null,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        )


        // 단계 2: "더하기" 버튼을 만들어 봅시다.
        // action에서 counter를 증가시킵시다.
        Button(
            onClick = {
                counter += 1
                coroutineScope.launch {
                    /*State for Scaffold composable component.
                Contains basic screen state, e.g. Drawer configuration, as well as sizes of components after layout has happened
                    Params:
                    drawerState - the drawer state
                    snackbarHostState - instance of SnackbarHostState to be used to show Snackbars inside of the Scaffold*/
/*                    @Stable
                    class ScaffoldState(
                        val drawerState: DrawerState,
                        val snackbarHostState: SnackbarHostState
                    )*/

                    /**
                     * 위 설명과 같이.. SnackbarHostState타입에 snackbarHostState는 Scaffold내에서 snackBar를 나타내주는데 사용된다.
                     */

                    /*
                    그리고 showSnackbar는 suspend 함수이며, 기본파라미터는 message, actionLabel, duration이 있다.
                    suspend fun showSnackbar(
                        message: String,
                        actionLabel: String? = null,
                        duration: SnackbarDuration = SnackbarDuration.Short
                    ): SnackbarResult = mutex.withLock {
                        try {
                            return suspendCancellableCoroutine { continuation ->
                                currentSnackbarData = SnackbarHostState.SnackbarDataImpl(message, actionLabel, duration, continuation)
                            }
                        } finally {
                            currentSnackbarData = null
                        }
                    }*/

//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = "카운터는  ${counter}입니다.",
//                        actionLabel = "닫기",
//                        duration = SnackbarDuration.Short
//                    )

                    /*enum class SnackbarDuration {
                        *//**
                         * Show the Snackbar for a short period of time
                         *//*
                        Short,

                        *//**
                         * Show the Snackbar for a long period of time
                         *//*
                        Long,

                        *//**
                         * Show the Snackbar indefinitely until explicitly dismissed or action is clicked
                         *//*
                        Indefinite
                    }*/

                    val resulut = scaffoldState.snackbarHostState.showSnackbar(
                        message = "카운터는  ${counter}입니다.",
                        actionLabel = "5 증가시키기!",
                        duration = SnackbarDuration.Short
                    )

                    when (resulut){
                        SnackbarResult.Dismissed -> {

                        }
                        SnackbarResult.ActionPerformed -> {
                            counter += 5
                        }
                    }
                }
            }
        ){
            Text(
                text = "더하기"
            )
        }

        // 단계 4: 버튼의 onClick에서 `coroutineScope.launch`를
        // 사용합니다.

        // 단계 5: 스낵바를 사용하기 위해 `scaffoldState.snackbarHostState.showSnackbar`
        // 사용합니다.

        // `message`에 카운터를 출력합시다.
        // `actionLabel`를 "닫기"로 지정합시다.
        // `duration`에 `SnackbarDuration.Short`를 사용합니다.

        //Snackbar
        //SnackbarHost
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnackbarTheme {
        SnackbarEx()
    }
}