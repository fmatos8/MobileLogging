package pt.fabiomatos.mystringlibrary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class RegisterLogging {
    private val client = OkHttpClient()

    @Throws(IOException::class)
    /**
     * Send message log to a server.
     *
     * @param json: the message to send in json, in a string format.
     */
    suspend fun sendRequestInfoLogging(json: String): String? {

        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val requestBody = json.toRequestBody(mediaType)
        val url = "https://us-central1-mobilesdklogging.cloudfunctions.net/saveString"
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    response.body?.string()
                } else {
                    throw IOException("Unexpected code $response")
                }
            }
        }
    }
}