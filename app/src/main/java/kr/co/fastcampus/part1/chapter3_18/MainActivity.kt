package kr.co.fastcampus.part1.chapter3_18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter3_18.R.drawable
import kr.co.fastcampus.part1.chapter3_18.ui.theme.CatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    CatalogEx(items)
                }
            }
        }
    }
}

@Composable
fun Item(itemData: ItemData) {
    // 스텝 1: catalog1.png를 참고해 카드 레이아웃을 완성하세요.
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ){
            Image(
                //painter = painterResource(id = R.drawable.a1),
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.title
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            /**
             *Typography를 생성하는 생성자. 이 생성자에 정의된 스타일 유형에 대한 자세한 내용은 Typography의 속성 설명서를 참조하세요.
            매개변수:
            defaultFontFamily - 이 생성자에 제공된 TextStyles에 사용할 기본 FontFamily입니다. TextStyle의 FontFamily가 null인 경우 이 기본값이 사용됩니다.
            h1 - h1은 짧고 중요한 텍스트 또는 숫자를 위해 예약된 가장 큰 헤드라인입니다.
            h2 - h2는 두 번째로 큰 헤드라인으로 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
            h3 - h3은 세 번째로 큰 헤드라인으로 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
            h4 - h4는 짧고 중요한 텍스트 또는 숫자를 위해 예약된 네 번째로 큰 헤드라인입니다.
            h5 - h5는 다섯 번째로 큰 헤드라인으로 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
            h6 - h6은 짧고 중요한 텍스트나 숫자를 위해 예약된 여섯 번째로 큰 헤드라인입니다.
            subtitle1 - subtitle1은 가장 큰 자막이며 일반적으로 길이가 짧은 중간 강조 텍스트용으로 예약되어 있습니다.
            subtitle2 - subtitle2는 가장 작은 자막이며 일반적으로 길이가 짧은 중간 강조 텍스트용으로 예약되어 있습니다.
            body1 - body1은 가장 큰 본문이며 일반적으로 작은 텍스트 크기에 잘 작동하므로 긴 형식의 쓰기에 사용됩니다.
            body2 - body2는 가장 작은 본문이며 일반적으로 작은 텍스트 크기에 잘 작동하므로 긴 형식의 쓰기에 사용됩니다.
            버튼 - 버튼 텍스트는 다양한 유형의 버튼(예: 텍스트, 윤곽선 및 포함된 버튼)과 탭, 대화 상자 및 카드에서 사용되는 클릭 유도 문안입니다.
            캡션 - 캡션은 가장 작은 글꼴 크기 중 하나입니다. 이미지에 주석을 달거나 헤드라인을 소개하는 데 드물게 사용됩니다.
            overline - overline은 가장 작은 글꼴 크기 중 하나입니다. 이미지에 주석을 달거나 헤드라인을 소개하는 데 드물게 사용됩니다.
             *
             * @Immutable
            class Typography internal constructor(
            val h1: TextStyle,
            val h2: TextStyle,
            val h3: TextStyle,
            val h4: TextStyle,
            val h5: TextStyle,
            val h6: TextStyle,
            val subtitle1: TextStyle,
            val subtitle2: TextStyle,
            val body1: TextStyle,
            val body2: TextStyle,
            val button: TextStyle,
            val caption: TextStyle,
            val overline: TextStyle
            ) {

            Constructor to create a Typography. For information on the types of style defined in this constructor, see the property documentation for Typography.
            Params:
            defaultFontFamily - the default FontFamily to be used for TextStyles provided in this constructor. This default will be used if the FontFamily on the TextStyle is null.
            h1 - h1 is the largest headline, reserved for short, important text or numerals.
            h2 - h2 is the second largest headline, reserved for short, important text or numerals.
            h3 - h3 is the third largest headline, reserved for short, important text or numerals.
            h4 - h4 is the fourth largest headline, reserved for short, important text or numerals.
            h5 - h5 is the fifth largest headline, reserved for short, important text or numerals.
            h6 - h6 is the sixth largest headline, reserved for short, important text or numerals.
            subtitle1 - subtitle1 is the largest subtitle, and is typically reserved for medium-emphasis text that is shorter in length.
            subtitle2 - subtitle2 is the smallest subtitle, and is typically reserved for medium-emphasis text that is shorter in length.
            body1 - body1 is the largest body, and is typically used for long-form writing as it works well for small text sizes.
            body2 - body2 is the smallest body, and is typically used for long-form writing as it works well for small text sizes.
            button - button text is a call to action used in different types of buttons (such as text, outlined and contained buttons) and in tabs, dialogs, and cards.
            caption - caption is one of the smallest font sizes. It is used sparingly to annotate imagery or to introduce a headline.
            overline - overline is one of the smallest font sizes. It is used sparingly to annotate imagery or to introduce a headline.
             */
            Text(
                text = itemData.title,
                style = MaterialTheme.typography.h4
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )
            
            Text(
                text = itemData.description
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    CatalogTheme {
        Item(
            ItemData(
                imageId = drawable.a1,
                title = "해변 놀이 공원",
                description = "해변 놀이 공원 설명입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
    }
}

@Composable
fun CatalogEx(itemList: List<ItemData>) {
    /**
     * card가 여러개인 카탈로그 형태로 만들기 위해
     * LazyColumn 컴포저블을 사용한다.
     * Android recyclerView나 listView를 대체하기 위해 사용한다.
     *
     * @Composable
    fun LazyColumn(
        modifier: Modifier = Modifier,
        state: LazyListState = rememberLazyListState(),
        contentPadding: PaddingValues = PaddingValues(0.dp),
        reverseLayout: Boolean = false,
        verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
        content: LazyListScope.() -> Unit
    ) {
    LazyList(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        flingBehavior = flingBehavior,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        isVertical = true,
        reverseLayout = reverseLayout,
        content = content
        )
    }
     */
    LazyColumn {//Lazy가 붙었다는 것은 필요할때만 보여주겠다는 의미..
        // 스텝 2: `items(itemList)`를 이용해 Item을 반복해서
        // 컬럼에 추가하세요.

        /**
         * 수동설정방법 -> header, footer가 필요한 경우
         * 직접 lazyColumn에 넣어준다.
         *
         * 그게 아니라 여러개 item들이 반복해서 나와야 하는 경우는
         * items(items: List<T> =  )를 이용해서 레이아웃을 해주면 된다.
         */
        item {
            Item(itemList[0])
        }
        item {
            Item(itemList[0])
        }

        items(itemList){
            //LazyItemScope 영역이다..
            /**
             * @LazyScopeMarker
            interface LazyListScope {

                fun items(
                count: Int,
                key: ((index: Int) -> Any)? = null,
                itemContent: @Composable LazyItemScope.(index: Int) -> Unit
                )
             */
            item ->
            Item(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatalogTheme {
        CatalogEx(items)
    }
}

data class ItemData(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String,
)

val items = listOf(
    ItemData(
        imageId = drawable.a1,
        title = "해변 놀이 공원",
        description = "해변 놀이 공원 설명입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a2,
        title = "캐년",
        description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a3,
        title = "워터월드",
        description = "워터월드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a4,
        title = "미국의 캐년",
        description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a5,
        title = "라스베가스",
        description = "라스베가스입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a6,
        title = "호르슈 밴드",
        description = "호르슈 밴드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a7,
        title = "미국의 길",
        description = "미국의 길입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a8,
        title = "엔텔로프 캐년",
        description = "엔텔로프 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = drawable.a9,
        title = "그랜드 캐년",
        description = "그랜드 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
)