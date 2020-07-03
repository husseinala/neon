package com.husseinala.neon.core

interface Transformation : Iterable<Transformation> {

    override fun iterator(): Iterator<Transformation> =
        SingleItemIterator(this)

    operator fun plus(other: Transformation): Transformation = when {
        other === this -> this
        this is CombinedTransformation -> when (other) {
            is CombinedTransformation -> CombinedTransformation(
                transformations + other.transformations
            )
            else -> copy(transformations + other)
        }
        else -> CombinedTransformation(
            listOf(
                this,
                other
            )
        )
    }

    companion object : Transformation
}

private data class CombinedTransformation(val transformations: List<Transformation>) :
    Transformation {
    override fun iterator(): Iterator<Transformation> = transformations.iterator()
}

private class SingleItemIterator<T>(private val item: T) : Iterator<T> {

    private var hasNext: Boolean = false

    override fun hasNext(): Boolean = hasNext

    override fun next(): T {
        if (hasNext) {
            hasNext = false
            return item
        } else {
            throw NoSuchElementException()
        }
    }
}

object CircleCropTransformation : Transformation

data class ScaleTypeTransformation(val scaleType: ScaleType) :
    Transformation

data class RoundedCornersTransformation(val radius: Int) :
    Transformation

fun Transformation.circleCrop() = this + CircleCropTransformation

fun Transformation.scaleType(scaleType: ScaleType) = this + ScaleTypeTransformation(
    scaleType
)

fun Transformation.centerCrop() = scaleType(
    ScaleType.CENTER_CROP
)

fun Transformation.centerInside() = scaleType(
    ScaleType.CENTER_INSIDE
)

fun Transformation.roundedCorners(radius: Int) =
    this + RoundedCornersTransformation(radius)

enum class ScaleType {
    CENTER_CROP,
    CENTER_INSIDE,
}
