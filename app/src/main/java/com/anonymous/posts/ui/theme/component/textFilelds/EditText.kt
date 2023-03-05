package com.anonymous.posts.ui.theme.component.textFilelds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

class TextFieldState {
    var text: String by mutableStateOf("")
}

@Composable
fun LoginTextFiled(properties: LoginTextFiledProperties) {
    val maxChar = 2

    TextField(
        value = properties.text.text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        onValueChange = { value ->
            if (value.length <= maxChar) {
                properties.text.text = value.filter { it.isDigit() }
            }
        }, label = {
            HintLabel(properties.hintName)
        },
        singleLine = true
    )
}

@Composable
private fun HintLabel(hint: String) {
    Text(
        text = hint,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun PreviewLoginTextFiled() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth()) {
            LoginTextFiled(
                LoginTextFiledProperties(
                    "enter user ID",
                    text = remember { TextFieldState() }
                )
            )
        }
    }
}
