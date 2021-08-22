package com.example.ecommerce.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R
import com.example.ecommerce.components.ImageButton
import com.example.ecommerce.components.ProductCard
import com.example.ecommerce.ui.base.*
import com.example.ecommerce.ui.theme.Yellow
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() = with(getViewModel<HomeViewModel>()){
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        HomeTopAppBar()
        HomeBody(this@with)
    }
}

@Composable
internal fun HomeTopAppBar(){
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                pushStyle(SpanStyle(color = Color.White))
                append("${stringResource(R.string.str_app)} ")
                pop()
                pushStyle(SpanStyle(color = Yellow))
                append(stringResource(R.string.str_market))
            },
            modifier = Modifier
                .padding(12.dp),
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun HomeBody(viewModel: HomeViewModel) {
    val numberOfItemsByRow = LocalConfiguration.current.screenWidthDp / 200
    val shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    LazyColumn(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = shape
            )
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        categoryBlock(
            viewModel = viewModel
        )
        productsOffSaleBlock(
            viewModel = viewModel
        )
        productsBlock(
            viewModel = viewModel,
            numberOfItemsByRow = numberOfItemsByRow
        )
    }
}

internal fun LazyListScope.categoryBlock(viewModel: HomeViewModel) = with(viewModel){
    item {
        Text(
            text = stringResource(R.string.str_categories),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 24.dp
            )
        )
    }
    when(state.categories){
        is StatusValue.Loading -> {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(87.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is StatusValue.Success -> {
            item {
                LazyRow(
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(state.categories.value){
                        Spacer(modifier = Modifier.width(16.dp))
                        ImageButton(
                            image = it.image,
                            text = it.text,
                            backgroundColor = it.backgroundColor,
                            textColor = it.textColor
                        )
                    }
                }
            }
        }
        is StatusValue.Error -> {

        }
        is StatusValue.LoadMore -> {

        }
    }

}

internal fun LazyListScope.productsOffSaleBlock(
    viewModel: HomeViewModel
) = with(viewModel){
    item {
        Text(
            text = stringResource(R.string.str_offers),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(
                horizontal = 16.dp,
            )
        )
    }
    item {
        val navigation = localAppNavigation()
        when(val status = state.productsOffSale) {
            is StatusValue.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(216.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is StatusValue.Success -> {
                LazyRow(
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(status.value){
                        Spacer(modifier = Modifier.width(12.dp))
                        ProductCard(
                            image = it.image,
                            name = it.name,
                            price = it.price,
                            discount = it.discount,
                            onClick = {
                                navigation.navigateToProduct(
                                    productId = it.id
                                )
                            },
                            modifier = Modifier
                                .wrapContentHeight()
                                .width(120.dp),
                            points = it.points,
                            shippingPrice = it.deliveryPrice
                        )
                    }
                }
            }
            is StatusValue.Error -> {

            }
            is StatusValue.LoadMore -> {

            }
        }
    }
}

internal fun LazyListScope.productsBlock(
    viewModel: HomeViewModel,
    numberOfItemsByRow: Int
) = with(viewModel){
    item {
        Text(
            text = stringResource(R.string.str_for_you),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(
                horizontal = 16.dp,
            )
        )
    }
    when(val status = state.products){
        is StatusValue.Loading -> {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(216.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is StatusValue.Success -> {
            items(items = status.value.chunked(numberOfItemsByRow)) { rowItems ->
                val navigation = localAppNavigation()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                ) {
                    for (product in rowItems) {
                        ProductCard(
                            image = product.image,
                            name = product.name,
                            price = product.price,
                            discount = product.discount,
                            onClick = {
                                navigation.navigateToProduct(product.id)
                            },
                            modifier = Modifier.weight(1f),
                            points = product.points,
                            shippingPrice = product.deliveryPrice
                        )
                    }
                }
                Spacer(Modifier.height(14.dp))
            }
        }
        is StatusValue.Error -> {

        }
        is StatusValue.LoadMore -> {

        }
    }
    item {
        Spacer(modifier = Modifier.height(50.dp))
    }
}