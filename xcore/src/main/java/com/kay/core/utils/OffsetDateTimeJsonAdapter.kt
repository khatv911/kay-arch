package com.kay.core.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter


/**
 * Created by Kay Tran on 3/6/18.
 * Profile : https://github.com/khatv911
 * Email   : khatv911@gmail.com
 */
class OffsetDateTimeJsonAdapter: JsonAdapter<OffsetDateTime>() {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    override fun fromJson(reader: JsonReader?): OffsetDateTime? {
        val str = reader?.nextString()
       return formatter.parse(str, OffsetDateTime::from)
    }

    override fun toJson(writer: JsonWriter?, value: OffsetDateTime?) {
        writer?.value(formatter.format(value))
    }
}