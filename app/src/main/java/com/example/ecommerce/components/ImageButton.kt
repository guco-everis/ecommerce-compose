package com.example.ecommerce.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.rememberImagePainter

@Composable
fun ImageButton(
    image: String,
    text: String,
    backgroundColor: String = "#000000",
    textColor: String = "#FFFFFF"
){
    Row(
        modifier = Modifier
            .height(55.dp)
            .background(
                color = Color(backgroundColor.toColorInt()),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {

            }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
        )
        Text(
            text = text,
            color = Color(textColor.toColorInt()),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}