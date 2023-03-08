package com.anonymous.posts.presentation.posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.anonymous.posts.R
import com.anonymous.posts.presentation.favoritePosts.FavoritePostsScreen

@Composable
fun PostsScreen(postId: String, navController: NavController) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        stringResource(id = R.string.all),
        stringResource(id = R.string.favorite)
    )

    Column(modifier = Modifier.fillMaxWidth()) {

        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> AllPostsScreen(postID = postId, navController = navController)
            1 -> FavoritePostsScreen(navController = navController)
        }
    }
}
