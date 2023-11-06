package `in`.hahow.androidrecruitproject.data.repository

import android.content.Context
import com.google.gson.Gson
import `in`.hahow.androidrecruitproject.data.MockAssetManager
import `in`.hahow.androidrecruitproject.data.Result
import `in`.hahow.androidrecruitproject.data.remote.dto.CourseResponse
import `in`.hahow.androidrecruitproject.domain.model.Course
import `in`.hahow.androidrecruitproject.domain.repository.CourseRepository
import timber.log.Timber

class CourseRepositoryImpl(
    private val context: Context,
) : CourseRepository {

    override suspend fun fetchCourse(): Result<List<Course>> {
        return try {
            val json = MockAssetManager.loadCourseJSONFromAsset(context)
            val result = Gson().fromJson(json, CourseResponse::class.java)
            Result.Success(result.data.map { it.toCourse() })
        } catch (e: Exception) {
            Timber.w("fetch courses exception: ${e.message}")
            Result.Error(e)
        }
    }
}