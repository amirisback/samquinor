package ui.screen.main

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.theme.dimen_16dp
import util.PROMPT_SHUTDOWN_CANCEL
import util.runPrompt
import util.shutdownPrompt

@Composable
@Preview
fun App() {

    MaterialTheme {

        Row(
            modifier = Modifier.padding(dimen_16dp).fillMaxSize()
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterVertically).fillMaxSize()
            ) {

                Text(
                    text = "Input your shutdown timer in seconds",
                    modifier = Modifier.fillMaxWidth()
                )

                var textField by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp),
                    value = textField,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        textField = it
                    }
                )
                Spacer(Modifier.weight(1f))
                Row() {
                    Button(
                        modifier = Modifier.weight(1f).padding(end = 8.dp),
                        onClick = {
                            runPrompt(shutdownPrompt(textField.toInt()))
                        }) {
                        Text("Set Shutdown Timer")
                    }

                    Button(
                        modifier = Modifier.weight(1f).padding(start = 8.dp),
                        onClick = {
                            runPrompt(PROMPT_SHUTDOWN_CANCEL)
                        }) {
                        Text("Reset")
                    }
                }

            }
        }

    }
}


fun main() = application {
    Window(
        title = "Shutdown Timer",
        resizable = false,
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
