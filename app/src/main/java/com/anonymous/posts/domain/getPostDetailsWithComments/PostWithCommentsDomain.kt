package com.anonymous.posts.domain.getPostDetailsWithComments

import com.anonymous.posts.domain.getComments.CommentsDomain
import com.anonymous.posts.domain.getPostById.PostDetailsDomain

data class PostWithCommentsDomain(
    val postDetailsDomain: PostDetailsDomain?,
    val commentsDomain: List<CommentsDomain?>
)
