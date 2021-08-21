package com.example.ecommerce.ui.base

sealed class StatusValue<T>(initValue: T? = null) {

    var value: T? = initValue

    class Error<T>(
        val msg: String,
        val cause: Exception? = null,
        initValue: T? = null
    ): StatusValue<T>(initValue = initValue)

    class Success<T>(initValue: T? = null): StatusValue<T>(initValue = initValue)

    class Loading<T>(initValue: T? = null): StatusValue<T>(initValue = initValue)

    class LoadMore<T>(initValue: T? = null): StatusValue<T>(initValue = initValue)

    fun toError(
        msg: String,
        cause: Exception? = null,
        newValue: T? = null
    ): StatusValue.Error<T>{
        return StatusValue.Error(
            msg = msg,
            cause = cause,
            initValue = newValue ?: value
        )
    }

    fun toSuccess(newValue: T? = null): StatusValue.Success<T> = Success(newValue ?: value)

    fun toLoading(newValue: T? = null): StatusValue.Loading<T> = Loading(newValue ?: value)

    fun toLoadMore(newValue: T? = null): StatusValue.LoadMore<T> = LoadMore(newValue ?: value)

}
