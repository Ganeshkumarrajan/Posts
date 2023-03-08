package com.anonymous.posts.presentation.postDetails.model

import com.anonymous.posts.ui.theme.component.items.properties.CommentItemProperties
import com.anonymous.posts.ui.theme.component.items.properties.PostDetailsUI

data class PostDetailsWithCommentsUI(
    val postUI: PostDetailsUI,
    val commentItemProperties: List<CommentItemProperties>
)
