package `in`.hahow.androidrecruitproject.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import `in`.hahow.android_recruit_project.R
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Duration
import kotlin.math.abs

data class Course(
    val coverImageUrl: String,
    val numSoldTickets: Int,
    val proposalDueTime: LocalDateTime? = null,
    val status: CourseStatus,
    val successCriteria: SuccessCriteria,
    val title: String,
    val totalVideoLengthInSeconds: Int? = null
) {
    fun calProgress() = numSoldTickets * 100 / successCriteria.numSoldTickets

    fun calCountDownDay() = abs(Duration.between(proposalDueTime, LocalDateTime.now()).toDays())
}

enum class CourseStatus(
    @StringRes val stringRes: Int
) {
    PUBLISHED(R.string.published),
    INCUBATING(R.string.incubating),
    SUCCESS(R.string.incubating_success)
}