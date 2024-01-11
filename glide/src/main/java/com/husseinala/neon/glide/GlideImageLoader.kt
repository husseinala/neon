package com.husseinala.neon.glide

import android.os.Handler
import android.os.Looper
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntSize
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.husseinala.neon.core.Cancelable
import com.husseinala.neon.core.CircleCropTransformation
import com.husseinala.neon.core.ImageConfig
import com.husseinala.neon.core.ImageLoader
import com.husseinala.neon.core.RoundedCornersTransformation
import com.husseinala.neon.core.ScaleType
import com.husseinala.neon.core.ScaleTypeTransformation

private typealias GlideTarget<T> = com.bumptech.glide.request.target.Target<T>

class GlideImageLoader(
    private val requestManager: RequestManager,
) : ImageLoader {
    override fun getImage(
        imageConfig: ImageConfig<*>,
        onSuccess: (ImageBitmap) -> Unit,
        onFailure: (Exception) -> Unit,
    ): Cancelable {
        val size =
            if (imageConfig.size == IntSize.Zero) {
                IntSize(GlideTarget.SIZE_ORIGINAL, GlideTarget.SIZE_ORIGINAL)
            } else {
                imageConfig.size
            }

        val transformations =
            imageConfig.transformation.mapNotNull { value ->
                when (value) {
                    is ScaleTypeTransformation ->
                        when (value.scaleType) {
                            ScaleType.CENTER_CROP -> CenterCrop()
                            ScaleType.CENTER_INSIDE -> CenterInside()
                        }
                    is CircleCropTransformation -> CircleCrop()
                    is RoundedCornersTransformation -> RoundedCorners(value.radius)
                    else -> null
                }
            }.toTypedArray()

        val requestOptions = RequestOptions().transform(*transformations)

        val future =
            requestManager
                .asBitmap()
                .load(imageConfig.id.value)
                .apply(requestOptions)
                .listener(
                    onSuccess = { onSuccess(it.asImageBitmap()) },
                    onFailure = onFailure,
                )
                .submit(size.width, size.height)

        return Cancelable {
            future.cancel(true)
        }
    }
}

private inline fun <T> RequestBuilder<T>.listener(
    crossinline onSuccess: (T) -> Unit,
    crossinline onFailure: (Exception) -> Unit = { },
): RequestBuilder<T> =
    listener(
        object : RequestListener<T> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: com.bumptech.glide.request.target.Target<T>,
                isFirstResource: Boolean,
            ): Boolean {
                runOnUiThread {
                    onFailure(e ?: Exception("Unknown Exception"))
                }
                return true
            }

            override fun onResourceReady(
                resource: T,
                model: Any,
                target: GlideTarget<T>,
                dataSource: DataSource,
                isFirstResource: Boolean,
            ): Boolean {
                runOnUiThread {
                    onSuccess(resource)
                }

                return true
            }
        },
    )

private fun runOnUiThread(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post { action() }
}
