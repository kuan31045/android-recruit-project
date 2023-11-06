package `in`.hahow.androidrecruitproject.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun parseToLocalDateTime(dateTimeString: String): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    return LocalDateTime.parse(dateTimeString, formatter)
}