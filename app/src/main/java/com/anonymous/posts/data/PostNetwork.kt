package com.anonymous.posts.data

class PostNetwork : ArrayList<PostNetworkItem>()

data class PostNetworkItem(
    val body: String?,
    val id: Int?,
    val title: String?,
    val userId: Int?,
)
