package com.girrafeecstud.society_safety_app.feature_auth

import com.girrafeecstud.society_safety_app.core_base.domain.base.BusinessResult
import com.girrafeecstud.society_safety_app.feature_auth.data.datasource.UserLoginDataSource
import com.girrafeecstud.society_safety_app.feature_auth.data.repository.UserLoginRepositoryImpl
import com.girrafeecstud.society_safety_app.feature_auth.domain.entity.UserLoginEntity
import com.girrafeecstud.society_safety_app.feature_auth.domain.repository.UserLoginRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

class UserLoginRepositoryImplUnitTest {

    private lateinit var repository: UserLoginRepository

    private val loginDataSource: UserLoginDataSource = mock()

    @Before
    fun setUp() {
        repository = UserLoginRepositoryImpl(dataSource = loginDataSource)
    }

    @Test
    fun `EXPECT BusinessResult Success WHEN call repository login method` () {
        runBlocking {
            val expectedResult = BusinessResult.Success(_data = null)

            var actualResult: BusinessResult<Nothing>? = null

            whenever(
                loginDataSource.login(UserLoginEntity(userPhoneNumber = "89312315120", userPassword = "qwertyuiop"))
            ).thenReturn(
                flow {
                    emit(BusinessResult.Success(_data = UUID.fromString("b35b1b78-4952-11ed-8353-00f48dc19557")))
                }
            )

            repository.login(UserLoginEntity(userPhoneNumber = "89312315120", userPassword = "qwertyuiop"))
                .collect { result ->
                    when (result) {
                        is BusinessResult.Success -> {
                            actualResult = result
                        }
                    }
                }

            Assert.assertEquals(expectedResult, actualResult)
        }
    }

    @After
    fun destroy() {

    }

}