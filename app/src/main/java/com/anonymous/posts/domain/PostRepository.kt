package com.anonymous.posts.domain

import com.anonymous.posts.domain.getComments.CommentsDomain
import com.anonymous.posts.domain.getPostById.PostDetailsDomain
import com.anonymous.posts.domain.getPosts.PostDomain
import com.anonymous.posts.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPosts(userId: String): Flow<NetworkResult<List<PostDomain>>>
    suspend fun doFavorite(postID: Int): Flow<Boolean>
    suspend fun unFavorite(postID: Int): Flow<Boolean>
    suspend fun getFavoritePosts(): Flow<List<PostDomain>>
    suspend fun getPostDetails(postId: Int): Flow<PostDetailsDomain?>
    suspend fun getComments(postId: Int): Flow<List<CommentsDomain>>
}
