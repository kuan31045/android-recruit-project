package `in`.hahow.androidrecruitproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import `in`.hahow.androidrecruitproject.data.repository.CourseRepositoryImpl
import `in`.hahow.androidrecruitproject.domain.repository.CourseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCourseRepository(
        @ApplicationContext context: Context
    ): CourseRepository {
        return CourseRepositoryImpl(context = context)
    }
}