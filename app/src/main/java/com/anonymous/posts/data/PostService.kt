package com.anonymous.posts.data

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("posts")
    suspend fun getPosts(@Query("userId") userID: String): ApiResponse<PostNetwork>
}
