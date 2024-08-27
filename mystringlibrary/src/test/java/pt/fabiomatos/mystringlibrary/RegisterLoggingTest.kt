package pt.fabiomatos.mystringlibrary

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RegisterLoggingTest {

    private val testSample: RegisterLogging = RegisterLogging()

    @Test
    fun testSend() = runBlocking {
        // Call the suspend function
        val result = testSample.sendRequestInfoLogging("")

        // Assert the result
        assertEquals("", result)
    }
}