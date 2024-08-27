package pt.fabiomatos.mobilelogging

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.fabiomatos.mystringlibrary.RegisterLogging
import java.io.IOException

class LoggingViewModel () : ViewModel() {

    /**
     * Viewmodel function to send a message to the server
     *
     * @param msg: Message to be sent to the server
     */
    fun log(msg: String) {
        val registerLogging = RegisterLogging()
        Log.e("ViewModel", "LoggingViewModel MESSAGE: $msg")
        viewModelScope.launch {
            try {
                val response = registerLogging.sendRequestInfoLogging(msg)
                Log.e("ViewModel", "LoggingViewModel RESPONSE: $response")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}