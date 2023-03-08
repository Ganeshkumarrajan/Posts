package com.anonymous.posts.data.mapper

import com.anonymous.posts.data.model.PostNetworkItem
import com.anonymous.posts.domain.getPostById.PostDetailsDomain
import javax.inject.Inject

class PostDetailsMapperNetworkToDomain @Inject constructor() :
    NetworkToDomainMapper<PostNetworkItem?, PostDetailsDomain> {
    override fun mapTo(input: PostNetworkItem?): PostDetailsDomain =
        PostDetailsDomain(
            input?.title ?: "", input?.body ?: ""
        )
}
