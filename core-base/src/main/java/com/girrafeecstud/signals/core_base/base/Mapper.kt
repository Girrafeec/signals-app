package com.girrafeecstud.signals.core_base.base

interface Mapper<in I, out O> {
    fun map(input: I): O
}