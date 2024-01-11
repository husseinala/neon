package com.husseinala.neon.picasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntSize
import com.husseinala.neon.core.Cancelable
import com.husseinala.neon.core.CircleCropTransformation
import com.husseinala.neon.core.ImageConfig
import com.husseinala.neon.core.ImageId
import com.husseinala.neon.core.ImageLoader
import com.husseinala.neon.core.RoundedCornersTransformation
import com.husseinala.neon.core.ScaleType
import com.husseinala.neon.core.ScaleTypeTransformation
import com.husseinala.neon.core.Transformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Target
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation as PicassoRoundedCorners

class PicassoImageLoader(
    private val picasso: Picasso,
) : ImageLoader {
    override fun getImage(
        imageConfig: ImageConfig<*>,
        onSuccess: (ImageBitmap) -> Unit,
        onFailure: (Exception) -> Unit,
    ): Cancelable {
        val id = imageConfig.id

        val request =
            when (id) {
                is ImageId.Path -> picasso.load(id.value)
                is ImageId.Uri -> picasso.load(id.value)
                is ImageId.File -> picasso.load(id.value)
                is ImageId.Resource -> picasso.load(id.value)
            }.apply {
                if (imageConfig.size != IntSize.Zero) {
                    resize(imageConfig.size.width, imageConfig.size.height)
                }

                applyTransformations(imageConfig.transformation)
            }.fetch(onSuccess = onSuccess, onFailure = onFailure)

        return Cancelable {
            picasso.cancelRequest(
                request,
            )
        }
    }
}

private fun RequestCreator.applyTransformations(transformations: Transformation) {
    transformations.forEach { transformation ->
        when (transformation) {
            is ScaleTypeTransformation ->
                when (transformation.scaleType) {
                    ScaleType.CENTER_CROP -> centerCrop()
                    ScaleType.CENTER_INSIDE -> centerInside()
                }
            is CircleCropTransformation -> transform(CropCircleTransformation())
            is RoundedCornersTransformation ->
                transform(
                    PicassoRoundedCorners(
                        transformation.radius,
                        0,
                    ),
                )
        }
    }
}

private fun RequestCreator.fetch(
    onSuccess: (ImageBitmap) -> Unit,
    onFailure: (Exception) -> Unit,
): Target {
    val target =
        object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // not used
            }

            override fun onBitmapFailed(
                exception: Exception,
                errorDrawable: Drawable?,
            ) {
                onFailure(exception)
            }

            override fun onBitmapLoaded(
                bitmap: Bitmap,
                from: Picasso.LoadedFrom?,
            ) {
                onSuccess(bitmap.asImageBitmap())
            }
        }

    into(target)

    return target
}
