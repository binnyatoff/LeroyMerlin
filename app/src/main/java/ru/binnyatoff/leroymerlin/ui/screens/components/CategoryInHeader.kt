package ru.binnyatoff.leroymerlin.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.binnyatoff.leroymerlin.ui.theme.AppTheme
import androidx.compose.ui.unit.sp

@Composable
fun CategoryInHeader(category: String, image: Int) {
    Card(modifier = Modifier.padding(end = 8.dp).width(155.dp).height(72.dp),
        backgroundColor = AppTheme.colors.secondaryBackground,
        shape = RoundedCornerShape(10.dp)) {
        Row(modifier = Modifier.padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(modifier = Modifier
                .size(48.dp)
                .padding(),
                painter = painterResource(id = image),
                contentDescription = category)
            Text(modifier = Modifier.height(32.dp)
                .padding(start = 12.dp),
                color = AppTheme.colors.primaryTextColor,
                fontSize = 12.sp,
                text = category)
        }
    }

}