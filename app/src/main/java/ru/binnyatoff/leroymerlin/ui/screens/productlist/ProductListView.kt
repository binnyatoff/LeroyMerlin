package ru.binnyatoff.leroymerlin.ui.screens.productlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.data.Category
import ru.binnyatoff.leroymerlin.data.Product
import ru.binnyatoff.leroymerlin.ui.navigation.NavigationTree
import ru.binnyatoff.leroymerlin.ui.screens.components.CategoryInHeader
import ru.binnyatoff.leroymerlin.ui.screens.components.ProductView
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme


@Composable
fun ProductListView(viewModel: ProductListViewModel, navController: NavController) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value) {
        is ProductListState.Loaded -> ProductListLoaded(navController = navController,
            productList = state.productList,
            categoryList = state.categoryList,
            viewModel = viewModel)
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(ProductListEvent.ScreenInit)
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductListLoaded(
    navController: NavController,
    productList: List<Product>,
    categoryList: List<Category>,
    viewModel: ProductListViewModel,
) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.White) {
            Text(color = Color.Black, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 16.dp, bottom = 16.dp)
                .height(56.dp),
                fontSize = 20.sp,
                text = stringResource(id = R.string.putties))
        }
    }) {
        LazyColumn(modifier = Modifier
            .padding(start = 16.dp)) {
            item {
                LazyRow(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
                    categoryList.forEach {
                        item {
                            CategoryInHeader(category = it.name, image = it.image)
                        }
                    }

                }
            }
            stickyHeader {
                Row(modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                    ) {
                        Icon(modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = null)
                        Text(color = AppTheme.colors.primaryTextColor,
                            fontSize = 14.sp,
                            text = stringResource(id = R.string.price_head))
                    }
                    Spacer(modifier = Modifier.weight(1F))
                    Row(modifier = Modifier.padding(end = 15.dp)) {
                        Icon(modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = null)
                        Text(color = AppTheme.colors.primaryTextColor,
                            fontSize = 14.sp,
                            text = stringResource(id = R.string.filter)
                        )
                    }
                }
            }
            productList.forEach {
                item {
                    ProductView(product = it,
                        onClick = {
                            navController.navigate(
                                "${NavigationTree.ProductDetails.name}/" + it.productId)
                        },
                        onClickBag = {
                            viewModel.obtainAction(ProductListAction.ClickedBag(it))
                        },
                        onClickShopList = {
                            viewModel.obtainAction(ProductListAction.ClickedShopList(it.productId,
                                !it.inShopList))
                        }
                    )
                }
            }
        }
    }
}