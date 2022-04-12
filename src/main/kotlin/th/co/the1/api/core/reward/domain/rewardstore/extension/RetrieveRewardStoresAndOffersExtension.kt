package th.co.the1.api.core.reward.domain.rewardstore.extension

import org.apache.commons.lang3.StringUtils
import th.co.the1.api.core.reward.domain.assettag.model.AssetTag
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.AssetTagTypeEnum
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.BRAND_TAG_SEPARATOR
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.BRAND_TAG_STRUCTURE_SIZE
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.CATEGORY_TAG_PREFIX_KEY
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.CATEGORY_TAG_SEPARATOR
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.DISPLAY_TAG_SEPARATOR
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.DISPLAY_TAG_STRUCTURE_SIZE
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.LOCATION_TAG_KEYS
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.LOCATION_TAG_SEPARATOR
import th.co.the1.api.core.reward.domain.constant.AssetTagConstant.LOCATION_TAG_STRUCTURE_SIZE
import th.co.the1.api.core.reward.domain.constant.CommonConstant.COLON_SEPARATOR
import th.co.the1.api.core.reward.domain.constant.CommonConstant.COMMA_SEPARATOR
import th.co.the1.api.core.reward.domain.constant.CommonConstant.SLASH_SEPARATOR

fun createAssetTags(sourceTag: String, type: String): List<AssetTag>? {
    return if (isContainTypeInAssetTagType(type)) {
        val assetTags = mutableListOf<AssetTag>()
        val prefix = getPrefixByAssetTagType(type)
        val allTags = prepareTags(sourceTag, prefix)
        allTags.forEach { tags ->
            when (type) {
                AssetTagTypeEnum.BRAND_TAG_TYPE.value -> setAssetTagForBrandTag(tags, assetTags)
                AssetTagTypeEnum.DISPLAY_TAG_TYPE.value -> setAssetTagForDisplayTag(tags, assetTags)
                AssetTagTypeEnum.CATEGORY_TAG_TYPE.value -> setAssetTagCategoryTag(tags, assetTags)
                AssetTagTypeEnum.LOCATION_TAG_TYPE.value -> setAssetTagLocationTag(tags, assetTags)
            }
        }
        return assetTags
    } else {
        null
    }
}

fun isContainTypeInAssetTagType(type: String): Boolean {
    return AssetTagTypeEnum.values().map { it.value }.contains(type)
}

fun getPrefixByAssetTagType(type: String): String {
    return when (type) {
        AssetTagTypeEnum.BRAND_TAG_TYPE.value -> BRAND_TAG_SEPARATOR
        AssetTagTypeEnum.DISPLAY_TAG_TYPE.value -> DISPLAY_TAG_SEPARATOR
        AssetTagTypeEnum.CATEGORY_TAG_TYPE.value -> CATEGORY_TAG_SEPARATOR
        else -> LOCATION_TAG_SEPARATOR
    }
}

fun prepareTags(sourceTag: String, prefix: String): List<List<String>> {
    return sourceTag.split(COMMA_SEPARATOR).mapNotNull {
        if (it.startsWith(prefix)) {
            it.substringAfter(prefix).split(SLASH_SEPARATOR)
        } else null
    }
}

private fun setAssetTagForBrandTag(
    tags: List<String>,
    assetTags: MutableList<AssetTag>,
) {
    if (tags.size == BRAND_TAG_STRUCTURE_SIZE && tags[0].isNotBlank()) {
        assetTags.add(
            AssetTag().also {
                it.value = tags[0].trim()
            }
        )
    }
}

private fun setAssetTagForDisplayTag(
    tags: List<String>,
    assetTags: MutableList<AssetTag>,
) {
    if (tags.size == DISPLAY_TAG_STRUCTURE_SIZE) {
        assetTags.add(
            AssetTag().also {
                it.groupName = tags[0].trim()
                it.key = tags[1].trim()
                it.value = tags[2].trim()
            }
        )
    }
}

