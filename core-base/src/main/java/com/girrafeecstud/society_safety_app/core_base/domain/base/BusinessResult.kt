package com.girrafeecstud.society_safety_app.core_base.domain.base

import com.girrafeecstud.society_safety_app.core_base.base.ExceptionType

sealed class BusinessResult<out T> (
)
{
    data class Success<out R> (val _data: R?): BusinessResult<R> ()
    data class Error (val businessErrorType: BusinessErrorType): BusinessResult<Nothing> ()
    data class Exception (val exceptionType: ExceptionType) : BusinessResult<Nothing> ()
}