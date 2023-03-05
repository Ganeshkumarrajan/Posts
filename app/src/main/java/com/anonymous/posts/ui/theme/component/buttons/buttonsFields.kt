package com.anonymous.posts.ui.theme.component.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginButton(properties: LoginButtonProperties, onclick: () -> (Unit), canEnable: Boolean = false) {
    Button(modifier = Modifier.fillMaxWidth(), enabled = canEnable, onClick = {
        onclick.invoke()
    }) {
        ButtonText(title = properties.title)
    }
}

@Composable
private fun ButtonText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.secondary,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun PreviewLoginButton() {
    MaterialTheme() {
        Column(modifier = Modifier.fillMaxWidth()) {
            LoginButton(properties = LoginButtonProperties("Login"), canEnable = false, onclick = {
            })
        }
    }
}
