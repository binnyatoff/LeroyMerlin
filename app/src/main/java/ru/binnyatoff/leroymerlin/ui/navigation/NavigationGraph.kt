package ru.binnyatoff.leroymerlin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.ui.screens.BagListView
import ru.binnyatoff.leroymerlin.ui.screens.ProductListView



@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationTree.ProductList.name) {

        composable(NavigationTree.ProductList.name) {
            ProductListView()
        }

        composable(NavigationTree.Bag.name) {
            BagListView()
        }

        composable(NavigationTree.ProductDetails.name) {
            ProductListView()
        }
    }
}