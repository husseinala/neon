package com.husseinala.neon.glide

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.husseinala.neon.core.ImageLoader
import com.husseinala.neon.core.LoadImage
import com.husseinala.neon.core.Neon
import com.husseinala.neon.core.ProvideImageLoader

@Composable
private fun context() = ContextAmbient.current

/**
 * This composable is used to set the current value of the [ImageLoader] ambient to a
 * [GlideImageLoader] with the specified Glide [RequestManager]. Any [Neon] or
 * [LoadImage] composables included in this composable's children will be using the
 * specified [RequestManager] to fetch the images.
 *
 * @param requestManager The Glide request manager to be used. Defaults to a retrieving a
 * request manager instance using [Glide.with].
 */
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
