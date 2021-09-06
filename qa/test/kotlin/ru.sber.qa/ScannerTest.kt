package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class ScannerTest {

    @Test
    fun `getScanData()  throw ScanTimeoutException `() {
        mockkObject(Random)
        every { Random.nextLong(5000L, 15000L) } returns 11000L
        assertThrows<ScanTimeoutException> { Scanner.getScanData() }
    }

    @Test
    fun test() {
        val expectedData = Random.nextBytes(100)

        mockkObject(Random)

        every { Random.nextBytes(100) } returns expectedData

        val actualData = Scanner.getScanData()

        assertArrayEquals(expectedData, actualData)
    }
    @AfterEach
    fun unmockk() {
        unmockkAll()
    }
}