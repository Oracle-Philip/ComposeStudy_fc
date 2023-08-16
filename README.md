# ComposeStudy_fc

해당 리포지토리는 패스트캠퍼스 인강 ****생산성 향상을 위한 Jetpack Compose 강의****

에서 제공하는 아래 github 예제코드로 스터디 및 정리한 것 입니다.

[생산성 향상을 위한 Jetpack Compose](https://github.com/Fastcampus-JetpackCompose-1)

- 2023-08-16 학습내용
    - Part1 Modifier에서
        - Button 컴포넌트에 경우 Modifier.background가 적용이 안된다.
        - 별도로 아래와 같이 colors를 사용한다.
            - 이때, backgroundColor는 버튼 자체의 배경을
            - contentColor는 Button 하위 컴포넌트에 대한 색상을 지정 할 수 있다.

            ```kotlin
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
                )
            ```