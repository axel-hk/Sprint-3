package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ScannerTest {
    private val scan = mockk<Scanner>()
    @Test
    fun test(){
        every { scan.getScanData() } returns Random.nextBytes(100)
        every { scan.getScanData() } throws ScanTimeoutException()
    }
}