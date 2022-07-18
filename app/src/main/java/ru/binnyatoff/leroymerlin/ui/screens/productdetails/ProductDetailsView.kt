package ru.binnyatoff.leroymerlin.ui.screens.productdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
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
import ru.binnyatoff.leroymerlin.ui.screens.components.GreenButton
import ru.binnyatoff.leroymerlin.ui.screens.components.InBagButton
import ru.binnyatoff.leroymerlin.ui.screens.components.Stars
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme


@Composable
fun ProductDetailsView(
    productId: String,
    viewModel: ProductDetailsViewModel,
    navController: NavController,
) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value) {
        is ProductDetailsState.Loaded -> ProductDetailsLoaded(product = state.product,
            navController = navController, viewModel)
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(ProductDetailsEvent.ScreenInit(productId.toInt()))
    })

}

@Composable
fun ProductDetailsLoaded(
    product: Product,
    navController: NavController,
    viewModel: ProductDetailsViewModel,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.back))
            }
            Spacer(modifier = Modifier.weight(1F))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_more_vert),
                    contentDescription = stringResource(id = R.string.more))
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(273.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(229.dp),
                painter = painterResource(id = product.productImage),
                contentDescription = "")
        }
        Row(modifier = Modifier
            .padding(start = 8.dp)
            .height(20.dp)
            .width(108.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(color = AppTheme.colors.buttonLight),
                onClick = {/*TODO*/ }
            )) {
            Text(modifier = Modifier.padding(start = 8.dp),
                color = AppTheme.colors.secondaryTextColor,
                text = stringResource(id = R.string.LM))
            Text(color = AppTheme.colors.secondaryTextColor,
                text = stringResource(R.string.number_mock),
                modifier = Modifier.padding(start = 4.dp))
        }
        Row() {
            Text(modifier = Modifier
                .padding(start = 16.dp)
                .width(280.dp),
                fontSize = 16.sp,
                text = product.productName)
            Spacer(modifier = Modifier.weight(1F))
            IconButton(onClick = {
                viewModel.obtainAction(ProductDetailsAction.ClickedShopList(product.productId,
                    product.inShopList))
            }, modifier = Modifier.padding(end = 8.dp)) {
                if (product.inShopList) {
                    Icon(painter = painterResource(id = R.drawable.ic_flag_fill),
                        contentDescription = stringResource(id = R.string.flag),
                        tint = AppTheme.colors.flagPrimary)
                } else {
                    Icon(painter = painterResource(id = R.drawable.ic_flag),
                        contentDescription = stringResource(id = R.string.flag),
                        tint = AppTheme.colors.flagSecondary)
                }
            }
        }
        Row(modifier = Modifier
            .padding(start = 16.dp)
            .width(181.dp)
            .height(48.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = { /*TODO*/ }
            ),
            verticalAlignment = Alignment.CenterVertically) {
            Stars()
            Text(modifier = Modifier.padding(start = 8.dp,
                top = 14.dp,
                bottom = 14.dp), text = stringResource(id = R.string.reviews))
        }
        Row(modifier = Modifier
            .height(80.dp)
            .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(color = AppTheme.colors.primaryTextColor,
                    fontSize = 18.sp,
                    text = product.productPrice)
                Text(color = AppTheme.colors.primaryTextColor, fontSize = 14.sp,
                    modifier = Modifier.padding(start = 4.dp),
                    text = stringResource(id = R.string.rub_amount))
            }
            Spacer(modifier = Modifier.weight(1F))
            if (product.inBag) {
                InBagButton(onClick = {
                    viewModel.obtainAction(ProductDetailsAction.ClickedBag(product))
                })
            } else {
                GreenButton(onClick = {
                    viewModel.obtainAction(ProductDetailsAction.ClickedBag(product))
                },
                    text = stringResource(id = R.string.in_bag))
            }
        }
    }
}