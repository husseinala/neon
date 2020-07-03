package com.husseinala.neon.core

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.compose.getValue
import androidx.compose.key
import androidx.compose.onActive
import androidx.compose.setValue
import androidx.compose.state
import androidx.compose.staticAmbientOf
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.onPositioned
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.graphics.ImageAsset
import androidx.ui.layout.fillMaxSize
import androidx.ui.unit.IntSize

val ImageLoaderAmbient = staticAmbientOf<ImageLoader>()

@Composable
fun Neon(
    url: String,
    modifier: Modifier = Modifier.fillMaxSize(),
    transformation: Transformation = Transformation,
    onLoading: @Composable () -> Unit = { },
    onError: @Composable (Throwable) -> Unit = { },
    onSuccess: @Composable (ImageAsset) -> Unit = {
        Image(
            asset = it,
            contentScale = ContentScale.Crop
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

@Composable
fun ProvideImageLoader(imageLoader: ImageLoader, children: @Composable () -> Unit) {
    Providers(ImageLoaderAmbient provides imageLoader, children = children)
}
