package `in`.hahow.androidrecruitproject

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import `in`.hahow.android_recruit_project.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class HahowApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}