package com.anonymous.posts.domain.getPosts

data class PostDomain(
    val id: Int,
    val title: String,
    val body: String,
    var isFavorite: Boolean? = false
)
