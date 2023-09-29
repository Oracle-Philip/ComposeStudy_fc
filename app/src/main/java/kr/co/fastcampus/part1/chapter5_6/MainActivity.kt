package kr.co.fastcampus.part1.chapter5_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.fastcampus.part1.chapter5_6.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyNav()
                }
            }
        }
    }
}

// 단계 2: `navController` 파라미터를 만듭니다.
// `NavHostController` 타입에 기본 값은 `rememberNavController()`
/**
 * MyNav()만 사용해도 NavHostController 타입에
 * navController가 자동으로 할당된다.
 */
@Composable
fun MyNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // 단계 3: `NavHost`를 만듭니다.
    // `navController`, `"Home"`, `modifier`를 전달합시다.
    NavHost(navController, "Home", modifier = modifier){
        /**
         * popUpTo("Home")를 통해서 navController로 라우팅을 하면서
         * stack에 쌓인 것들을 다 지우고 백버튼 누를시 Home으로 이동하게 할 수 있다.
         * 예) Home -> Office -> Playground에서
         * stack에서 해당요소 Home을 찾아서 그 사이에 있는 stack을 없애 버린다.
         *
         *  만약에 popUpTo의 후행람다로 inclusive = true를 할 경우
         *  Home도 포함해서 없애버리겠다는 의미이다.
         *  앱 종료전까지 백버튼으로는 Home을 나타낼 수 없다.
         *
         *  inclusive는 언제 사용할까?
         *  예) Home -> Login -> Mail에서
         *  popUpTo("Login") { inclusive = true }
         *  Home -> Mail로 stack 구성이 가능하다.
         */

        /**
         * 그 외에도 launchSingleTop 옵션이 있다.
         * launchSingleTop을 통해 Home 화면에 Home으로 이동하는 버튼을 누르고
         * 백버튼을 누르면 stack에 하나밖에 쌓여있지 않아 종료됨을 알 수 있다.
         * 최상단에 떠있으면 새로 띄우지 않는 것이다.
         */

        /**
         * Button(onClick = {
            navController.navigate("Argument/fastcampus"){
                launchSingleTop = true
            }
            })
            {
                Text(
                text = "Fastcampus 아이디로 연결")
            }
         * composable("Argument/{userId}"){ backStackEntry ->
            val userId = backStackEntry.arguments?.get("userId")
            Text("userId : $userId")
            }
         */
        composable("Home"){
            Column {
                Text("Home")
                Button(onClick = {
                    navController.navigate("Playground"){
                        popUpTo("Home"){
                            inclusive = true
                        }
                    }
                }){
                    Text(
                        text = "Playground으로 이동"
                    )
                }
                Button(onClick = {
                    navController.navigate("Office"){
                        popUpTo("Home"){
                            inclusive = true
                        }
                    }
                }){
                    Text(
                        text = "Office로 이동"
                    )
                }
                Button(onClick = {
                    navController.navigate("Home"){
                        launchSingleTop = true
                    }
                }){
                    Text(
                        text = "Home로 이동"
                    )
                }
                Button(onClick = {
                    navController.navigate("Argument/fastcampus"){
                        launchSingleTop = true
                    }
                }){
                    Text(
                        text = "Fastcampus 아이디로 연결"
                    )
                }
            }
        }
        composable("Argument/{userId}"){ backStackEntry ->
            val userId = backStackEntry.arguments?.get("userId")
            Text("userId : $userId")
        }
        composable("Office"){
            Column {
                Text(
                    text = "Office"
                )
                Button(onClick = {
                    navController.navigate("Playground"){
                        popUpTo("Home"){
                            inclusive = true
                        }
                    }
                }){
                    Text(
                        text = "Playground으로 이동"
                    )
                }
                Button(onClick = {
                    navController.navigate("Home"){
                        popUpTo("Home"){
                            inclusive = true
                        }
                    }
                }){
                    Text(
                        text = "Home으로 이동"
                    )
                }
            }
        }
        composable("Playground"){
            Column {
                Text(
                    text = "Playground"
                )
                Button(onClick = {
                    navController.navigate("Office"){
                        popUpTo("Home"){
                            inclusive = true
                        }
                    }
                }){
                    Text(
                        text = "Office로 이동"
                    )
                }
                Button(onClick = {
                    navController.navigate("Home"){
                        popUpTo("Home"){
                            inclusive = true
                        }
                    }
                }){
                    Text(
                        text = "Home으로 이동"
                    )
                }
            }
        }
    }

    // 단계 4: `composable("Home")`를 만들고 안에 "Office로 이동" 버튼을
    // 만듭니다.

    // 단계 5: `composable("Office")`를 만들고 텍스트를 넣어봅시다.
    // "Office로 이동" 버튼에 `navController.navigate("Office")`를
    // 넣어줍니다.

    // 단계 6: `Playground`를 만들고 `Home`, `Office`, `Playgorund`를
    // 서로 연결합니다.

    // 단계 7: Home, Office, Playgorund, Home, Office, Playgorund
    // 순으로 이동한 후 백버튼을 계속 눌러서 이동을 확인해봅시다.

    // 단계 8: navigate에 후행 람다로 `popUpTo("Home")`을 넣고 스택 이동을
    // 확인해봅니다.

    // 단계 9: `popUpTo`의 후행 람다에 `inclusive = true`를 넣어보고
    // 스택 이동을 확인해봅시다.

    // 단계 10: `Home`에서 `Home`으로 가는 버튼을 만들고
    // `launchSingleTop = true`을 설정해보세요.

    // 단계 11: "Argument/{userId}"를 라우트로 받는
    // composable을 만드세요.
    // `arguments?.get("userId")`을 받아 출력하세요.
    // "Argument/fastcampus"로 이동하는 버튼을 만들어보세요.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationTheme {
        MyNav()
    }
}