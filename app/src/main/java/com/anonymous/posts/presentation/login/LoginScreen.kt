package com.anonymous.posts.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anonymous.posts.R
import com.anonymous.posts.presentation.navigationApp.ScreenNames
import com.anonymous.posts.ui.theme.component.buttons.LoginButton
import com.anonymous.posts.ui.theme.component.buttons.LoginButtonProperties
import com.anonymous.posts.ui.theme.component.textFilelds.LoginTextFiled
import com.anonymous.posts.ui.theme.component.textFilelds.LoginTextFiledProperties
import com.anonymous.posts.ui.theme.component.textFilelds.TextFieldState

@Composable
fun LoginScreen(navController: NavHostController) {
    MaterialTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
        ) {

            val text = remember { TextFieldState() }

            LoginTextFiled(
                properties =
                LoginTextFiledProperties(
                    stringResource(id = R.string.enter_user_id),
                    text
                )
            )

            Spacer(Modifier.height(10.dp))

            LoginButton(
                properties = LoginButtonProperties(title = stringResource(id = R.string.login)),
                onclick = {
                    navController.popBackStack()
                    navController.navigate("${ScreenNames.Posts.route}/${text.text}")
                },
                canEnable = text.text.isNotEmpty()
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        val navigation = rememberNavController()
        LoginScreen(navigation)
    }
}
