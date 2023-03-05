package com.anonymous.posts.presentation.posts

import android.provider.Contacts.Intents.UI
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
import com.anonymous.posts.presentation.PostViewModel
import com.anonymous.posts.presentation.UIState
import com.anonymous.posts.ui.theme.component.PostItem
import com.anonymous.posts.ui.theme.component.PostUI

@Composable
fun AllPostsScreen(viewModel: PostViewModel = hiltViewModel<PostViewModel>(), postID: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        OnPostReceived(viewModel)
    }

    viewModel.getPosts(postID)
}

@Composable
fun OnPostReceived(viewModel: PostViewModel) {
    when (val result = viewModel.posts.collectAsState().value) {
        is UIState.Success -> {
            onSuccess(data = result.data)
        }
        is UIState.Error -> {

        }
    }
}

@Composable
private fun onLoading() {

}

@Composable
private fun onError() {

}

@Composable
private fun onSuccess(data: List<PostUI>) {
    LazyColumn() {
        items(data) { post ->
            Column(Modifier.padding(10.dp)) {
                PostItem(properties = post,{

                })
            }

        }
    }
}
