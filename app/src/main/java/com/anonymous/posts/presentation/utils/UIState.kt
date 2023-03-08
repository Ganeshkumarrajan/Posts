package com.anonymous.posts.presentation.utils

sealed class UIState<T> {
    class Nothing<T> : UIState<T>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val message: String) : UIState<T>()
}
