package ru.binnyatoff.leroymerlin.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.ui.screens.components.IconBag
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme


sealed class BottomNavigationItems(var title: Int, var screen_route: String, var icon: Int) {
    object ProductList :
        BottomNavigationItems(R.string.main, NavigationTree.ProductList.name, R.drawable.ic_shape)
    object Bag : BottomNavigationItems(R.string.bag, NavigationTree.Bag.name, R.drawable.ic_bag)
}


@Composable
fun BottomNavigation(navController: NavController, viewModel: BottomViewModel) {
    BottomNavigation(modifier = Modifier.height(56.dp),
        backgroundColor = AppTheme.colors.primaryBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val bagSize = viewModel.bagSize.collectAsState()

        BottomNavigationItem(modifier = Modifier,
            selectedContentColor = AppTheme.colors.accentColor,
            unselectedContentColor = AppTheme.colors.minor,
            selected = currentRoute == BottomNavigationItems.ProductList.screen_route,
            label = { Text(text = stringResource(id = BottomNavigationItems.ProductList.title)) },
            onClick = { navController.navigate(BottomNavigationItems.ProductList.screen_route) },
            icon = {
                Icon(painter = painterResource(id = BottomNavigationItems.ProductList.icon),
                    contentDescription = stringResource(
                        id = BottomNavigationItems.ProductList.title))
            })


        BottomNavigationItem(modifier =
        Modifier,
            selectedContentColor = AppTheme.colors.accentColor,
            unselectedContentColor = AppTheme.colors.minor,
            selected = currentRoute == BottomNavigationItems.Bag.screen_route,
            label = { Text(text = stringResource(id = BottomNavigationItems.Bag.title)) },
            onClick = { navController.navigate(BottomNavigationItems.Bag.screen_route) },
            icon = {
                IconBag(amount = bagSize.value, modifier = Modifier
                    .width(40.dp)
                    .height(30.dp))
            }
        )
    }
}

