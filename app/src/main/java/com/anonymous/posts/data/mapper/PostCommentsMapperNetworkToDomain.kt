package com.anonymous.posts.data.mapper

import com.anonymous.posts.data.model.PostCommentsNetwork
import com.anonymous.posts.domain.getComments.CommentsDomain
import javax.inject.Inject

class PostCommentsMapperNetworkToDomain @Inject constructor() :
    NetworkToDomainMapper<PostCommentsNetwork?, List<CommentsDomain>> {
    override fun mapTo(input: PostCommentsNetwork?): List<CommentsDomain> =
        input?.let {
            it.map { item ->
                CommentsDomain(item.body ?: "", item.email ?: "", item.name ?: "")
            }
        } ?: emptyList()
}
