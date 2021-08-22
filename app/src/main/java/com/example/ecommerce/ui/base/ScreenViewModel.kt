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
import com.example.ecommerce.ui.base.State as IState
import com.example.ecommerce.ui.base.Event as IEvent

abstract class ScreenViewModel<State: IState, Event: IEvent>(
    state: State,
    event: Event? = null
): ViewModel() {

    var loading by mutableStateOf(false)

    var state by mutableStateOf(
        value = state,
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

    fun launch(block: suspend CoroutineScope.() -> Unit){
        viewModelScope.launch(
            context = NonCancellable,
            block = block
        )
    }

    fun setState(reduce: State.()-> Unit){
        state.reduce()
        state = state
    }

    init {
        this.event = event
    }

}