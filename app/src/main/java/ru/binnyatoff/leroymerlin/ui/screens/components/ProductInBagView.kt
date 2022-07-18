package ru.binnyatoff.leroymerlin.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.data.Product
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme

@Composable
fun ProductInBagView(product: Product, onClick: () -> Unit, onClickButton: () -> Unit) {
    Row(modifier = Modifier
        .padding(start = 16.dp, top = 16.dp)
        .clickable(
            interactionSource = MutableInteractionSource(),
            indication = rememberRipple(),
            onClick = onClick
        )
        .fillMaxWidth()) {
        Image(modifier = Modifier.size(87.dp),
            painter = painterResource(id = product.productImage),
            contentDescription = product.productName)
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Row() {
                Text(modifier = Modifier.width(193.dp),
                    fontSize = 14.sp,
                    color = AppTheme.colors.primaryTextColor,
                    text = product.productName)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_more_vert),
                        contentDescription = stringResource(id = R.string.more))
                }
            }
            Row(modifier = Modifier.padding(end = 16.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InBagButton(modifier = Modifier
                    .width(79.dp)
                    .height(40.dp),
                    onClick = onClickButton)
                Spacer(modifier = Modifier.weight(1F))
                Row() {
                    Text(fontSize = 14.sp, text = product.productPrice)
                    Text(modifier = Modifier.padding(start = 2.dp),
                        fontSize = 12.sp,
                        text = stringResource(
                            id = R.string.rub))
                }
            }
            Divider(modifier = Modifier.padding(top = 16.dp),
                color = AppTheme.colors.secondaryBackground,
                thickness = 1.dp)
        }
    }
}
