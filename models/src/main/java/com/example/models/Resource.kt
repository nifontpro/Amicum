package com.example.models

sealed class Resource<T> {
    data class Success<T>(val data: T? = null) : Resource<T>()
    data class Error<T>(val message: String = "") : Resource<T>()
    class Loading<T> : Resource<T>()
}