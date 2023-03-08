package com.anonymous.posts.domain.getPostById

import com.anonymous.posts.domain.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPostByIdUseCase {
    suspend fun invoke(userID: Int): Flow<PostDetailsDomain?>
}

class GetPostByIdUseCaseImpl @Inject constructor(private val repository: PostRepository) :
    GetPostByIdUseCase {
    override suspend fun invoke(userID: Int): Flow<PostDetailsDomain?> =
        repository.getPostDetails(userID)
}
