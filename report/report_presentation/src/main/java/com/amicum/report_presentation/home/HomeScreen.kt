package com.amicum.report_presentation.home

import android.app.DatePickerDialog
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.amicum.auth_presentation.LoginDialog
import com.amicum.report_presentation.R
import com.amicum.core_ui.LocalSpacing
import com.amicum.core_ui.mediumPadding
import com.amicum.core_ui.normalPadding
import com.amicum.core_ui.utils.TopBarOther
import com.amicum.report_presentation.home.drawer.Drawer
import kotlinx.coroutines.launch
import java.time.LocalDate

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    onNavigateMaterials: () -> Unit = {},
    onNavigateDownTime: () -> Unit = {},
    onNavigateNotifications: () -> Unit = {},
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    val spacing = LocalSpacing.current
    spacing.bigPadding

    val authUser = viewModel.authUser.collectAsState().value
//    val authUser = viewModel.authUser.value
    val shifts = viewModel.shifts.collectAsState().value

    LaunchedEffect(scaffoldState) {
        viewModel.message.collect { message ->
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarOther(
                header = stringResource(R.string.app_name),
                image = Icons.Filled.Menu,
                imageOther = Icons.Outlined.Notifications,
                onBack = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onOther = onNavigateNotifications
            )
        },
        drawerContent = {
            Drawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController,
                authUser = authUser
            ) { viewModel.logout() }
        },
    ) {

        var percent by remember {
            mutableStateOf(0f)
        }

        val animatePercent = animateFloatAsState(
            targetValue = percent,
            animationSpec = tween(3000)
        )

        val selectDate = viewModel.selectDate
        val localDate = if (selectDate.value.isNotEmpty()) selectDate.value.toLocalDate()
        else LocalDate.now()
        val context = LocalContext.current
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, day ->
                val date = LocalDate.of(year, month + 1, day)
                selectDate.value = date.toString()
            }, localDate.year, localDate.monthValue - 1, localDate.dayOfMonth
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CentralContainer(animatePercent) { percent = 100f }
                BottomContainer(
                    onLeftClick = onNavigateMaterials,
                    onRightClick = onNavigateDownTime
                )
            }

            TopContainer(
                selectDate = selectDate,
                onLeftClick = { datePickerDialog.show() },
                onRightClick = { percent = 30f }
            )

            val isShowLoginDialog = viewModel.isShowLoginDialog.collectAsState().value
//            val isShowLoginDialog = viewModel.isShowLoginDialog.value
            if (!isShowLoginDialog) {
                LoginDialog()
            }
        }
    }
}

/**
 * Верхний контейнер с двумя кнопками
 */
@Composable
private fun BoxScope.TopContainer(
    selectDate: MutableState<String>,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    ContainerVerticalLine {
        ButtonWithDownImage(
            topText = stringResource(R.string.sDate),
            centerText = selectDate.value.getLocalizedDate(),
            onClick = onLeftClick
        )
        ButtonWithDownImage(
            topText = stringResource(R.string.sShift),
            centerText = "IV",
            alignment = Alignment.CenterEnd,
            onClick = onRightClick
        )
    }
}

/**
 *  Центральный контейнер
 */
@Composable
private fun ColumnScope.CentralContainer(
    animatePercent: State<Float>,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(top = 0.dp, bottom = normalPadding)
            .padding(horizontal = mediumPadding),
        elevation = normalPadding
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {

            CenterButtonWithProgress(
                percent = animatePercent,
                sizeCanvas = screenWidth * CENTER_BUTTON_K,
                onClick = onClick
            )

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = mediumPadding),
            ) {
                TwoTextStyleInfo(
                    topText = "${animatePercent.value.toInt()}%",
                    centerText = stringResource(R.string.sPercentFill),
                )
                TwoTextStyleInfo(
                    topText = "12",
                    centerText = stringResource(R.string.sCountWorkers),
                    isLeft = false
                )
            }
        }
    }
}

/**
 * Нижний контейнер
 */
@Composable
private fun BottomContainer(
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
    background: Color = MaterialTheme.colors.surface,
//    content: @Composable BoxScope.() -> Unit
) {
    Card(
        elevation = normalPadding,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = mediumPadding)
            .padding(bottom = normalPadding)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(background)
                .padding(vertical = normalPadding),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ButtonWithTopImage(
                textButton = stringResource(R.string.sMaterialsAccount),
                image = ImageVector.vectorResource(R.drawable.ic_materials),
                onClick = onLeftClick
            )

            ButtonWithTopImage(
                textButton = stringResource(R.string.sDowntimeAccount),
                image = ImageVector.vectorResource(R.drawable.ic_time),
                onClick = onRightClick
            )
        }
    }
}

// Коэффициент масштабирования центральной круговой
// кнопки с индикатором прогресса, в зависимости от шириы экрана
private const val CENTER_BUTTON_K = 0.55f
