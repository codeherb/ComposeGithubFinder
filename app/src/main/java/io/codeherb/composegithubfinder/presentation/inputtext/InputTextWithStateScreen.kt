package io.codeherb.composegithubfinder.presentation.inputtext

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.codeherb.composegithubfinder.presentation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun InputTextWithStateScreen() = Screen(title = "텍스트 입력 테스트") {
    var contents by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("InputTextWithState",
                modifier = Modifier.fillMaxWidth()
                    .background(Color.Yellow),
                textAlign = TextAlign.Center
            )
            AnimatedVisibility(
                visible = contents.isNotEmpty(),
                modifier = Modifier.fillMaxWidth().defaultMinSize(100.dp),
            ) {
                Text(
                    text = contents,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            InputArea(modifier = Modifier.fillMaxWidth()) { msg ->
                coroutineScope.launch {
                    contents = ""
                    delay(500)
                    contents = msg
                }
            }
        }

    }
}

@ExperimentalComposeUiApi
@Composable
fun InputArea(modifier: Modifier, onInputMessage: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }
    fun sendInputMessage() {
        onInputMessage(inputText)
        inputText = ""
    }
    Row(modifier = modifier) {
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            modifier = Modifier
                .weight(1f)
                .onKeyEvent { keyEvent ->
                    if (keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.Enter) {
                        sendInputMessage()
                        return@onKeyEvent true
                    }
                    return@onKeyEvent false
                }
        )
        TextButton(
            onClick = ::sendInputMessage,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(text = "검색")
        }
    }
}