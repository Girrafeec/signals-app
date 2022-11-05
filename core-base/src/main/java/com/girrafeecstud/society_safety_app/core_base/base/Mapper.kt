package com.girrafeecstud.society_safety_app.core_base.base

interface Mapper<in I, out O> {
    fun map(input: I): O
}