package th.co.the1.api.core.reward.domain.constant

object RewardDatabaseConstants {
    const val OFFER_END_DATE = "endDate"
    const val OFFER_ID = "id"
    const val OFFER_PARTNER_CODE = "partnerCode"
    const val OFFER_PARTNER_NAME = "partnerName"
    const val OFFER_PRICE = "price"
    const val OFFER_REWARD_TYPE = "offerRewardType"
    const val OFFER_ROOT_OFFER_ID = "rootOfferId"
    const val OFFER_START_DATE = "startDate"
    const val OFFER_TAGS = "tags"
    const val OFFER_TITLE_EN = "titleEn"
    const val OFFER_VOUCHER_TYPE = "voucherType"
    const val REWARD_STORE_ATTRIBUTE = "rewardStores"
    const val REWARD_STORE_ID = "rewardStoreId"
    const val REWARD_STORE_DISPLAY_FOR_GUEST = "displayForGuest"
    const val REWARD_STORE_DISPLAY_ON_EXPLORE = "displayOnExplore"
    const val REWARD_STORE_DISPLAY_ORDER = "displayOrder"
    const val REWARD_STORE_INTERNAL_NAME = "internalName"
    const val REWARD_STORE_TYPE = "type"
    const val VERSION = "version"
    const val KEY = "key"
    const val VALUE = "value"
    const val GROUP_NAME = "groupName"
    const val ROOT_NAME = "rootName"
    const val BRAND_TAGS = "brandTags"
    const val LOCATION_TAGS = "locationTags"
    const val DISPLAY_TAGS = "displayTags"
    const val CATEGORY_TAGS = "categoryTags"
    const val TRUE = "true"
    const val FALSE = "false"

    enum class DisplayForGuest(val value: String) {
        GUEST("1"),
        NON_GUEST("0"),
    }

    enum class DisplayOnExplore(val value: String) {
        TRUE("1"),
        FALSE("0"),
    }
}
