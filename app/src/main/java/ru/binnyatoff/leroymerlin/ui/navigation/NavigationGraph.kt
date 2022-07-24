package ru.binnyatoff.leroymerlin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.binnyatoff.leroymerlin.ui.screens.baglist.BagListView
import ru.binnyatoff.leroymerlin.ui.screens.productdetails.ProductDetailsView
import ru.binnyatoff.leroymerlin.ui.screens.productlist.ProductListView
import ru.binnyatoff.leroymerlin.ui.screens.baglist.BagListViewModel
import ru.binnyatoff.leroymerlin.ui.screens.productdetails.ProductDetailsViewModel
import ru.binnyatoff.leroymerlin.ui.screens.productlist.ProductListViewModel

@Composable
fun NavigationGraph(navController: NavHostController , modifier: Modifier) {
    NavHost(navController = navController, startDestination = NavigationTree.ProductList.name, modifier = modifier) {

        composable(NavigationTree.ProductList.name) {
            val viewModel = hiltViewModel<ProductListViewModel>()
            ProductListView(viewModel = viewModel, navController = navController)
        }

        composable(NavigationTree.Bag.name) {
            val viewModel = hiltViewModel<BagListViewModel>()
            BagListView(viewModel = viewModel, navController = navController)
        }

        composable("${NavigationTree.ProductDetails.name}/{productId}") {
            val viewModel = hiltViewModel<ProductDetailsViewModel>()
            ProductDetailsView(it.arguments?.getString("productId", "1") ?: "1",
                viewModel = viewModel,
                navController = navController)
        }
    }
}