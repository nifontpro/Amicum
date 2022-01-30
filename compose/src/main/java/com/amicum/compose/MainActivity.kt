package com.amicum.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amicum.report_presentation.report.ReportScreen
import com.amicum.report_presentation.test_work.TestWorkScreen
import com.amicum.report_presentation.time.TimeScreen
import com.amicum.compose.ui.theme.AmicumMobileTheme
import com.amicum.core_ui.utils.Route
import com.amicum.report_presentation.home.HomeScreen
import com.amicum.report_presentation.materials.MaterialsScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        setContent {
            AmicumMobileTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainNavigation()
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.HOME) {

        // Drawer Menu:
        composable(Route.HOME) {
            HomeScreen(
                navController = navController,
//                onNavigateUp = { navController.navigateUp() },
                onNavigateMaterials = { navController.navigate(Route.MATERIALS) },
                onNavigateDownTime = { navController.navigate(Route.DOWN_TIME) },
                onNavigateNotifications = { navController.navigate(Route.NOTIFICATION) },
            )
        }
        composable(Route.REPORT) {
            ReportScreen { navController.navigateUp() }
        }
        composable(Route.MATERIALS) {
            MaterialsScreen { navController.navigateUp() }
        }
        composable(Route.DOWN_TIME) {
            TimeScreen()
        }
        composable(Route.NOTIFICATION) {

        }
        composable(Route.SETTINGS) {
        }

        // HomeWork:
        composable(Route.TEST_WORK) {
            TestWorkScreen { navController.navigateUp() }
        }
    }
}