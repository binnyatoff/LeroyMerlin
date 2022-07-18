package ru.binnyatoff.leroymerlin.ui.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.binnyatoff.leroymerlin.ui.navigation.BottomNavigationItems

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