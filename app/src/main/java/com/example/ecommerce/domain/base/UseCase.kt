package com.example.ecommerce.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import com.example.ecommerce.domain.base.Parameters as IParameters
import com.example.ecommerce.domain.base.Result as IResult

interface UseCase<Parameters: IParameters, Result: IResult> {

    suspend fun execute(params: Parameters): Result

    suspend fun <T>launch(block: suspend CoroutineScope.() -> T): T {
        return withContext(
            context = Dispatchers.IO + NonCancellable,
            block = block
        )
    }

}