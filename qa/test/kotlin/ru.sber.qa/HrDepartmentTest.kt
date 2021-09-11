package ru.sber.qa

import org.junit.jupiter.api.Test
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset


class HrDepartmentTest {
    val now = System.currentTimeMillis()
    private val fixedClock = Clock.fixed(Instant.ofEpochMilli(now), ZoneId.systemDefault())
    private val certificate = mockk<Certificate>()
    private val certificateRequest = mockk<CertificateRequest>()
    private val hrEmployeeNumber = 1L
    private val department = mockk<HrDepartment>()
    private val request = CertificateRequest(hrEmployeeNumber,CertificateType.NDFL)

    @BeforeEach
    fun `fix the clock `() {
        mockkStatic(Clock::class)
        // Default system clock
        every { department.clock } returns fixedClock

    }
    @Test
    fun receiveRequestDoesNotThrow() {
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-03T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL

        assertDoesNotThrow{HrDepartment.receiveRequest(certificateRequest)}
    }

    @Test
    fun processNextRequestDoesNotThrow() {
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-01T10:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        HrDepartment.receiveRequest(certificateRequest)

        assertDoesNotThrow{
            HrDepartment.processNextRequest(hrEmployeeNumber)}
    }

    @Test
    fun receiveRequestThrowOnWeekend() {

        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-05T10:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.NDFL

        assertThrows(
            WeekendDayException::class.java,
            {HrDepartment.receiveRequest(certificateRequest)},
            "Must throw on any day off"
        )
    }

    @Test
    fun recieveRequestThrowsOnEvenDays() {

        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-02T12:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.NDFL

        assertThrows(
            NotAllowReceiveRequestException::class.java,
            {HrDepartment.receiveRequest(certificateRequest)},
            "Must throw on any even days"
        )
    }

    @Test
    fun recieveRequestThrowsOnOddDays() {
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-03T12:00:00Z"), ZoneOffset.UTC)

        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        assertThrows(NotAllowReceiveRequestException::class.java) {
            HrDepartment.receiveRequest(certificateRequest)
        }
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