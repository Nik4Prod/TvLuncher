package com.example.tvapplication.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberCarouselState

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CarouselTv(
    imageList: List<Int>
) {
    val carouselState = rememberCarouselState()
    Carousel(
        itemCount = imageList.size,
        carouselState = carouselState,
        modifier = Modifier.wrapContentSize()
    ) {
        ImageItem(image = imageList[it])
    }
}

@Composable
fun ImageItem(
    image: Int
) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .height(600.dp)
                .clip(RoundedCornerShape(15)),
            contentScale = ContentScale.FillHeight
        )
    }


}