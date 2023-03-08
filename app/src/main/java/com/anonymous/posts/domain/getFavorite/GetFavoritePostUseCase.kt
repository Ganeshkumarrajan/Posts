package com.anonymous.posts.domain.getFavorite

import com.anonymous.posts.domain.PostRepository
import com.anonymous.posts.domain.getPosts.PostDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoritePostUseCase {
    suspend fun invoke(): Flow<List<PostDomain>>
}

class GetFavoritePostUseCaseImpl @Inject constructor(private val repository: PostRepository) :
    GetFavoritePostUseCase {
    override suspend fun invoke(): Flow<List<PostDomain>> =
        repository.getFavoritePosts()
}
