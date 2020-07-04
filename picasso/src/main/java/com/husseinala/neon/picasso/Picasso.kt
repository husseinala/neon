package com.husseinala.neon.picasso

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import com.husseinala.neon.core.ImageLoader
import com.husseinala.neon.core.LoadImage
import com.husseinala.neon.core.Neon
import com.husseinala.neon.core.ProvideImageLoader
import com.squareup.picasso.Picasso

@Composable
private fun context() = ContextAmbient.current

/**
 * This composable is used to set the current value of the [ImageLoader] ambient to a
 * [PicassoImageLoader] with the specified [Picasso] instance. Any [Neon] or
 * [LoadImage] composables included in this composable's children will be using the
 * specified [Picasso] instance to fetch the images.
 *
 * @param picasso The Picasso instance to be used. Defaults to a retrieving a
 * Picasso instance using [Picasso.Builder].
 */
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
