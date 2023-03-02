package com.anonymous.posts.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPostUseCase {
    suspend fun invoke(userID:String): Flow<NetworkResult<List<PostDomain>>>
}

class GetPostsUseCaseImpl @Inject constructor(private val repository: PostRepository):GetPostUseCase{
    override suspend fun invoke(userID: String): Flow<NetworkResult<List<PostDomain>>> =
        repository.getPosts(userID)


}