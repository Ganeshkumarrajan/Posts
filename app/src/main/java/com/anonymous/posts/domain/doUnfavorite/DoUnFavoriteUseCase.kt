package com.anonymous.posts.domain.doUnfavorite

import com.anonymous.posts.domain.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DoUnFavoriteUseCase {
    suspend fun invoke(postID: Int): Flow<Boolean>
}

class DoUnFavoriteUseCaseImpl @Inject constructor(private val repository: PostRepository) :
    DoUnFavoriteUseCase {
    override suspend fun invoke(postID: Int): Flow<Boolean> =
        repository.unFavorite(postID)
}
