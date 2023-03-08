package com.anonymous.posts.presentation.di

import com.anonymous.posts.domain.getPostDetailsWithComments.PostWithCommentsDomain
import com.anonymous.posts.domain.getPosts.PostDomain
import com.anonymous.posts.presentation.postDetails.mapper.PostDetailsMapper
import com.anonymous.posts.presentation.postDetails.model.PostDetailsWithCommentsUI
import com.anonymous.posts.presentation.posts.mapper.DomainToUIMapper
import com.anonymous.posts.presentation.posts.mapper.PostMapperDomainToUi
import com.anonymous.posts.ui.theme.component.items.properties.PostUI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ViewModelModule {
    @Provides
    fun providePostsUIMapper(): DomainToUIMapper<List<PostDomain>, List<PostUI>> =
        PostMapperDomainToUi()

    @Provides
    fun providePostDetailsUIMapper(): DomainToUIMapper<PostWithCommentsDomain, PostDetailsWithCommentsUI> =
        PostDetailsMapper()
}
