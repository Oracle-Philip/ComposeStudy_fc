package kr.co.fastcampus.part1.chapter4_17

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kr.co.fastcampus.part1.chapter4_17.ui.theme.EffectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EffectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EffectEx()
                }
            }
        }
    }
}

@Composable
fun EffectEx(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val scaffoldState = rememberScaffoldState()

    // 단계 1: `LaunchedEffect`을 이용해서 스낵바를 이용해 봅시다.
    // 파라미터에는 `scaffoldState.snackbarHostState`를 전달합시다.
    // "헬로 컴포즈"라고 출력합시다.
    // `LaunchedEffect`는 `CouroutineScope`를 만들기 때문에 스코프를 별도로
    // 만들 필요는 없습니다.
/*    LaunchedEffect(
        key1 = scaffoldState.snackbarHostState,
        block = {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Hello Compose, Sangpill",
            )
        }
    )*/

    // 단계 2: `DisposableEffect`를 호출하고 파리미터로 `lifecycleOwner`를
    // 전달합니다.
    DisposableEffect(key1 = lifecycleOwner){

        /**
         * Class that can receive any lifecycle change and dispatch it to the receiver.
         *
         *
         * If a class implements both this interface and
         * [androidx.lifecycle.DefaultLifecycleObserver], then
         * methods of `DefaultLifecycleObserver` will be called first, and then followed by the call
         * of [LifecycleEventObserver.onStateChanged]
         *
         *
         * If a class implements this interface and in the same time uses [OnLifecycleEvent], then
         * annotations will be ignored.
         */
        /*interface LifecycleEventObserver : LifecycleObserver {
            *//**
             * Called when a state transition event happens.
             *
             * @param source The source of the event
             * @param event The event
             *//*
            fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event)
        }*/

        //할당
        //source, event 2개의 parameter중 source 안쓸거라서 _
        val observer = LifecycleEventObserver{ _, event ->
            //참고로 Java는 SAM Single Abstract Method..
            when (event){
                Lifecycle.Event.ON_START -> {
                    Log.d("이펙트", "ONSTART")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("이펙트", "ONSTOP")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("이펙트", "ONRESUME")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("이펙트", "ONPAUSE")
                }
                else -> {
                    Log.d("이펙트", "$event")
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
        //해제
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // `LifecycleEventObserver`를 상속받고 두 상태에 대해 로그를 남깁니다.
    // `Lifecycle.Event.ON_START`, `Lifecycle.Event.ON_STOP`

    // 블록 내에서 `lifecycleOwner.lifecycle.addObserver`로 옵저버를 추가하고
    // onDispose에서 옵저버를 제거합니다.

    Scaffold(
        scaffoldState = scaffoldState
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EffectTheme {
        EffectEx()
    }
}