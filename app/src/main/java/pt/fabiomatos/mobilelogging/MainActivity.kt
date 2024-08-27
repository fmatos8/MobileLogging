package pt.fabiomatos.mobilelogging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.fabiomatos.mobilelogging.ui.theme.MobileLoggingTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileLoggingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SendLogging(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Object that represents a message sent to the log.
 */
data class MessageSent(val myString: String)

@Composable
fun SendLogging(modifier: Modifier = Modifier) {
    val viewModel = LoggingViewModel()

    var phrase by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp)
    ) {
        Text(
            text = "Hello, please type a log to send!",
            modifier = modifier
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = phrase,
            onValueChange = { phrase = it }
        )
        Button(
            onClick = {
                val message = MessageSent(phrase)
                val gson = Gson()
                viewModel.log(gson.toJson(message))
            }
        ) {
            Text(text = "Log")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileLoggingTheme {
        SendLogging()
    }
}