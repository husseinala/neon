package com.husseinala.neon.core

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.compose.getValue
import androidx.compose.key
import androidx.compose.onActive
import androidx.compose.setValue
import androidx.compose.state
import androidx.compose.staticAmbientOf
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.onPositioned
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.graphics.ImageAsset
import androidx.ui.layout.fillMaxSize
import androidx.ui.unit.IntSize

/**
 * Provides an [ImageLoader] that can be used by the [Neon] and [LoadImage] composables to fetch images.
 */
val ImageLoaderAmbient = staticAmbientOf<ImageLoader>()

/**
 * A composable that downloads and display an image using the specified [url]. This will attempt
 * to fill the container view width and height. However, an optional [Modifier] parameter can be
 * specified to adjust sizing or draw additional content.
 *
 * @param url The url of the image to be downloaded.
 * @param modifier Modifier used to adjust the layout algorithm or draw decoration content (ex.
 * background). This defaults to using the [centerInside] transformation.
 * @param transformation Used to specify any needed transformation to the downloaded image
 * (ex. [centerCrop], [roundedCorners]).
 * @param onLoading the composable to be displayed when the image is being downloaded, defaults to empty content.
 * @param onError The composable to be displayed when an error occurs, defaults to empty content.
 * @param onSuccess The composable to be displayed when the image downloaded successfully, defaults to a basic [Image].
 */
@Composable
fun Neon(
    url: String,
    modifier: Modifier = Modifier.fillMaxSize(),
    transformation: Transformation = Transformation.centerInside(),
    onLoading: @Composable () -> Unit = { },
    onError: @Composable (Throwable) -> Unit = { },
    onSuccess: @Composable (ImageAsset) -> Unit = {
        Image(
            asset = it,
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        )
    }
) {
    var monetState by state<NeonState<ImageAsset>> { NeonState.Loading }
    var componentSize by state { IntSize.Zero }

    if (componentSize != IntSize.Zero) {
        LoadImage(
            imageConfig = ImageConfig(
                id = ImageId.Path(url),
                size = componentSize,
                transformation = transformation
            ),
            onLoaded = {
                monetState = NeonState.Success(it)
            },
            onFailure = { NeonState.Error(it) }
        )
    }

    Box(
        modifier = modifier + Modifier.onPositioned {
            if (componentSize != it.size) componentSize = it.size
        }
    ) {
        monetState.run {
            when (this) {
                is NeonState.Loading -> onLoading()
                is NeonState.Error -> onError(error)
                is NeonState.Success -> onSuccess(result)
            }
        }
    }
}

/**
 * A composable that downloads an image with the specified [ImageConfig] using the [ImageLoader]
 * provided by the [ImageLoaderAmbient].
 *
 * @param onLoaded Callback invoked when an image has been successfully downloaded.
 * @param onFailure Callback invoked when an error occurs while downloading the specified image.
 */
@Composable
fun LoadImage(
    imageConfig: ImageConfig<*>,
    onLoaded: (ImageAsset) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    key(imageConfig) {
        val imageLoader = ImageLoaderAmbient.current

        onActive {
            val cancelable = imageLoader.getImage(
                imageConfig,
                onSuccess = onLoaded,
                onFailure = onFailure
            )

            onDispose { cancelable.cancel() }
        }
    }
}

/**
 * This composable is used to set the current value of the [ImageLoader] ambient. Any [Neon] or
 * [LoadImage] composables included in this composable's children will be using the specified [ImageLoader]
 * to fetch the images.
 */
@Composable
fun ProvideImageLoader(imageLoader: ImageLoader, children: @Composable () -> Unit) {
    Providers(ImageLoaderAmbient provides imageLoader, children = children)
}
