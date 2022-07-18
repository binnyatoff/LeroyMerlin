package ru.binnyatoff.leroymerlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.binnyatoff.leroymerlin.ui.navigation.BottomNavigation
import ru.binnyatoff.leroymerlin.ui.navigation.BottomViewModel
import ru.binnyatoff.leroymerlin.ui.navigation.NavigationGraph
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme
import ru.binnyatoff.leroymerlin.ui.theme.LeroyMerlinTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LeroyMerlinTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val primaryBackground = AppTheme.colors.primaryBackground

    Scaffold(
        bottomBar = {
            val viewModel = hiltViewModel<BottomViewModel>()
            BottomNavigation(navController = navController, viewModel = viewModel)
        }
    ) {
        systemUiController.setSystemBarsColor(
            color = primaryBackground,
            darkIcons = true
        )
        NavigationGraph(navController = navController)
    }
}