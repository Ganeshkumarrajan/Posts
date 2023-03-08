package com.anonymous.posts.presentation.di

import com.anonymous.posts.domain.PostRepository
import com.anonymous.posts.domain.doFavorite.DoFavoriteUseCase
import com.anonymous.posts.domain.doFavorite.DoFavoriteUseCaseImpl
import com.anonymous.posts.domain.doUnfavorite.DoUnFavoriteUseCase
import com.anonymous.posts.domain.doUnfavorite.DoUnFavoriteUseCaseImpl
import com.anonymous.posts.domain.getComments.GetCommentsDomainImplUseCase
import com.anonymous.posts.domain.getComments.GetCommentsUseCase
import com.anonymous.posts.domain.getFavorite.GetFavoritePostUseCase
import com.anonymous.posts.domain.getFavorite.GetFavoritePostUseCaseImpl
import com.anonymous.posts.domain.getPostById.GetPostByIdUseCase
import com.anonymous.posts.domain.getPostById.GetPostByIdUseCaseImpl
import com.anonymous.posts.domain.getPostDetailsWithComments.GetPostWithCommentsUseCase
import com.anonymous.posts.domain.getPostDetailsWithComments.GetPostWithCommentsUseCaseImpl
import com.anonymous.posts.domain.getPosts.GetPostUseCase
import com.anonymous.posts.domain.getPosts.GetPostsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideGetPostsUseCaseImpl(postRepository: PostRepository): GetPostUseCase =
        GetPostsUseCaseImpl(postRepository)

    @Provides
    fun provideDoFavoriteUseCaseImpl(postRepository: PostRepository): DoFavoriteUseCase =
        DoFavoriteUseCaseImpl(postRepository)

    @Provides
    fun provideDoUnFavoriteUseCaseImpl(postRepository: PostRepository): DoUnFavoriteUseCase =
        DoUnFavoriteUseCaseImpl(postRepository)

    @Provides
    fun provideGetFavoritePostsUseCaseImpl(postRepository: PostRepository): GetFavoritePostUseCase =
        GetFavoritePostUseCaseImpl(postRepository)

    @Provides
    fun provideGetCommentsUseCaseImpl(postRepository: PostRepository): GetCommentsUseCase =
        GetCommentsDomainImplUseCase(postRepository)

    @Provides
    fun provideGetPostWithCommentsUseCaseImpl(
        getCommentsUseCase: GetCommentsUseCase,
        useCase: GetPostByIdUseCase
    ): GetPostWithCommentsUseCase =
        GetPostWithCommentsUseCaseImpl(getCommentsUseCase, useCase, Dispatchers.IO)

    @Provides
    fun provideGetPostByIdUseCaseImpl(postRepository: PostRepository): GetPostByIdUseCase =
        GetPostByIdUseCaseImpl(postRepository)
}
