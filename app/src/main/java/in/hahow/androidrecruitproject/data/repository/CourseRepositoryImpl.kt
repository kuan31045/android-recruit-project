package `in`.hahow.androidrecruitproject.data.repository

import `in`.hahow.androidrecruitproject.data.Result
import `in`.hahow.androidrecruitproject.domain.model.Course
import `in`.hahow.androidrecruitproject.domain.repository.CourseRepository

class CourseRepositoryImpl() : CourseRepository {

    override fun fetchCourse(): Result<List<Course>> {
        TODO("Not yet implemented")
    }
}