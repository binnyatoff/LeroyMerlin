package ru.binnyatoff.leroymerlin.ui.screens.baglist

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.data.Product
import ru.binnyatoff.leroymerlin.ui.navigation.NavigationTree
import ru.binnyatoff.leroymerlin.ui.screens.components.GreenButton
import ru.binnyatoff.leroymerlin.ui.screens.components.ProductInBagView
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme

@Composable
fun BagListView(viewModel: BagListViewModel, navController: NavController) {

    val viewState = viewModel.viewState.observeAsState(BagListState.Empty)
    when (val state = viewState.value) {
        is BagListState.Loaded -> BagListLoaded(state.list,
            amount = state.amount,
            weight = state.weight,
            price = state.price,
            viewModel = viewModel,
            navController = navController)
        is BagListState.Empty -> BagListEmpty()
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(BagListEvent.ScreenInit)
    })

}

@Composable
fun BagListLoaded(
    list: List<Product>,
    viewModel: BagListViewModel,
    navController: NavController,
    amount: Int,
    weight: Double,
    price: Int,
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)) {
            IconButton(modifier = Modifier
                .align(Alignment.TopEnd),
                onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_more_vert),
                    contentDescription = stringResource(id = R.string.more))
            }
            Text(modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(top = 16.dp),
                fontSize = 34.sp,
                text = stringResource(id = R.string.bag),
                color = AppTheme.colors.primaryTextColor)
        }
        Divider(modifier = Modifier.padding(top = 16.dp),
            color = AppTheme.colors.secondaryBackground,
            thickness = 1.dp)
        Column() {
            list.forEach {
                ProductInBagView(product = it, onClick = {
                    navController.navigate(
                        "${NavigationTree.ProductDetails.name}/" + it.productId)
                },
                    onClickButton = { viewModel.obtainAction(BagListAction.ClickedBag(it)) })
            }
        }
        Spacer(modifier = Modifier.weight(1F))
        Text(fontSize = 12.sp,
            color = AppTheme.colors.minor,
            text = stringResource(id = R.string.total))
        Row(modifier = Modifier.padding(top = 7.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(fontSize = 14.sp,
                color = AppTheme.colors.primaryTextColor,
                text = amount.toString() + " " + stringResource(id = R.string.product_with_weight)
                        + " " +
                        +weight + " " + stringResource(
                    id = R.string.kg))
            Spacer(modifier = Modifier.weight(1F))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(modifier = Modifier.padding(end = 2.dp), fontSize = 16.sp,
                    color = AppTheme.colors.primaryTextColor, text = "$price")
                Text(fontSize = 12.sp,
                    color = AppTheme.colors.primaryTextColor,
                    text = stringResource(id = R.string.rub))
            }
        }
        GreenButton(modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(48.dp), onClick = { /*TODO*/ },
            text = stringResource(id = R.string.draw_up))
    }
}


@Composable
fun BagListEmpty() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 56.dp, end = 16.dp, start = 16.dp)) {
        Text(color = AppTheme.colors.primaryTextColor,
            fontSize = 28.sp,
            text = stringResource(id = R.string.bag_empty))
        Text(modifier = Modifier.padding(top = 12.dp), color = AppTheme.colors.primaryTextColor,
            fontSize = 16.sp,
            text = stringResource(id = R.string.bag_empty_text))
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp), onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.buttonMinor)) {
            Text(modifier = Modifier.padding(12.dp),
                fontSize = 16.sp,
                text = stringResource(id = R.string.entry),
                color = AppTheme.colors.primaryTextColor)
        }

    }
}