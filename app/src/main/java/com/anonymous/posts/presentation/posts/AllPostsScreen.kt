package com.anonymous.posts.presentation.posts

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
fun AllPostsScreen(viewModel: PostViewModel = hiltViewModel(), postID: String, navController: NavController) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        OnPostReceived(viewModel, navController)
    }

    viewModel.onUiEvent(PostListUiEvent.GetPost(userId = postID))
}

@Composable
private fun OnPostReceived(viewModel: PostViewModel, navController: NavController) {
    when (val result = viewModel.posts.collectAsState().value) {
        is UIState.Success -> {
            OnSuccess(data = result.data, viewModel, navController)
        }
        is UIState.Error -> {}
        else -> {}
    }
}

@Composable
private fun OnSuccess(data: List<PostUI>, viewModel: PostViewModel, navController: NavController) {
    LazyColumn() {
        items(data) { post ->
            Column(Modifier.padding(10.dp)) {
                PostItem(properties = post, onclick = {
                    if (it.isFavorite) viewModel.onUiEvent(PostListUiEvent.DoFavorite(it.id))
                    else viewModel.onUiEvent(PostListUiEvent.DoUnFavorite(it.id))
                }, onItemClicked = { postId ->
                        navController.navigate("${ScreenNames.PostDetails.route}/$postId")
                    })
            }
        }
    }
}
