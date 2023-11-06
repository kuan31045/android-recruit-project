package `in`.hahow.androidrecruitproject.data.remote.dto

import `in`.hahow.androidrecruitproject.domain.model.Course
import `in`.hahow.androidrecruitproject.domain.model.CourseStatus
import `in`.hahow.androidrecruitproject.domain.model.SuccessCriteria
import `in`.hahow.androidrecruitproject.util.parseToLocalDateTime

data class CourseResponse(
    val data: List<CourseDto>
)

data class CourseDto(
    val coverImageUrl: String,
    val numSoldTickets: Int,
    val proposalDueTime: String? = null,
    val status: String,
    val successCriteria: SuccessCriteria,
    val title: String,
    val totalVideoLengthInSeconds: Int? = null
) {
    fun toCourse(): Course {
        return Course(
            coverImageUrl = coverImageUrl,
            numSoldTickets = numSoldTickets,
            proposalDueTime = proposalDueTime?.let { parseToLocalDateTime(proposalDueTime) },
            status = CourseStatus.valueOf(status),
            successCriteria = successCriteria,
            title = title,
            totalVideoLengthInSeconds = totalVideoLengthInSeconds
        )
    }
}