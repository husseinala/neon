package com.husseinala.neon.core

sealed class NeonState<out T> {
    object Loading : NeonState<Nothing>()

    data class Success<T>(val result: T) : NeonState<T>()

    data class Error(val error: Throwable) : NeonState<Nothing>()
}
