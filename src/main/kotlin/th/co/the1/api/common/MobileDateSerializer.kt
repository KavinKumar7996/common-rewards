package th.co.the1.api.common

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.apache.commons.lang3.StringUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class MobileDateSerializer : JsonSerializer<Date>() {

    override fun serialize(value: Date?, gen: JsonGenerator, provider: SerializerProvider?) {
        val format = SimpleDateFormat(RewardUtils.DATE_FORMAT)
        format.timeZone = TimeZone.getTimeZone(RewardUtils.TIME_ZONE)
        gen.writeString(value?.let { format.format(value) } ?: StringUtils.EMPTY)
    }
}
