package com.anonymous.posts.presentation.postDetails.mapper

import com.anonymous.posts.domain.getPostDetailsWithComments.PostWithCommentsDomain
import com.anonymous.posts.presentation.postDetails.model.PostDetailsWithCommentsUI
import com.anonymous.posts.presentation.posts.mapper.DomainToUIMapper
import com.anonymous.posts.ui.theme.component.items.properties.CommentItemProperties
import com.anonymous.posts.ui.theme.component.items.properties.PostDetailsUI
import javax.inject.Inject

class PostDetailsMapper @Inject constructor() :
    DomainToUIMapper<PostWithCommentsDomain, PostDetailsWithCommentsUI> {
    override fun map(input: PostWithCommentsDomain): PostDetailsWithCommentsUI =
        convert(input)

    private fun convert(data: PostWithCommentsDomain): PostDetailsWithCommentsUI =
        PostDetailsWithCommentsUI(
            PostDetailsUI(
                data.postDetailsDomain?.title ?: "", data.postDetailsDomain?.body ?: ""
            ),

            data.commentsDomain.map {
                CommentItemProperties(it?.name ?: "", it?.email ?: "", it?.body ?: "")
            }
        )
}
