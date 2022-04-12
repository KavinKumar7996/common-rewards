package th.co.the1.api.core.reward.domain.exception

import org.springframework.http.HttpStatus

class DataNotFoundException(override var message: String?) : RewardsApiException() {
    override var statusCode = HttpStatus.NOT_FOUND
    override var errorCode = ERROR_CODE

    companion object {
        const val ERROR_CODE = "DATA_NOT_FOUND"
    }
}
