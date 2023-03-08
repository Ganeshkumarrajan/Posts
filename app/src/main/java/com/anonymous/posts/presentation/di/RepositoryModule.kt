package com.anonymous.posts.presentation.di

import com.anonymous.posts.data.mapper.NetworkToDomainMapper
import com.anonymous.posts.data.mapper.PostCommentsMapperNetworkToDomain
import com.anonymous.posts.data.mapper.PostDetailsMapperNetworkToDomain
import com.anonymous.posts.data.mapper.PostMapperNetworkToDomain
import com.anonymous.posts.data.model.PostCommentsNetwork
import com.anonymous.posts.data.model.PostNetwork
import com.anonymous.posts.data.model.PostNetworkItem
import com.anonymous.posts.data.repository.PostRepositoryImpl
import com.anonymous.posts.data.service.PostService
import com.anonymous.posts.domain.PostRepository
import com.anonymous.posts.domain.getComments.CommentsDomain
import com.anonymous.posts.domain.getPostById.PostDetailsDomain
import com.anonymous.posts.domain.getPosts.PostDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideSearchMapper(): NetworkToDomainMapper<PostNetwork?, List<PostDomain>> =
        PostMapperNetworkToDomain()

    @Provides
    fun providePostDetailsMapperNetworkToDomain(): NetworkToDomainMapper<PostNetworkItem?, PostDetailsDomain> =
        PostDetailsMapperNetworkToDomain()

    @Provides
    fun providePostCommentsMapperNetworkToDomain(): NetworkToDomainMapper<PostCommentsNetwork?, List<CommentsDomain>> =
        PostCommentsMapperNetworkToDomain()

    @Provides
    @Singleton
    fun provideRepository(
        postService: PostService,
        postMapper: NetworkToDomainMapper<PostNetwork?, List<PostDomain>>,
        postDetailsMapper: NetworkToDomainMapper<PostNetworkItem?, PostDetailsDomain>,
        commentsMapper: NetworkToDomainMapper<PostCommentsNetwork?, List<CommentsDomain>>,
    ): PostRepository =
        PostRepositoryImpl(
            postService,
            postMapper,
            postDetailsMapper,
            commentsMapper,
            Dispatchers.IO
        )
}
