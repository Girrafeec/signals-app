package com.girrafeecstud.signals.core_base.base

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.unique(): Flow<T> =
    flow {
        var lastValue: Any? = NoValue
        collect { value: T ->
            Log.i("tag sos uniq last", lastValue.toString())
            Log.i("tag sos uniq cur", value.toString())
            if (lastValue != value) {
                lastValue = value
                emit(value)
            }
        }
    }

private object NoValue