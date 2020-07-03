package com.husseinala.neon.picasso

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import com.husseinala.neon.core.ProvideImageLoader
import com.squareup.picasso.Picasso

@Composable
private fun context() = ContextAmbient.current

@Composable
fun ProvidePicassoLoader(
    picasso: Picasso = Picasso.Builder(context()).build(),
    children: @Composable () -> Unit
) {
    ProvideImageLoader(
        imageLoader = PicassoImageLoader(
            picasso
        ),
        children = children
    )
}
