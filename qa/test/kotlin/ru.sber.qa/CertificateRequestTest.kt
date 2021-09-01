
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import io.mockk.*
import ru.sber.qa.CertificateRequest
import ru.sber.qa.CertificateType
import java.lang.AssertionError
import kotlin.test.assertNotNull

class CertificateRequestTest {
   private val request = mockk<CertificateRequest>()

    @Test
    fun test(){
        assertNotNull(CertificateRequest(2L, CertificateType.NDFL).process(2L))
    }
}