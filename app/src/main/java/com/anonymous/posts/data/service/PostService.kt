package com.anonymous.posts.data.service

import com.anonymous.posts.data.model.PostCommentsNetwork
import com.anonymous.posts.data.model.PostNetwork
import com.anonymous.posts.data.model.PostNetworkItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("posts")
    suspend fun getPosts(@Query("userId") userID: String): ApiResponse<PostNetwork>

    @GET("posts/{postId}")
    suspend fun getPostDetails(@Path("postId") postId: String): ApiResponse<PostNetworkItem>

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: String): ApiResponse<PostCommentsNetwork>
}
