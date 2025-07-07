package ui.screen.main

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Robot
import java.awt.event.KeyEvent
import java.util.*
import kotlin.concurrent.timer


@Composable
@Preview
fun App() {

    var isRunning by remember { mutableStateOf(false) }
    val robot = remember { Robot() }
    var keyPressCount by remember { mutableStateOf(0) }

    // Timer untuk menekan tombol X secara otomatis
    var timerTask by remember { mutableStateOf<Timer?>(null) }

    fun startAutoPress() {
        isRunning = true
        timerTask = timer(initialDelay = 0, period = 100) { // Tekan setiap 100ms
            robot.keyPress(KeyEvent.VK_X)
            robot.keyRelease(KeyEvent.VK_X)
            keyPressCount++
        }
    }

    fun stopAutoPress() {
        isRunning = false
        timerTask?.cancel()
        timerTask = null
    }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isRunning) "Menekan X otomatis..." else "Program Auto-Clicker X",
                style = MaterialTheme.typography.h5
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Tombol X ditekan: $keyPressCount kali",
                style = MaterialTheme.typography.body1
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    if (isRunning) stopAutoPress() else startAutoPress()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isRunning) MaterialTheme.colors.error else MaterialTheme.colors.primary
                )
            ) {
                Text(if (isRunning) "STOP" else "START")
            }
        }
    }
}


fun main() = application {
    Window(
        title = "Samquinor Clicker",
        resizable = false,
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
