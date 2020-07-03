@file:Suppress("functionName")

package com.husseinala.neon.core

import android.net.Uri
import androidx.ui.graphics.ImageAsset
import androidx.ui.unit.IntSize
import java.io.File

interface ImageLoader {
    fun getImage(
        imageConfig: ImageConfig<*>,
        onSuccess: (ImageAsset) -> Unit,
        onFailure: (Exception) -> Unit
    ): Cancelable
}

/**
 * @param url the url of the image to be loaded
 * @param size the desired size of the image. Defaults to original image size.
 * @param transformation the desired transformations to apply to
 */
data class ImageConfig<T>(
    val id: ImageId<T>,
    val size: IntSize = IntSize.Zero,
    val transformation: Transformation = Transformation
)

sealed class ImageId<T> {

    abstract val value: T

    data class Path(override val value: String) : ImageId<String>()
    data class Uri(override val value: android.net.Uri) : ImageId<android.net.Uri>()
    data class File(override val value: java.io.File) : ImageId<java.io.File>()
    data class Resource(override val value: Int) : ImageId<Int>()
}

interface Cancelable {
    fun cancel()
}

fun Cancelable(action: () -> Unit = {}) = object :
    Cancelable {
    override fun cancel() {
        action()
    }
}
