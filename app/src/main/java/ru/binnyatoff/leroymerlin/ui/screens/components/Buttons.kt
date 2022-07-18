package ru.binnyatoff.leroymerlin.ui.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme

@Composable
fun InBagButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier, onClick = onClick,
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 11.dp,
            end = 12.dp,
            bottom = 11.dp
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.buttonMinor)) {
        Text(text = stringResource(id = R.string.one_amount))
        Icon(painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "")
    }
}

@Composable
fun AddToBagButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier, onClick = onClick,
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 10.dp,
            end = 20.dp,
            bottom = 10.dp
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.buttonMinor)) {
        Text(text = stringResource(id = R.string.in_bag))
    }
}

@Composable
fun GreenButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    Button(
        modifier = modifier, onClick = onClick,
        contentPadding = PaddingValues(
            top = 12.dp,
            bottom = 12.dp,
            end = 20.dp,
            start = 20.dp
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.buttonAccent),
    ) {
        Text(fontSize = 16.sp, color = AppTheme.colors.primaryBackground, text = text)
    }
}