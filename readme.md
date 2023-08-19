# Typography

- Typography를 생성하는 생성자입니다. 이 생성자에 정의된 스타일 유형에 대한 자세한 내용은 Typography의 속성 설명서를 참조하세요.
    - 매개변수:
        - defaultFontFamily - 이 생성자에 제공된 TextStyles에 사용할 기본 FontFamily입니다. TextStyle의 FontFamily가 null인 경우 이 기본값이 사용됩니다.
        - h1 - 짧고 중요한 텍스트나 숫자를 위해 예약된 가장 큰 헤드라인입니다.
        - h2 - 두 번째로 큰 헤드라인으로, 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
        - h3 - 세 번째로 큰 헤드라인으로, 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
        - h4 - 네 번째로 큰 헤드라인으로, 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
        - h5 - 다섯 번째로 큰 헤드라인으로, 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
        - h6 - 여섯 번째로 큰 헤드라인으로, 짧고 중요한 텍스트나 숫자를 위해 예약되어 있습니다.
        - subtitle1 - 가장 큰 자막이며, 일반적으로 길이가 짧은 중간 강조 텍스트용으로 예약되어 있습니다.
        - subtitle2 - 가장 작은 자막이며, 일반적으로 길이가 짧은 중간 강조 텍스트용으로 예약되어 있습니다.
        - body1 - 가장 큰 본문이며, 일반적으로 작은 텍스트 크기에 잘 작동하므로 긴 형식의 글쓰기에 사용됩니다.
        - body2 - 가장 작은 본문이며, 일반적으로 작은 텍스트 크기에 잘 작동하므로 긴 형식의 글쓰기에 사용됩니다.
        - button - 다양한 유형의 버튼(예: 텍스트, 윤곽선 및 포함된 버튼)과 탭, 대화 상자, 카드에서 사용되는 클릭 유도 문안입니다.
        - caption - 가장 작은 글꼴 크기 중 하나이며, 이미지에 주석을 달거나 헤드라인을 소개하는 데 드물게 사용됩니다.
        - overline - 가장 작은 글꼴 크기 중 하나이며, 이미지에 주석을 달거나 헤드라인을 소개하는 데 드물게 사용됩니다.

            ```kotlin
            @Immutable
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
            	            /*Constructor to create a Typography. For information on the types of style defined in this constructor, see the property documentation for Typography.
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
            ```