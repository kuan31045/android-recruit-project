package `in`.hahow.androidrecruitproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import `in`.hahow.androidrecruitproject.ui.HahowApp
import `in`.hahow.androidrecruitproject.ui.theme.HahowTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HahowTheme {
                HahowApp()
            }
        }
    }
}