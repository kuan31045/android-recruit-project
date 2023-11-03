package `in`.hahow.androidrecruitproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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

        }
    }
}

enum class Screen(val route: String) {
    HOME(route = "home")
}