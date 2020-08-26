package com.husseinala.neon.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import kotlin.math.roundToInt

enum class ScaleType {
    CENTER_CROP,
    CENTER_INSIDE,
}

/**
 * Transformation that cricle crop the downloaded image.
 */
@Composable
fun Transformation.circleCrop() = this + CircleCropTransformation

/**
 * Specify the [ScaleType] to be used when scaling the downloaded image.
 */
@Composable
fun Transformation.scaleType(scaleType: ScaleType) = this + ScaleTypeTransformation(
    scaleType
)

/**
 * Use [ScaleType.CENTER_CROP] when scaling down the image to the preferred size.
 */
@Composable
fun Transformation.centerCrop() = scaleType(
    ScaleType.CENTER_CROP
)

/**
 * Used [ScaleType.CENTER_INSIDE] whe scaling down the image to the preferred size.
 */
@Composable
fun Transformation.centerInside() = scaleType(
    ScaleType.CENTER_INSIDE
)

/**
 * Transformation that rounds the corners of the downloaded image using the specified [radius].
 */
@Composable
fun Transformation.roundedCorners(radius: Dp) =
    this + RoundedCornersTransformation(radius.toPx().roundToInt())

@Composable
private fun Dp.toPx() = DensityAmbient.current.density * value

object CircleCropTransformation : Transformation

data class ScaleTypeTransformation(val scaleType: ScaleType) :
    Transformation

data class RoundedCornersTransformation(val radius: Int) :
    Transformation
