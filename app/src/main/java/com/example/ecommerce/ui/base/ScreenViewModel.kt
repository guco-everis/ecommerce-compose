package com.example.ecommerce.ui.base

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import java.lang.Exception
import com.example.ecommerce.ui.base.Model as IModel
import com.example.ecommerce.ui.base.Event as IEvent

abstract class ScreenViewModel<Model: IModel, Event: IEvent>(
    initModel: Model? = null,
    initEvent: Event? = null
): ViewModel() {

    var loading by mutableStateOf(false)
        protected set

    var model by mutableStateOf<Model?>(
        value = initModel,
        policy = neverEqualPolicy()
    )
        protected set

    private var listener: (suspend CoroutineScope.(Event?) -> Unit)? = null

    protected var event: Event? = null
        set(value) {
            field = value
            listener?.let {
                try {
                    viewModelScope.launch(
                        context = NonCancellable,
                        block = {
                            it(this, field)
                        }
                    )
                }catch (ex: Exception){
                    Log.w("OnEvent", ex)
                }
            }
        }

    fun onEvent(listener: suspend CoroutineScope.(Event?) -> Unit){
        this.listener = listener
    }

    fun updateModel(){
        model = model
    }

    fun launch(block: suspend CoroutineScope.() -> Unit){
        viewModelScope.launch(
            context = NonCancellable,
            block = block
        )
    }

    init {
        event = initEvent
    }
}