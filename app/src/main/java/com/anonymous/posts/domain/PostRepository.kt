package com.anonymous.posts.domain

import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(userId: String): Flow<NetworkResult<List<PostDomain>>>
    suspend fun doFavorite(item: PostDomain)
    suspend fun unFavorite(item: PostDomain)
    suspend fun getFavoritePosts(): Flow<List<PostDomain>>
}

data class PostDomain(val id: Int, val title: String, val body: String)

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    class Error<T>(errorCode: String) : NetworkResult<T>()
}
