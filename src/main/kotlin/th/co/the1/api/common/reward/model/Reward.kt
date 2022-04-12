package th.co.the1.api.common.reward.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import th.co.the1.api.core.reward.application.serializer.MobileDateSerializer
import java.io.Serializable
import java.util.Date

class Reward : Serializable {
    var id: String? = null
    var rootOfferId: String? = null
    var title: String? = null
    var titleEn: String? = null
    var offerShortDescription: String? = null
    var offerShortDescriptionEn: String? = null
    var imageUrls: List<RewardMedia>? = null
    var remainingDays: Long? = null

    @JsonSerialize(using = MobileDateSerializer::class)
    var startDate: Date? = null

    @JsonSerialize(using = MobileDateSerializer::class)
    var endDate: Date? = null
    var targetedTier: String? = null
    var siebelPoints: Int? = null

    @JsonSerialize(using = MobileDateSerializer::class)
    var acquisitionStartDate: Date? = null

    @JsonSerialize(using = MobileDateSerializer::class)
    var acquisitionEndDate: Date? = null
    var partnerCode: String? = null
    var partnerName: String? = null
    var issueChannel: String? = null
    var voucherType: String? = null
    var category: String? = null
    var offerRewardType: String? = null
    var campaignAttribute: String? = null
    var brandTag: String? = null
    var tag: String? = null
    var displayTag: String? = null
    var locationTag: String? = null
}
