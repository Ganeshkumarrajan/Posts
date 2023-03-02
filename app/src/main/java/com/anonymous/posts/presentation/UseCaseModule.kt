package com.anonymous.posts.presentation

import com.anonymous.posts.domain.GetPostUseCase
import com.anonymous.posts.domain.GetPostsUseCaseImpl
import com.anonymous.posts.domain.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideGetPostsUseCaseImpl(postRepository: PostRepository): GetPostUseCase =
        GetPostsUseCaseImpl(postRepository)
}
