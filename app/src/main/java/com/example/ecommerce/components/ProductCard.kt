package com.example.ecommerce.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.ecommerce.R
import com.example.ecommerce.ui.theme.Delivery
import com.example.ecommerce.ui.theme.Gray
import com.example.ecommerce.utils.format
import com.gowtham.ratingbar.RatingBar
import kotlinx.coroutines.delay

@Composable
fun ProductCard(
    image: String,
    name: String,
    price: Double,
    discount: Double,
    onClick: () -> Unit,
    points: Float,
    shippingPrice: Double,
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Color.White
            )
            .clickable(
                onClick = onClick
            )
            .padding(8.dp)
            .then(modifier)
    ) {
        Column {
            var widthBox by remember {
                mutableStateOf(0.dp)
            }
            val density = LocalDensity.current.density
            Box(
                Modifier
                    .fillMaxWidth()
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.width) {
                            placeable.placeRelative(0, 0)
                        }
                    }
                    .onGloballyPositioned {
                        widthBox = (it.size.width / density).dp
                    }
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = image
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .width(widthBox)
                        .height(widthBox),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.h6
            )
            if(discount > 0.0){
                DiscountPrice(
                    price = price,
                    discount = discount
                )
            }else{
               RegularPrice(points = points, price = price)
            }
        }
        when{
            discount > 0.0 -> {
                DiscountFlag(
                    price = price,
                    discount = discount
                )
            }
            shippingPrice == 0.0 -> {
                ShippingFlag()
            }
        }
    }
}

@Composable
internal fun DiscountFlag(price: Double, discount: Double){
    val percentage = discount * 100 / price
    var targetValue by remember {
        mutableStateOf(0.8f)
    }
    // 0.8f - 1.0f
    val duration = 700L
    val scale by animateFloatAsState(
        targetValue,
        animationSpec = tween(
            durationMillis = duration.toInt(),
            easing = FastOutSlowInEasing
        )
    )
    LaunchedEffect(true){
        while (true){
            targetValue = 1.0f
            delay(duration)
            targetValue = 0.8f
            delay(duration)
        }
    }
    Box(
        modifier = Modifier
            .height(54.dp)
            .width(54.dp)
            .padding(4.dp)
            .scale(scale)
            .background(
                color = Color.Red,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "-${percentage.toInt()}%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

@Composable
internal fun DiscountPrice(price: Double, discount: Double){
    Row(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Text(
            text = "S/${price.format(2)}",
            style = MaterialTheme.typography.h6,
            color = Gray,
            textDecoration = TextDecoration.LineThrough,
            fontSize = 14.sp
        )
        Text(
            text = "S/${(price - discount).format(2)}",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
internal fun RegularPrice(points: Float, price: Double){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        RatingBar(
            value = points,
            onRatingChanged = {},
            numStars = 5,
            modifier = Modifier
                .weight(1f),
            size = 12.dp,
            isIndicator = true
        )
        Text(
            text = "S/${price.format(2)}",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(1f),
            maxLines = 1,
            textAlign = TextAlign.End
        )
    }
}

@Composable
internal fun ShippingFlag(){
    Column(
        modifier = Modifier
            .background(
                color = Delivery,
                shape = GenericShape { size, _ ->
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height)
                    lineTo(size.width / 2, (size.height * 0.7).toFloat())
                    lineTo(0f, size.height)
                    lineTo(0f, 0f)
                    close()
                }
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            Icons.Filled.ShoppingCart,
            contentDescription = null,
            tint = Color.White
        )
        Text(
            text = stringResource(R.string.str_l_free),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
