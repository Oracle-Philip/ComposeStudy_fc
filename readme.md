# Ch4-컴포즈 활용

- Intro - 이번 챕터에서 배울내용
    - Recomposition
    - ConstraintLayout
        - 기본
        - ConstraintSet
        - Chain
        - Barrier
        - 활용
    - Canvas
    - Dialog
    - DropDown Menu
    - SnackBar
    - BottomAppBar

    ---

    - State
    - State Hoisting
        - jetpact Compose는 상태변화에 따라 렌더링을 하는데, State를 상위계층으로 올리는 것을 State Hoisting이라 한다.
    - 애니메이션
    - 부수효과
        - 어떤 컴포넌트들이 그렬질수도 있고 그려지지 않을 수도 있고 동시에 다른 스레드들에서 그려질 수 있는 특징을 가지고 있는데 
        - 그렇기 때문에 side effect 없이 컴포저블이 만들어지는게 중요하다.
        - io호출, 복잡한 연산은 side effect를 줄이는게 중요하다.
    ---

    - Todo 앱 구현하기
    ---

- Recomposition