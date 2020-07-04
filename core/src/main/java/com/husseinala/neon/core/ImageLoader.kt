@file:Suppress("functionName")

package com.husseinala.neon.core

import androidx.ui.graphics.ImageAsset
import androidx.ui.unit.IntSize

/**
 * An image loader that can be used to fetch an image from a remote or local source.
 */
interface ImageLoader {

    /**
     * Fetch an image using the specified [ImageConfig].
     *
     * @param imageConfig The configuration for the image to be fetched.
     * @param onSuccess The callback to be invoked when an image has loaded successfully.
     * @param onFailure The callback to be invoked when an error occurs while loading the image.
     */
    fun getImage(
        imageConfig: ImageConfig<*>,
        onSuccess: (ImageAsset) -> Unit,
        onFailure: (Exception) -> Unit
    ): Cancelable
}

/**
 * Used to specify the configuration set for the image to be downloaded.
 *
 * @param id The id of the image to be loaded.
 * @param size The desired size of the image. Defaults to original image size when [IntSize.Zero].
 * is used.
 * @param transformation The desired transformations to apply to the downloaded image.
 */
data class ImageConfig<T>(
    val id: ImageId<T>,
    val size: IntSize = IntSize.Zero,
    val transformation: Transformation = Transformation
)

/**
 * Used to specify the location of the image to be fetched.
 */
sealed class ImageId<T> {

    abstract val value: T

    /**
     * Used when loading an image from a specific path.
     *
     * @param value the path to load the image from.
     */
    data class Path(override val value: String) : ImageId<String>()

    /**
     * Used when loading an image from a specific Uri.
     *
     * @param value the Uri to load the image from.
     */
    data class Uri(override val value: android.net.Uri) : ImageId<android.net.Uri>()

    /**
     * Used when loading an image from a specific File.
     *
     * @param value the File to load the image from.
     */
    data class File(override val value: java.io.File) : ImageId<java.io.File>()

    /**
     * Used when loading an image from resources using the specified resource ID.
     *
     * @param value the resource ID for the drawable to be loaded.
     */
    data class Resource(override val value: Int) : ImageId<Int>()
}

/**
 * An interface that's used to indicate a cancelable request.
 */
interface Cancelable {

    /**
     * Attempts to cancel execution of the current request.
     */
    fun cancel()
}

/**
 * Convenience method for creating a new [Cancelable] instance.
 */
fun Cancelable(action: () -> Unit = {}) = object :
    Cancelable {
    override fun cancel() {
        action()
    }
}
