package com.anonymous.posts.presentation.navigationApp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anonymous.posts.presentation.login.LoginScreen
import com.anonymous.posts.presentation.posts.PostsScreen

@Composable
fun NavigationApp(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = ScreenNames.Login.route
    ) {
        composable(route = ScreenNames.Login.route) {
            LoginScreen(navController)
        }
        composable(route = "${ScreenNames.Posts.route}/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId") ?: ""
            PostsScreen(postId)
        }
    }
}

sealed class ScreenNames(val route: String) {
    object Login : ScreenNames("login")
    object Posts : ScreenNames("posts")
    object PostDetails : ScreenNames("postDetails")
}
