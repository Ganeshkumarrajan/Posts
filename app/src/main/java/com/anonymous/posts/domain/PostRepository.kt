package com.anonymous.posts.domain

import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(userId:String): Flow<NetworkResult<List<PostDomain>>>
}

data class PostDomain(val id:Int, val title:String, val body:String)

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    class Error<T>(errorCode: String) : NetworkResult<T>()
}
