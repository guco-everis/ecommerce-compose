package com.example.ecommerce.ui.shopping_cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ecommerce.R
import com.example.ecommerce.components.DialogLoading
import com.example.ecommerce.components.ShoppingCartItem
import com.example.ecommerce.ui.base.StatusValue
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.utils.format
import org.koin.androidx.compose.getViewModel
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieConstants

@Composable
fun ShoppingCartScreen() = with(getViewModel<ShoppingCartViewModel>()) {
    if(loading){
        DialogLoading(
            onDismissRequest = {
                loading = false
            }
        )
    }
    Surface {
        when(state.items){
            is StatusValue.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            is StatusValue.Success -> {
                if(state.items.value.isEmpty()){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_box))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LottieAnimation(
                                composition = lottieComposition,
                                modifier = Modifier
                                    .width(180.dp)
                                    .height(180.dp),
                                iterations = LottieConstants.IterateForever
                            )
                            Text(
                                text = stringResource(id = R.string.str_empty_cart),
                                style = MaterialTheme.typography.h4,
                                color = Color.Black
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(color = Color.White)
                    ) {
                        ShoppingCartList(this@with)
                        ShoppingCartPrices(this@with)
                    }
                }
            }
            is StatusValue.LoadMore -> {

            }
            is StatusValue.Error -> {

            }
        }
    }
}

@Composable
internal fun ColumnScope.ShoppingCartList(viewModel: ShoppingCartViewModel) = with(viewModel){
    LazyColumn(
        modifier = Modifier
            .weight(1f)
            .padding(12.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.str_cart),
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
        items(state.items.value) {
            Spacer(modifier = Modifier.height(12.dp))
            ShoppingCartItem(
                checked = it.enabled,
                name = it.product.name,
                image = it.product.image,
                units = it.units,
                price = it.product.price,
                onChecked = { checked ->
                    enabledProduct(
                        id = it.product.id,
                        enabled = checked
                    )
                },
                onDelete = {
                    deleteProduct(it.product.id)
                },
                onMinus = {
                    minusProduct(it.product.id)
                },
                onPlus = {
                    plusProduct(it.product.id)
                }
            )
        }
    }
}

@Composable
internal fun ShoppingCartPrices(viewModel: ShoppingCartViewModel) = with(viewModel){
    Surface(
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        contentColor = Color.White,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            ItemPrice(
                title = stringResource(R.string.str_price),
                price = state.price
            )
            MyDivider()
            ItemPrice(
                title = stringResource(R.string.str_delivery),
                price = state.delivery
            )
            MyDivider()
            ItemPrice(
                title = stringResource(R.string.str_total_price),
                price = state.totalPrice,
                big = true
            )
            Button(
                onClick = {
                    loading = true
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = stringResource(R.string.str_confirm_order))
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
internal fun ItemPrice(title: String, price: Double, big: Boolean = false){
    Row(
        modifier = Modifier
            .padding(
                top = 14.dp,
                bottom = 14.dp
            )
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Color.Gray
        )
        if(big){
            Text(
                text = "S/${price.format(2)}",
                color = MaterialTheme.colors.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                text = "S/${price.format(2)}",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
internal fun MyDivider(){
    Divider(color = Color.LightGray.copy(alpha = 0.3f))
}

@Preview
@Composable
fun ShoppingCartScreenPreview(){
    EcommerceTheme {
        Scaffold {
            ShoppingCartScreen()
        }
    }
}