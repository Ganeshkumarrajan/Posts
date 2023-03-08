package com.anonymous.posts.ui.theme.component.items.properties

data class PostDetailsUI(
    val title: String,
    val description: String
)

data class PostUI(
    val id: Int,
    val title: String,
    val description: String,
    var isFavorite: Boolean
)
