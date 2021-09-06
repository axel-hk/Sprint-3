
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import io.mockk.*
import ru.sber.qa.CertificateRequest
import ru.sber.qa.CertificateType
import java.lang.AssertionError
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CertificateRequestTest {

    private val certificateRequest = CertificateRequest(2L, CertificateType.NDFL)

    @Test
    fun testNotNull(){
        assertNotNull(certificateRequest.process(2L))
    }
    @Test
    fun numerShouldBeTheSame(){
        assertEquals(2L, certificateRequest.employeeNumber)
    }
    @Test
    fun typeShouldBeTheSame(){
        assertEquals(CertificateType.NDFL, certificateRequest.certificateType)
    }
}