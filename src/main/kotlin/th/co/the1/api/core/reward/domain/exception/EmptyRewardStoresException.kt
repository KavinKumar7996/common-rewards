package th.co.the1.api.core.reward.domain.exception

import org.springframework.http.HttpStatus

class EmptyRewardStoresException : RewardsApiException() {
    override var message: String? = "Reward stores not available"
    override var statusCode: HttpStatus = HttpStatus.NOT_FOUND
    override var errorCode: String = "UNAVAILABLE_REWARD_STORES"
}