private fun setAssetTagCategoryTag(
    tags: List<String>,
    assetTags: MutableList<AssetTag>,
) {
    tags.forEachIndexed { index, tag ->
        if (tag.isNotBlank()) {
            assetTags.add(setAssetTagForCategoryTag(tags, tag, index))
        }
    }
}

private fun setAssetTagLocationTag(
    tags: List<String>,
    assetTags: MutableList<AssetTag>,
) {
    if (tags.size <= LOCATION_TAG_STRUCTURE_SIZE) {
        tags.forEachIndexed { index, tag ->
            if (tag.isNotBlank()) {
                assetTags.add(setAssetTagForLocationTag(tags, tag, index))
            }
        }
    }
}

private fun setAssetTagForCategoryTag(
    tags: List<String>,
    tag: String,
    index: Int,
): AssetTag {
    return AssetTag().also {
        it.key = CATEGORY_TAG_PREFIX_KEY.plus(index)
        it.value = tag.trim()
        it.rootName = calculateRootName(tags, index)
    }
}

private fun setAssetTagForLocationTag(
    tags: List<String>,
    tag: String,
    index: Int,
): AssetTag {
    return AssetTag().also {
        it.key = LOCATION_TAG_KEYS[index]
        it.value = tag.trim()
        it.rootName = calculateRootName(tags, index)
    }
}

private fun calculateRootName(tags: List<String>, index: Int): String {
    return if (index == 0) {
        StringUtils.EMPTY
    } else {
        tags.subList(0, index).joinToString(COLON_SEPARATOR).trim()
    }
}

fun validateAssetTags(assetTags: String, assetTagSeparator: String): Boolean {
    return assetTags.isNotBlank() && assetTags.contains(assetTagSeparator)
}

fun String.filterAssetTags(assetTagSeparator: String): String? {
    return if (this.isBlank()) {
        null
    } else {
        var assetTags = this.split(COMMA_SEPARATOR).filter {
            it.startsWith(assetTagSeparator)
        }
        assetTags = when (assetTagSeparator) {
            BRAND_TAG_SEPARATOR -> assetTags.filterAssetTagForBrandTag()
            CATEGORY_TAG_SEPARATOR -> assetTags.filterAssetTagForCategoryTag()
            DISPLAY_TAG_SEPARATOR -> assetTags.filterAssetTagForDisplayTag()
            else -> assetTags.filterAssetTagForLocationTag()
        }
        assetTags.joinToString(COMMA_SEPARATOR)
    }
}

fun List<String>.filterAssetTagForBrandTag(): List<String> {
    return this.filter {
        val tags = getAssetTagWithAssetTagSeparator(it, BRAND_TAG_SEPARATOR)
        (tags != null) && (tags.size == BRAND_TAG_STRUCTURE_SIZE)
    }
}

fun List<String>.filterAssetTagForCategoryTag(): List<String> {
    return this.filter {
        getAssetTagWithAssetTagSeparator(it, CATEGORY_TAG_SEPARATOR)?.isNotEmpty() == true
    }
}

fun List<String>.filterAssetTagForDisplayTag(): List<String> {
    return this.filter {
        val tags = getAssetTagWithAssetTagSeparator(it, DISPLAY_TAG_SEPARATOR)
        (tags != null) && (tags.size == DISPLAY_TAG_STRUCTURE_SIZE)
    }
}

fun List<String>.filterAssetTagForLocationTag(): List<String> {
    return this.filter {
        val tags = getAssetTagWithAssetTagSeparator(it, LOCATION_TAG_SEPARATOR)
        (tags != null) && (tags.size <= LOCATION_TAG_STRUCTURE_SIZE)
    }
}

private fun getAssetTagWithAssetTagSeparator(
    assetTag: String,
    assetTagSeparator: String,
): List<String>? {
    val tag = assetTag.substringAfter(assetTagSeparator)
    return if (tag.isNotEmpty()) tag.split(SLASH_SEPARATOR) else null
}
