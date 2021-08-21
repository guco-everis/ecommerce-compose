package com.example.ecommerce.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R

@Composable
internal fun ShoppingCartActions(
    units: Int,
    onPlus: () -> Unit,
    onMinus: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(onClick = onMinus)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_baseline_remove_24),
                contentDescription = null,
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp),
                tint = Color.LightGray.copy(alpha = 0.3f)
            )
        }
        Text(
            text = "${if(units < 10) "0" else ""}${units}",
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 6.dp, end = 6.dp)
        )
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp)
                )
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(onClick = onPlus)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp),
                tint = Color.White
            )
        }
    }
}