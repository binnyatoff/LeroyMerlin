package ru.binnyatoff.leroymerlin.ui.navigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme


sealed class BottomNavigationItems(var title: Int, var screen_route: String, var icon: Int) {
    object ProductList :
        BottomNavigationItems(R.string.main, NavigationTree.ProductList.name, R.drawable.ic_shape)

    object Bag : BottomNavigationItems(R.string.bag, NavigationTree.Bag.name, R.drawable.ic_bag)
}


@Composable
fun BottomNavigation(navController: NavController) {
    BottomNavigation(modifier = Modifier.height(56.dp),
        backgroundColor = AppTheme.colors.primaryBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
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
                IconBag(amount = 100, modifier = Modifier
                    .width(40.dp)
                    .height(30.dp))
            }
        )
    }
}

@Composable
fun IconBag(amount: Int, modifier: Modifier) {
    Box(modifier = modifier
    ) {
        Icon(modifier = Modifier
            .align(Alignment.BottomCenter)
            .size(24.dp),
            painter = painterResource(id = BottomNavigationItems.Bag.icon),
            contentDescription = stringResource(
                id = BottomNavigationItems.Bag.title))
        if (amount != 0) {
            if (amount > 9) {
                BagAmountComponent(amount = "9+", Modifier.align(Alignment.TopEnd))
            } else {
                BagAmountComponent(amount = amount.toString(), Modifier.align(Alignment.TopEnd))
            }
        }
    }
}

@Composable
fun BagAmountComponent(amount: String, modifier: Modifier) {
    Box(modifier = modifier
        .size(19.dp),
        contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.White
            )
        }
        Canvas(modifier = Modifier.size(16.dp)) {
            drawCircle(
                color = Color(0xFFE6615E)
            )
        }
        Text(textAlign = TextAlign.Center, fontSize = 12.sp, color = Color.White, text = amount)
    }
}