package kr.co.fastcampus.part1.chapter4_12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kr.co.fastcampus.part1.chapter4_12.ui.theme.BottomAppBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomAppBarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BottomAppBarEx()
                }
            }
        }
    }
}

@Composable
fun BottomAppBarEx() {
    /**
     * 기본적으로 remember가 붙어있는 함수들은...
     * remember를 호출하는 Utility이다..
     */

/*    @Composable
    fun rememberScaffoldState(
        drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
        snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    ): ScaffoldState = remember {
        ScaffoldState(drawerState, snackbarHostState)
    }*/

    /**
     * !중요!
     * Composable 함수인 BottomAppBarEx()에서 LaunchedEffect 외에
     * Scaffold와 밖에 있는 code들을 channel로 연결하여
     * 호출해 갱신하게 만드는 방법도 있다!
     * 예를 들어 Button(onClick = {channel.send()}) 시에 밖에서 channel.notifiy를 하여
     * 갱신하는 방법 등이 있다!
     */

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var counter by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar(

            ) {
//                LaunchedEffect(
//                    key1 = counter,
//                    block = {
//                        coroutineScope.launch {
//                            scaffoldState.snackbarHostState.showSnackbar(
//                                message = "bottomBar Click",
//                                actionLabel = "",
//                                duration = SnackbarDuration.Short
//                            )
//                        }
//                    })
                Text(
                    text = "Hello",
                    modifier = Modifier
                        .clickable {
                            counter += 1
//                            coroutineScope.launch {
//                                /**
//                                 * showSnackbar의 기본값은 actionLabel, duration이 주어진다.
//                                 * message만 세팅해도 된다.
//                                 */
//                                scaffoldState.snackbarHostState.showSnackbar(
//                                    message = "bottomBar Click",
//                                    /*actionLabel = "",
//                                    duration = SnackbarDuration.Short*/
//                                )
//                            }
                        }
                )
                Button(
                    onClick = {
                            coroutineScope.launch {
                                /**
                                 * showSnackbar의 기본값은 actionLabel, duration이 주어진다.
                                 * message만 세팅해도 된다.
                                 */
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "bottomBar Click",
                                    /*actionLabel = "",
                                    duration = SnackbarDuration.Short*/
                                )
                            }
                    }
                ){
                    Text(
                        text = "인사하기"
                    )
                }
                Button(
                    onClick = {
                        counter += 1
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "${counter}입니다.",
                            )
                        }
                    }
                ){
                    Text(
                        text = "더하기"
                    )
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            counter -= 1
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "${counter}입니다.",
                            )
                        }
                    }
                ){
                    Text(
                        text = "빼기"
                    )
                }
               /* {
                    //@Composable invocations can only happen from the context of a @Composable function
                    LaunchedEffect(
                        key1 = counter,
                        block = {
                            coroutineScope.launch {
                                *//**
                                 * showSnackbar의 기본값은 actionLabel, duration이 주어진다.
                                 * message만 세팅해도 된다.
                                 *//*
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "bottomBar Click",
                                    *//*actionLabel = "",
                                    duration = SnackbarDuration.Short*//*
                                )
                            }
                        })
                }*/
                /*Row(

                ){
//                    Text(
//                        text = "bottomAppBar",
//                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "bottomAppBar",
                                    actionLabel = "확인",
                                    duration = SnackbarDuration.Short
                                )

                                when(result){
                                    SnackbarResult.ActionPerformed -> {}
                                    SnackbarResult.Dismissed -> {}
                                }
                            }
                        }
                    ){
                        Text(
                            text = "Show snackBar"
                        )
                    }
                    Button(
                        onClick = {
                            counter -= 1
                        }
                    ){
                        Text(
                            text = "빼기"
                        )
                    }
                    Button(
                        onClick = {
                            counter += 1
                        }
                    ){
                        Text(
                            text = "더하기"
                        )
                    }
                }*/
            }
        }
    ){
        Box(
            /**
             * Box modifier를 fillMaxSize()로 하지 않으면
             * Text()가 가운데 정렬 안된다.
             */
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                text = "Counter $counter",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        /*LaunchedEffect(
            key1 = counter,
            block = {
                coroutineScope.launch {
                    *//**
                     * showSnackbar의 기본값은 actionLabel, duration이 주어진다.
                     * message만 세팅해도 된다.
                     *//*
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "bottomBar Click",
                        *//*actionLabel = "",
                        duration = SnackbarDuration.Short*//* */
    }

    // 단계 1: `Scaffold`에 `scaffoldState`를 설정합니다.

    // 단계 2: `bottomBar` 파라미터에 `BottomAppBar`를 넣읍시다.
    // 내용은 텍스트와 버튼을 넣어 봅시다. 버튼에는 `snackBar`를
    // 연동해 메시지를 출력합니다.

    // 단계 3: 더하기와 빼기 버튼을 추가로 만들고 `MutableState`
    // 만듭시다. `Scaffold`의 `content`에 `Text`를 넣어 카운터를 출력하게
    // 합시다.
}

/*@Composable
fun showSnackBar(){
    LaunchedEffect(
        key1 = counter,
        block = {
            coroutineScope.launch {
                *//**
                 * showSnackbar의 기본값은 actionLabel, duration이 주어진다.
                 * message만 세팅해도 된다.
                 *//*
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "bottomBar Click",
                    *//*actionLabel = "",
                    duration = SnackbarDuration.Short*//*
                )
            }
        })
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomAppBarTheme {
        BottomAppBarEx()
    }
}