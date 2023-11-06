package `in`.hahow.androidrecruitproject.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import `in`.hahow.android_recruit_project.R
import `in`.hahow.androidrecruitproject.ui.home.HomeScreen

@Composable
fun HahowNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.route,
        modifier = modifier
    ) {
        composable(route = Screen.HOME.route) {
            HomeScreen()
        }
    }
}

enum class Screen(val route: String, @StringRes val titleRes: Int) {
    HOME(route = "home", titleRes = R.string.my_course)
}