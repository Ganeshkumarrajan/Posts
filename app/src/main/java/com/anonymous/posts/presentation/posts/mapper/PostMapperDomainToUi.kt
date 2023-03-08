package com.anonymous.posts.presentation.posts.mapper

import com.anonymous.posts.domain.getPosts.PostDomain
import com.anonymous.posts.ui.theme.component.items.properties.PostUI

interface DomainToUIMapper<I, O> {
    fun map(input: I): O
}

class PostMapperDomainToUi : DomainToUIMapper<List<PostDomain>, List<PostUI>> {
    override fun map(input: List<PostDomain>): List<PostUI> =
        input.map {
            PostUI(it.id, it.title, it.body, it.isFavorite ?: false)
        }
}
