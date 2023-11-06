package `in`.hahow.androidrecruitproject.domain.model

import androidx.annotation.StringRes
import `in`.hahow.android_recruit_project.R
import java.time.LocalDateTime

data class Course(
    val coverImageUrl: String,
    val numSoldTickets: Int,
    val proposalDueTime: LocalDateTime,
    val status: String,
    val successCriteria: SuccessCriteria,
    val title: String,
    val totalVideoLengthInSeconds: Int? = null
) {
    fun calProgress() = numSoldTickets * 100 / successCriteria.numSoldTickets
}

enum class CourseStatus(
    @StringRes val stringRes: Int
) {
    PUBLISHED(R.string.published),
    INCUBATING(R.string.incubating),
    SUCCESS(R.string.incubating_success)
}