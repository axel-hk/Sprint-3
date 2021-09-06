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
    private val department = mockkClass(HrDepartment::class)
    private val request = CertificateRequest(1L,CertificateType.NDFL)

    @BeforeEach
    fun `fix the clock `() {
        mockkStatic(Clock::class)
        // Default system clock
        every { department.clock } returns fixedClock

    }
    @Test
    fun testWeekendDayException(){

        every { department.receiveRequest(request) } throws WeekendDayException()

    }

    @Test
    fun testNotAllowRecieveRequest(){
        every { department.receiveRequest(request) } throws  NotAllowReceiveRequestException()
    }

}