package com.husseinala.neon.glide

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.husseinala.neon.core.ProvideImageLoader

@Composable
private fun context() = ContextAmbient.current

@Composable
fun ProvideGlideLoader(
    requestManager: RequestManager = Glide.with(context()),
    children: @Composable () -> Unit
) {
    ProvideImageLoader(
        imageLoader = GlideImageLoader(
            requestManager
        ),
        children = children
    )
}
