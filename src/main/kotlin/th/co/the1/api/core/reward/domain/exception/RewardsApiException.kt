package th.co.the1.api.core.reward.domain.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

abstract class RewardsApiException : RuntimeException() {
    override var message: String? = null
    abstract var statusCode: HttpStatus
    abstract var errorCode: String
}
