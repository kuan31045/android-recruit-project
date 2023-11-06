package `in`.hahow.androidrecruitproject.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.nio.charset.Charset

object MockAssetManager {

    private const val fileName = "data.json"

    suspend fun loadCourseJSONFromAsset(context: Context): String? = withContext(Dispatchers.IO) {
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}