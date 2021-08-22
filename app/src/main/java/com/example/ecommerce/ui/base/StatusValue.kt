package com.example.ecommerce.ui.base

sealed class StatusValue<T>(value: T) {

    var value: T = value

    class Error<T>(
        val msg: String,
        val cause: Exception? = null,
        value: T
    ): StatusValue<T>(value = value)

    class Success<T>(value: T): StatusValue<T>(value = value)

    class Loading<T>(value: T): StatusValue<T>(value = value)

    class LoadMore<T>(value: T): StatusValue<T>(value = value)

    fun toError(
        msg: String,
        cause: Exception? = null,
        newValue: T? = null
    ): StatusValue.Error<T>{
        return StatusValue.Error(
            msg = msg,
            cause = cause,
            value = newValue ?: value
        )
    }

    fun toSuccess(newValue: T): StatusValue.Success<T> = Success(newValue ?: value)

    fun toLoading(newValue: T): StatusValue.Loading<T> = Loading(newValue ?: value)

    fun toLoadMore(newValue: T): StatusValue.LoadMore<T> = LoadMore(newValue ?: value)

}
