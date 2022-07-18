package ru.binnyatoff.leroymerlin.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.data.Product
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme

@Composable
fun ProductView(
    product: Product,
    onClick: () -> Unit,
    onClickBag: () -> Unit,
    onClickShopList: () -> Unit,
) {
    Row(modifier = Modifier
        .padding(top = 16.dp)
        .clickable(
            interactionSource = MutableInteractionSource(),
            indication = rememberRipple(),
            onClick = onClick
        )) {
        Image(modifier = Modifier.size(92.dp),
            painter = painterResource(id = product.productImage),
            contentDescription = stringResource(id = R.string.item))
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(fontSize = 14.sp, text = product.productName)

            Row(modifier = Modifier
                .width(97.dp)
                .height(16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Stars(modifier = Modifier)
                Text(fontSize = 12.sp,
                    color = AppTheme.colors.secondaryTextColor,
                    text = stringResource(
                        id = R.string.mock))
            }
            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(top = 8.dp)) {
                Text(color = AppTheme.colors.primaryTextColor,
                    fontSize = 16.sp,
                    text = product.productPrice)
                Text(color = AppTheme.colors.primaryTextColor,
                    fontSize = 12.sp,
                    text = stringResource(id = R.string.rub_amount))
            }
            Text(modifier = Modifier.padding(top = 2.dp), color = AppTheme.colors.minor,
                fontSize = 12.sp,
                text = product.twoProductPrice + stringResource(id = R.string.rub_amount))
            Row(modifier = Modifier.padding(top = 12.dp)) {
                if (product.inBag) {
                    InBagButton(onClick = onClickBag)
                } else {
                    AddToBagButton(onClick = onClickBag)
                }
                Spacer(modifier = Modifier.weight(1F))
                IconButton(modifier = Modifier, onClick = onClickShopList) {
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
            Divider(modifier = Modifier.padding(top = 16.dp),
                color = AppTheme.colors.secondaryBackground,
                thickness = 1.dp)
        }
    }
}