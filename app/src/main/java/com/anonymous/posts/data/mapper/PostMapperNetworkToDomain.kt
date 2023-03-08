package com.anonymous.posts.data.mapper

import com.anonymous.posts.data.model.PostNetwork
import com.anonymous.posts.domain.getPosts.PostDomain
import javax.inject.Inject

interface NetworkToDomainMapper<I, O> {
    fun mapTo(input: I): O
}

class PostMapperNetworkToDomain @Inject constructor() :
    NetworkToDomainMapper<PostNetwork?, List<PostDomain>> {
    override fun mapTo(input: PostNetwork?): List<PostDomain> =
        input?.let {
            it.map { item ->
                PostDomain(item.id ?: 0, item.title ?: "", item.body ?: "")
            }
        } ?: emptyList()
}
