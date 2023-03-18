package com.girrafeecstud.signals.core_base.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.unique(): Flow<T> =
    flow {
        var lastValue: Any? = NoValue
        collect { value: T ->
            if (lastValue != value) {
                lastValue = value
                emit(value)
            }
        }
    }

private object NoValue