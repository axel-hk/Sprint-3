package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NotAllowReceiveRequestExceptionTest {
    private val exceptionTest = mockk<NotAllowReceiveRequestException>()
    @Test
    fun test(){
        every{exceptionTest.message} returns "Не разрешено принять запрос на справку"

    }

}