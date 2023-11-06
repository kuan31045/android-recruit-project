package `in`.hahow.androidrecruitproject.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.hahow.android_recruit_project.R
import `in`.hahow.androidrecruitproject.data.Result
import `in`.hahow.androidrecruitproject.domain.model.Course
import `in`.hahow.androidrecruitproject.domain.repository.CourseRepository
import `in`.hahow.androidrecruitproject.util.LoadingStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val courses: List<Course> = emptyList(),
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var loadingState: LoadingStatus by mutableStateOf(LoadingStatus.Loading)
        private set

    init {
        fetchCourses()
    }

     fun fetchCourses() {
        viewModelScope.launch {
            loadingState = LoadingStatus.Loading
            delay(200)

            val result = courseRepository.fetchCourse()

            loadingState = when (result) {
                is Result.Success -> {
                    _uiState.update { currentState ->
                        currentState.copy(courses = result.data)
                    }
                    LoadingStatus.Done
                }

                is Result.Error -> LoadingStatus.Error(R.string.fetch_failed)

                else -> LoadingStatus.Loading
            }
        }
    }
}