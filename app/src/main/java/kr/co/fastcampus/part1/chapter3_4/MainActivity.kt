package kr.co.fastcampus.part1.chapter3_4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter3_4.ui.theme.ModifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModifierTheme {
                ModifierEx(){
                    Toast.makeText(this, "Text Click!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun ModifierEx(
    TextClick : () -> Unit
) {
    Button(
        //modifier = Modifier.fillMaxSize(),
//        modifier = Modifier
//            .height(100.dp)
//            .width(200.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red,
            contentColor = Color.Yellow,
        ),
        modifier = Modifier
            .background()
            .size(height = 100.dp, width = 200.dp)
            .padding(10.dp),
        onClick = {},
        enabled = false
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            Modifier.background(Color.Cyan, RoundedCornerShape(3.dp))
        )
        Spacer(
            modifier = Modifier.size(ButtonDefaults.IconSpacing)
        )
        Text(
            text = "Search",
            Modifier
                .offset(
                    x = 51.dp
                )
                .background(Color.DarkGray, shape = RectangleShape)
                .clickable {
                    TextClick()
                }
        )
    }

    // 스텝 1: modifier에 Modifier.fillMaxSize()를 사용해봅시다.

    // 스텝 2: fillMaxSize대신 Modifier.height를 설정해봅시다.

    // 스텝 3: modifier에 height와 width를 같이 설정해봅시다.

    // 스텝 4: size에 width와 height를 인자로 넣을 수도 있습니다.

    // 스텝 5: background를 설정해봅시다. (버튼에서는 되지 않음.)
    // Button에 되지 않으면 Text나 Icon에 지정해봅시다.

    // 스탭 6: colors 파라미터에 ButtonDefaults.buttonColors를
    // 넣어보세요. backgroundColor와 contentColor 프로퍼티를
    // 설정하세요.

    // 스텝 7: Button의 modifier에 padding을 추가해봅시다.

    // 스탭 8: Button에 enabled를 false로 설정하고, Text의
    // modifier에 clickable을 넣어봅시다.

    // 스탭 9: Text의 modifier에 offset를 설정하고 x 파라미터를
    // 설정합니다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModifierTheme {
        ModifierEx(){
            //Toast.makeText(, "click", Toast.LENGTH_SHORT).show()
        }
    }
}