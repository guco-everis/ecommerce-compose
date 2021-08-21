package com.example.ecommerce.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.ecommerce.R
import com.example.ecommerce.utils.format

@Composable
internal fun ShoppingCartItem(
    checked: Boolean,
    name: String,
    image: String,
    price: Double,
    units: Int,
    onDelete: () -> Unit,
    onChecked: (value: Boolean) -> Unit,
    onPlus: () -> Unit,
    onMinus: () -> Unit
){
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primary,
                uncheckedColor = Color.LightGray.copy(alpha = 0.5f),
            )
        )
        Image(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .height(60.dp)
                .width(60.dp)
                .align(Alignment.CenterVertically),
            painter = rememberImagePainter(data = image),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = name,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(onClick = onDelete)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(4.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "S/${(price * units).format(2)}",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.primary
                )
                ShoppingCartActions(
                    units = units,
                    onPlus = onPlus,
                    onMinus = onMinus
                )
            }
        }
    }
}

@Preview
@Composable
fun ShoppingCartItemPreview(){
    Surface {
        ShoppingCartItem(
            checked = false,
            name = "Manzana",
            image = "",
            units = 2,
            price = 12.0,
            onChecked = {},
            onDelete = {},
            onMinus = {},
            onPlus = {}
        )
    }
}
