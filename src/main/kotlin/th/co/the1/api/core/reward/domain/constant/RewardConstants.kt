package th.co.the1.api.core.reward.domain.constant

object RewardConstants {
    const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val NEW_DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.S'Z'"
    const val TIMEZONE_UTC = "UTC"
    const val ACCEPT_LANGUAGE: String = "accept-language"
    const val CLIENT_ID = "client_id"
    const val CLIENT_SECRET = "client_secret"
    const val SOURCE_TXN_ID = "sourceTransID"
    const val REQUEST_TIME = "requestTime"
    const val PARTNER_CODE = "partnerCode"
    const val TRANSACTION_CHANNEL = "transactionChannel"
    const val CONTENT_TYPE_HEADER = "Content-Type"
    const val PARTNER_CODE_VALUE = "T1C TH"
    const val LANG_PREF = "languagePreference"
    const val ACQUIRE_DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
    const val ACQUIRE_DATE_FORMAT_1: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val ACQUIRE_DATE_FORMAT_2: String = "yyyy-MM-dd'T'HH:mm:ss.SSSSS'Z'"
    const val LANGUAGE_TH = "th"
    const val REWARD = "reward"
    const val MAX_OFFER_SIZE = 1000
    const val SIMPLE_DATE_FORMAT: String = "yyyy-MM-dd HH:mm:ss"
    const val SESSIONM_REWARD_DATE_FORMAT: String = "ddMMyyyy_HH:mm:ss:SSS"
    const val SESSIONM_REWARD_DATE_FORMAT_NEW: String = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'"
    const val SESSIONM_ATTRIBUTE_START_DATE: String = "start_date"
    const val SORT_DIRECTION_ASC: String = "asc"
    const val SORT_DIRECTION_DESC: String = "desc"
    const val ELIGIBILITY_MODE_INVITE: String = "invite"
    const val ELIGIBILITY_MODE_PUBLIC: String = "public"
    const val ELIGIBILITY_MODE_TIER: String = "tier"
    const val DEFAULT_SKIP = 0
    const val SKIP_100 = 100
    const val SKIP_1000 = 1000
    const val TAKE_100 = 100
    const val TAKE_1000 = 1000
    const val CULTURE_TH = "th-TH"
    const val CULTURE_US = "en-US"
    const val MAX_VERSION = 5
    const val DEFAULT_MIN_POINT = 0
    const val DEFAULT_MAX_POINT = 99999999
    const val DEFAULT_PAGE_NO = 1
    const val DEFAULT_PAGE_SIZE = 24
    const val SORT_COLUMN = "start_date"
    const val GROUP_SIMILAR = "similar"
    const val SIMILAR_TYPE_ALL = "All"
    val SIMILAR_VOUCHER_TYPES = listOf("2", "5", "6", "8", "9")
    const val T1X_REWARD_TYPE = "T1X-Reward"
    const val DEFAULT_DISPLAY_ON_EXPLORE = "true"

    enum class DisplayOnExplore(val flag: String) {
        TRUE("1"),
        FALSE("0")
    }
}
