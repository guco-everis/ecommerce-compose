package com.example.ecommerce.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.ecommerce.R
import com.example.ecommerce.components.ShoppingCartActions
import com.example.ecommerce.ui.base.StatusValue
import com.example.ecommerce.ui.base.UpdateCart
import com.example.ecommerce.ui.theme.Delivery
import com.example.ecommerce.utils.format
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import com.example.ecommerce.ui.theme.Green
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun ProductScreen(productId: String) = with(getViewModel<ProductViewModel>(
    parameters = { parametersOf(productId) }
)){
    var snackBarVisible by remember { mutableStateOf(false) }
    Surface {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .background(
                            color = Color.LightGray.copy(alpha = 0.3f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    when(val status = state.product){
                        is StatusValue.Loading -> {
                            CircularProgressIndicator()
                        }
                        else -> {
                            Image(
                                painter = rememberImagePainter(data = status.value?.image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            )
                        }
                    }
                }
                state.product.value?.apply {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.h5
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        if(discount > 0.0){
                            Row {
                                Text(
                                    text = "S/${price.format(2)}",
                                    style = MaterialTheme.typography.h5,
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "S/${(price - discount).format(2)}",
                                    style = MaterialTheme.typography.h5,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        }else{
                            Text(
                                text = "S/${price.format(2)}",
                                style = MaterialTheme.typography.h5,
                                color = MaterialTheme.colors.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Row {
                            Text(
                                text = stringResource(id = R.string.str_delivery_cost),
                                color = Delivery,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            if(deliveryPrice > 0.0){
                                Text(
                                    text = "S/${deliveryPrice.format(2)}",
                                    color = Delivery,
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                Text(
                                    text = stringResource(id = R.string.str_free),
                                    color = Delivery,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShoppingCartActions(
                                units = state.units,
                                onPlus = ::plusUnit,
                                onMinus = ::minusUnit
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Button(
                                onClick = {
                                    addProduct()
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(16.dp),

                                ) {
                                Text(text = stringResource(R.string.str_add_cart))
                            }
                        }
                    }
                }
            }
            if(snackBarVisible){
                Snackbar(
                    backgroundColor = Green,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Text(text = stringResource(id = R.string.str_add_cart_success))
                }
            }
        }
    }
    onEvent {
        when(it){
            is ProductEvent.UpdateCart -> {
                UpdateCart.execute()
                snackBarVisible = true
                withContext(Dispatchers.IO){ delay(4000) }
                snackBarVisible = false
            }
        }
    }
}