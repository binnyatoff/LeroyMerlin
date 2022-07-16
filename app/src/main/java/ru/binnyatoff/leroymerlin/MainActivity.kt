package ru.binnyatoff.leroymerlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.binnyatoff.leroymerlin.ui.navigation.BottomNavigation
import ru.binnyatoff.leroymerlin.ui.navigation.NavigationGraph
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme
import ru.binnyatoff.leroymerlin.ui.theme.LeroyMerlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            LeroyMerlinTheme {
                Scaffold(bottomBar = { BottomNavigation(navController = navController) }) {
                    NavigationGraph(navController)
                }
            }
        }
    }
}
