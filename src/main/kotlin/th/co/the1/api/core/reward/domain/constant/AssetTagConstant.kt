package th.co.the1.api.core.reward.domain.constant

object AssetTagConstant {

    const val BRAND_TAG_SEPARATOR = "Asset Tag : Brand /"
    const val CATEGORY_TAG_SEPARATOR = "Asset Tag : Category /"
    const val DISPLAY_TAG_SEPARATOR = "Asset Tag : Additional Tag /"
    const val LOCATION_TAG_SEPARATOR = "Asset Tag : Location /"

    const val CATEGORY_TAG_PREFIX_KEY = "Category"

    const val LOCATION_TAG_KEY_WORLD = "World"
    const val LOCATION_TAG_KEY_REGION = "Region"
    const val LOCATION_TAG_KEY_PROVINCE = "Province"
    const val LOCATION_TAG_KEY_LOCATION = "Location"
    val LOCATION_TAG_KEYS =
        listOf(LOCATION_TAG_KEY_WORLD, LOCATION_TAG_KEY_REGION, LOCATION_TAG_KEY_PROVINCE, LOCATION_TAG_KEY_LOCATION)

    const val BRAND_TAG_STRUCTURE_SIZE = 1
    const val DISPLAY_TAG_STRUCTURE_SIZE = 3
    const val LOCATION_TAG_STRUCTURE_SIZE = 4

    enum class AssetTagTypeEnum(val value: String) {
        BRAND_TAG_TYPE("brand"),
        CATEGORY_TAG_TYPE("category"),
        DISPLAY_TAG_TYPE("display"),
        LOCATION_TAG_TYPE("location")
    }
}
