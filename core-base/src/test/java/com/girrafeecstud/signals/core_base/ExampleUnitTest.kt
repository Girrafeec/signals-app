package com.girrafeecstud.signals.core_base

import com.girrafeecstud.signals.core_base.base.ExceptionType
import com.girrafeecstud.signals.core_base.domain.base.BusinessResult
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val result = getResult()

        when(result) {
            is BusinessResult.Success -> {
                println(result._data)
            }
            is BusinessResult.Error -> {
                println(result.businessErrorType)
            }
            is BusinessResult.Exception -> {
                println(result.exceptionType)
            }
        }
    }

    fun getResult(): BusinessResult<String> {
        return BusinessResult.Exception(ExceptionType.NO_INTERNET_CONNECTION)
        //return BusinessResult.Error(BusinessErrorType.USER_ALREADY_EXISTS)
        //return BusinessResult.Success("result")
    }

}