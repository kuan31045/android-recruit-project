package `in`.hahow.androidrecruitproject.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.hahow.androidrecruitproject.domain.model.Course
import `in`.hahow.androidrecruitproject.util.LoadingStatus
import `in`.hahow.androidrecruitproject.util.PreviewData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val courses: List<Course> = emptyList(),
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var loadingState: LoadingStatus by mutableStateOf(LoadingStatus.Loading)
        private set

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            loadingState = LoadingStatus.Loading
            delay(200)
            _uiState.update { currentState ->
                currentState.copy(
                    courses = PreviewData.courses
                )
            }
            loadingState = LoadingStatus.Done
        }
    }
}