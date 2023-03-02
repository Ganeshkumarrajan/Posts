package com.anonymous.posts.presentation

import com.anonymous.posts.data.*
import com.anonymous.posts.domain.PostDomain
import com.anonymous.posts.domain.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideSearchMapper(): NetworkToDomainMapper<PostNetwork?, List<PostDomain>> =
        PostMapperNetworkToDomain()

    @Provides
    fun provideRepository(
        service: PostService,
        postMapper: NetworkToDomainMapper<PostNetwork?, List<PostDomain>>
    ): PostRepository =
        PostRepositoryImpl(service, postMapper, Dispatchers.IO)
}
