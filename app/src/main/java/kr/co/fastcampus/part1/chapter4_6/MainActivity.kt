package kr.co.fastcampus.part1.chapter4_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import kotlinx.coroutines.selects.selectUnbiased
import kr.co.fastcampus.part1.chapter4_6.ui.theme.Card2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Card2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        CardEx(cardData)
                        CardEx(cardData)
                        CardEx(cardData)
                    }
                }
            }
        }
    }
}

@Composable
fun CardEx(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)

    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(4.dp),
    ) {
        // 단계 1: 아래의 Row 레이아웃을 ConstraintLayout로 바꾸어 봅시다.
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            //.height(100.dp)
        ) {
            val (imageIcon, title, description) = createRefs()

            //createHorizontalChain(imageIcon, spacer, column_Text, chainStyle = ChainStyle.SpreadInside)

            AsyncImage(
                model = cardData.imageUri,
                contentDescription = cardData.imageDescription,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolderColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .constrainAs(imageIcon){
                        //centerVerticallyTo(parent)
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                        start.linkTo(parent.start)
                        linkTo(parent.top, parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
                }
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(40.dp)
            )

            //createVerticalChain(title, description)
            //val barrier = createEndBarrier(title, description)
            val barrier = createEndBarrier(imageIcon)

            Text(
                text = cardData.author,
                modifier = Modifier
                    .constrainAs(title){
                        //start.linkTo(imageIcon.end, margin = 8.dp)
                        //start.linkTo(barrier, margin = 8.dp)
                        linkTo(
                            imageIcon.end,
                            parent.end,
//                            topMargin = 20.dp,
                            startMargin = 8.dp,
                            endMargin = 8.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )
            Text(
                text = cardData.description,
                modifier = Modifier
                    .constrainAs(description){
                        //start.linkTo(imageIcon.end, margin = 8.dp)
                        linkTo(
                            imageIcon.end,
                            parent.end,
                            startMargin = 8.dp,
                            endMargin = 8.dp,
                        )
                        //제약조건만큼... width 설정..
                        width = Dimension.fillToConstraints
                    }
            )

            /**
             * 참고로 Chain Style packed는 밖 영역은 동일하게 하고
             * 안에 영역은 붙어있게 한다.
             */
            /**
             * title과 description에 위 그리고 아래
             * 공백을 주기위해
             * reference chain을 만들자
             */
            val chain = createVerticalChain(
                title,
                description,
                chainStyle = ChainStyle.Packed
            )

            constrain(chain){
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }

            /**
             * 위와 같이 constraint(chain){}을 통해별도 마진을 주는 이유는..
             * 위 Text modifier를 통해 margin을 줄 수 있지만
             * 이미 Text title과 description이 vertical-chain으로 묶여져있어서
             * Text에 top 혹은 Bottom margin을 직접주게 되면 vertical한 margin은 밖에 있는 margin에 껴지게 되므로...
             */


//            Spacer(
//                modifier = Modifier
//                    .size(8.dp)
////                    .constrainAs(spacer){
////
////                }
//            )
//            Spacer(modifier = Modifier.size(8.dp))
//            Column(
//                modifier = Modifier
////                    .constrainAs(column_Text){
////
////                }
//            ) {
//                Text(text = cardData.author)
//                Text(text = cardData.description)
//            }
        }

//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(8.dp)
//        ) {
//            AsyncImage(
//                model = cardData.imageUri,
//                contentDescription = cardData.imageDescription,
//                contentScale = ContentScale.Crop,
//                placeholder = ColorPainter(color = placeHolderColor),
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(40.dp)
//            )
//            Spacer(modifier = Modifier.size(8.dp))
//            Column {
//                Text(text = cardData.author)
//                Text(text = cardData.description)
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Card2Theme {
        CardEx(cardData)
    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

val cardData = CardData(
    imageUri = "https://i.pravatar.cc/150?img=9",
    imageDescription = "엔텔로프 캐년",
    author = "Dalinaum",
    description = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다."
)