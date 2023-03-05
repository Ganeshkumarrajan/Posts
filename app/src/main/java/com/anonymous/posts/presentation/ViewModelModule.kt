package com.anonymous.posts.presentation

import com.anonymous.posts.domain.PostDomain
import com.anonymous.posts.ui.theme.component.PostUI
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
}
