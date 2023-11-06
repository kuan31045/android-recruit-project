package `in`.hahow.androidrecruitproject.util

import androidx.annotation.StringRes

sealed interface LoadingStatus {
    object Done : LoadingStatus
    data class Error(@StringRes val stringRes: Int) : LoadingStatus
    object Loading : LoadingStatus
}