package com.anonymous.posts.data

import com.anonymous.posts.domain.NetworkResult
import com.anonymous.posts.domain.PostDomain
import com.anonymous.posts.domain.PostRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val postMapper: NetworkToDomainMapper<PostNetwork?, List<PostDomain>>,
    private val dispatcher: CoroutineDispatcher
) : PostRepository {

    override suspend fun getPosts(userId: String): Flow<NetworkResult<List<PostDomain>>> = flow {
        emit(convertToNetworkResult(postService.getPosts(userId), mapper = postMapper))
    }.flowOn(dispatcher)
}


private fun <I, O> convertToNetworkResult(
    data: ApiResponse<I>,
    mapper: NetworkToDomainMapper<I, O>
): NetworkResult<O> {
    var result: NetworkResult<O>? = null

    data.onSuccess {
        result = NetworkResult.Success(mapper.mapTo(this.data))
    }.onError {
        result = NetworkResult.Error("")
    }.onException {
        result = NetworkResult.Error("")
    }

    return result ?: NetworkResult.Error("")
}

interface NetworkToDomainMapper<I, O> {
    fun mapTo(input: I): O
}

class PostMapperNetworkToDomain @Inject constructor() : NetworkToDomainMapper<PostNetwork?, List<PostDomain>> {
    override fun mapTo(input: PostNetwork?): List<PostDomain> =
        input?.let {
            it.map { item ->
                PostDomain(item.id ?: 0, item.title ?: "", item.body ?: "")
            }
        } ?: emptyList()
}