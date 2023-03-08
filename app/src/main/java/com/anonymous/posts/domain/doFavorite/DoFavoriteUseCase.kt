package com.anonymous.posts.domain.doFavorite

import com.anonymous.posts.domain.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DoFavoriteUseCase {
    suspend fun invoke(postID: Int): Flow<Boolean>
}

class DoFavoriteUseCaseImpl @Inject constructor(private val repository: PostRepository) :
    DoFavoriteUseCase {
    override suspend fun invoke(postID: Int): Flow<Boolean> =
        repository.doFavorite(postID)
}
