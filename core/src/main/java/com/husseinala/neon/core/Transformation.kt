package com.husseinala.neon.core

/**
 * A transformation element that's used to apply a specific transformation to the download image and can
 * be combined with other transformations to create a new [CombinedTransformation] instance.
 */
interface Transformation : Iterable<Transformation> {
    override fun iterator(): Iterator<Transformation> = SingleItemIterator(this)

    operator fun plus(other: Transformation): Transformation =
        when {
            other === this -> this
            this is CombinedTransformation ->
                when (other) {
                    is CombinedTransformation ->
                        CombinedTransformation(
                            transformations + other.transformations,
                        )
                    else -> copy(transformations + other)
                }
            else ->
                CombinedTransformation(
                    listOf(
                        this,
                        other,
                    ),
                )
        }

    companion object : Transformation
}

/**
 * An immutable [Transformation] list to be applied to the downloaded image.
 */
private data class CombinedTransformation(val transformations: List<Transformation>) :
    Transformation {
    override fun iterator(): Iterator<Transformation> = transformations.iterator()
}

private class SingleItemIterator<T>(private val item: T) : Iterator<T> {
    private var hasNext: Boolean = true

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
