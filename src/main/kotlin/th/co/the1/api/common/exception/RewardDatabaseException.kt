package th.co.the1.api.common.exception

import org.springframework.http.HttpStatus

class RewardDatabaseException(override var message: String?) : RewardsApiException() {
    override var statusCode = HttpStatus.INTERNAL_SERVER_ERROR
    override var errorCode = ERROR_CODE

    companion object {
        const val ERROR_CODE = "REWARD_DATABASE_UNAVAILABLE"
    }
}
