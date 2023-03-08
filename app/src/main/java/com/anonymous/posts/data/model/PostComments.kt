package com.anonymous.posts.data.model

class PostCommentsNetwork : ArrayList<PostCommentsNetworkItem>()

data class PostCommentsNetworkItem(
    val body: String?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val postId: Int?
)
