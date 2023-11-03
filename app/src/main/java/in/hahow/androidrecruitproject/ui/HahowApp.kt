package `in`.hahow.androidrecruitproject.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import `in`.hahow.androidrecruitproject.ui.navigation.HahowNavGraph

@Composable
fun HahowApp(navController: NavHostController = rememberNavController()) {
    HahowNavGraph(navController = navController)
}