package ru.sber.qa

import org.junit.jupiter.api.Test
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import kotlin.test.assertNotNull

class HrDepartmentTest {
    val now = System.currentTimeMillis()
    private val fixedClock = Clock.fixed(Instant.ofEpochMilli(now), ZoneId.systemDefault())
    private val department = mockk<HrDepartment>()

    @BeforeEach
    fun `fix the clock `() {
        mockkStatic(Clock::class)
        // Default system clock
        every { department.clock } returns fixedClock

    }
    @Test
    fun test(){

        every { department.receiveRequest(CertificateRequest(1L,CertificateType.NDFL)) } throws WeekendDayException()
        every { department.receiveRequest(CertificateRequest(1L,CertificateType.NDFL)) } throws  NotAllowReceiveRequestException()

    }

}