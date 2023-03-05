package com.anonymous.posts.presentation.posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun PostsScreen( postId: String) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "About")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> AllPostsScreen(postID = postId)
            1 -> FavoritePosts()
        }
    }
}




@Composable
fun FavoritePosts(){
    print("sd")
}