package `in`.hahow.androidrecruitproject.domain.repository

import `in`.hahow.androidrecruitproject.data.Result
import `in`.hahow.androidrecruitproject.domain.model.Course

interface CourseRepository {

    suspend fun fetchCourse(): Result<List<Course>>
}