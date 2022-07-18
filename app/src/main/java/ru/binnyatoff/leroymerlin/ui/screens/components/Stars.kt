package ru.binnyatoff.leroymerlin.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.binnyatoff.leroymerlin.R

@Composable
fun Stars(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        for (i in 0..5) {
            Image(painter = painterResource(id = R.drawable.ic_star),
                contentDescription = stringResource(
                    id = R.string.star))
        }
    }
}