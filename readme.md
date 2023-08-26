### 부수효과(SideEffect) 강의 전

---

- LaunchedEffect: 컴포저블의 범위에서 정지 함수 실행
    - 정지함수 실행을 위해 있는 것이다. suspend 함수라고 볼 수 있다. coroutine scope를 제공해준다.
- rememberCoroutineScope: 컴포지션 인식 범위를 확보하여 컴포저블 외부에서 코루틴 실행
    - coroutine scope를 만들고 유지하기 위해서 rememberCoroutineScope를 사용 할 수 있다.
- rememberUpdatedState: 값이 변경되는 경우 다시 시작되지 않아야 하는 효과에서 값 참조
    - 값이 변경되는 경우에 어떤 효과 등 재사용되고 싶지 않고 다시 시작되고 싶지 않은 것들..(값은 바뀌는데 그것을 이용한 효과에 대해서 그 효과는 재사용하고 싶지 않을때)
- DisposableEffect: 정리가 필요한 효과
    - 예) file을 열었다가 닫을때.. 닫는 것이 짝이 맞춰져 있어야할때
    
- SideEffect: Compose 상태를 비 Compose 상태로 바꿀 수 있는것

- producestate: 비 Compose 상태를 Compose 상태로 바꿀 수 있는것
- derivedStateof: 하나 이상의 상태 객체를 다른 상태로 변환
- snapshotFlow: Compose의 상태를 Flow로 변환