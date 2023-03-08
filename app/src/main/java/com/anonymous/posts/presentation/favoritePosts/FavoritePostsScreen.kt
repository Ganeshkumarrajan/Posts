package com.anonymous.posts.presentation.favoritePosts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anonymous.posts.presentation.navigationApp.ScreenNames
import com.anonymous.posts.presentation.utils.UIState
import com.anonymous.posts.ui.theme.component.items.PostItem
import com.anonymous.posts.ui.theme.component.items.properties.PostUI

@Composable
fun FavoritePostsScreen(
    viewModel: FavoritePostViewModel =
        hiltViewModel(),
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        OnPostReceived(viewModel, navController)
    }

    viewModel.getPosts()
}

@Composable
fun OnPostReceived(viewModel: FavoritePostViewModel, navController: NavController) {
    when (val result = viewModel.posts.collectAsState().value) {
        is UIState.Success -> {
            OnSuccess(data = result.data, navController)
        }
        else -> {}
    }
}

@Composable
private fun OnSuccess(data: List<PostUI>, navController: NavController) {
    LazyColumn() {
        items(data) { post ->
            Column(Modifier.padding(10.dp)) {
                PostItem(properties = post, {
                }, { postId ->
                    navController.navigate("${ScreenNames.PostDetails.route}/$postId")
                })
            }
        }
    }
}
