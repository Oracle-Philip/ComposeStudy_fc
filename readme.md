# Scaffold

- scaffold는 slotApi를 확장한 것으로 볼 수 있다.
- scaffold 안에서 여러가지 상황에 대해 정의 되어 있다.

    ```kotlin
    @Composable
    public fun Scaffold(
        modifier: Modifier,
        scaffoldState: ScaffoldState,
        topBar: @Composable () -> Unit,
        bottomBar: @Composable () -> Unit,
        snackbarHost: @Composable (SnackbarHostState) -> Unit,
        floatingActionButton: @Composable () -> Unit,
        floatingActionButtonPosition: FabPosition,
        isFloatingActionButtonDocked: Boolean,
        drawerContent: @Composable() (ColumnScope.() -> Unit)?,
        drawerGesturesEnabled: Boolean,
        drawerShape: Shape,
        drawerElevation: Dp,
        drawerBackgroundColor: Color,
        drawerContentColor: Color,
        drawerScrimColor: Color,
        backgroundColor: Color,
        contentColor: Color,
        content: @Composable (PaddingValues) -> Unit
    ): Unit
    Material Design layout.
    Scaffold implements the basic material design visual layout structure.
    This component provides API to put together several material components to construct your screen, by ensuring proper layout strategy for them and collecting necessary data so these components will work together correctly.
    For similar components that implement different layout structures, see BackdropScaffold, which uses a backdrop as the centerpiece of the screen, and BottomSheetScaffold, which uses a persistent bottom sheet as the centerpiece of the screen.
    Simple example of a Scaffold with TopAppBar, FloatingActionButton and drawer:
    Params:
    modifier - optional Modifier for the root of the Scaffold
    scaffoldState - state of this scaffold widget. It contains the state of the screen, e.g. variables to provide manual control over the drawer behavior, sizes of components, etc
    topBar - top app bar of the screen. Consider using TopAppBar.
    bottomBar - bottom bar of the screen. Consider using BottomAppBar.
    snackbarHost - component to host Snackbars that are pushed to be shown via SnackbarHostState.showSnackbar. Usually it's a SnackbarHost
    floatingActionButton - Main action button of your screen. Consider using FloatingActionButton for this slot.
    floatingActionButtonPosition - position of the FAB on the screen. See FabPosition for possible options available.
    isFloatingActionButtonDocked - whether floatingActionButton should overlap with bottomBar for half a height, if bottomBar exists. Ignored if there's no bottomBar or no floatingActionButton.
    drawerContent - content of the Drawer sheet that can be pulled from the left side (right for RTL).
    drawerGesturesEnabled - whether or not drawer (if set) can be interacted with via gestures
    drawerShape - shape of the drawer sheet (if set)
    drawerElevation - drawer sheet elevation. This controls the size of the shadow below the drawer sheet (if set)
    drawerBackgroundColor - background color to be used for the drawer sheet
    drawerContentColor - color of the content to use inside the drawer sheet. Defaults to either the matching content color for drawerBackgroundColor, or, if it is not a color from the theme, this will keep the same value set above this Surface.
    drawerScrimColor - color of the scrim that obscures content when the drawer is open
    backgroundColor - background of the scaffold body
    contentColor - color of the content in scaffold body. Defaults to either the matching content color for backgroundColor, or, if it is not a color from the theme, this will keep the same value set above this Surface.
    content - content of your screen. The lambda receives an PaddingValues that should be applied to the content root via Modifier.padding to properly offset top and bottom bars. If you're using VerticalScroller, apply this modifier to the child of the scroller, and not on the scroller itself.
    Samples:
    androidx.compose.material.samples.SimpleScaffoldWithTopBar
    // Unresolved
    androidx.compose.material.samples.ScaffoldWithBottomBarAndCutout
    // Unresolved
    androidx.compose.material.samples.ScaffoldWithSimpleSnackbar
    // Unresolved
      androidx.compose.material   ScaffoldKt.class 
      Gradle: androidx.compose.material:material:1.1.1@aar (classes.jar)
    ```

    - 참고내용
        - drawerContent : 햄버거 아이콘 관련