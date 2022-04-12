package th.co.the1.api.core.reward.domain.reward.model

import java.io.Serializable

data class RewardMedia(
    var id: String? = null,
    var offerId: String? = null,
    var uri: String? = null,
    var categoryId: String? = null,
    var categoryName: String? = null,
    var contentType: Int? = null,
    var culture: String? = null
) : Serializable
