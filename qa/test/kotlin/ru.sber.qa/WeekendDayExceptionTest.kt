package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WeekendDayExceptionTest {
    private val exceptionTest = mockk<WeekendDayException>()
    @Test
    fun test(){
        every{exceptionTest.message} returns "Заказ справков в выходной день не работает"

    }
}