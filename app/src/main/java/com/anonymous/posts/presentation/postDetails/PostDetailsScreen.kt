package com.anonymous.posts.presentation.postDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anonymous.posts.presentation.utils.UIState
import com.anonymous.posts.ui.theme.component.items.CommentItem
import com.anonymous.posts.ui.theme.component.items.Description
import com.anonymous.posts.ui.theme.component.items.Title
import com.anonymous.posts.ui.theme.component.items.properties.CommentItemProperties
import com.anonymous.posts.ui.theme.component.items.properties.PostDetailsUI

@Composable
fun PostDetailsScreen(
    postID: Int,
    viewModel: PostDetailsViewModel = hiltViewModel<PostDetailsViewModel>()
) {
    Column(modifier = Modifier.padding(10.dp)) {
        when (val result = viewModel.posts.collectAsState().value) {
            is UIState.Success -> {
                PostDetails(result.data.postUI)
                Spacer(modifier = Modifier.height(20.dp))
                Comments(items = result.data.commentItemProperties)
            }
            else -> {}
        }
    }

    viewModel.getPosts(postID)
}

@Composable
private fun PostDetails(data: PostDetailsUI) {
    Title(text = data.title, modifier = Modifier)
    Description(text = data.description)
}

@Composable
private fun Comments(items: List<CommentItemProperties>) {
    LazyColumn() {
        items(items) {
            Column(Modifier.padding(10.dp)) {
                CommentItem(properties = it)
            }
        }
    }
}
