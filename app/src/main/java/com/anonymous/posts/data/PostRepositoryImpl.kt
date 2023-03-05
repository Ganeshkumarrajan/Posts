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

    private val favoritePosts = mutableListOf<PostDomain>()
    private var posts: NetworkResult<List<PostDomain>>? = null

    override suspend fun getPosts(userId: String): Flow<NetworkResult<List<PostDomain>>> = flow {
        posts?.let {
            if (posts is NetworkResult.Success) {
                emit(it)
            } else {
                emit(getItemFromNet(userId))
            }
        } ?: kotlin.run {
            emit(getItemFromNet(userId))
        }

    }.flowOn(dispatcher)

    override suspend fun doFavorite(item: PostDomain) {
        favoritePosts.add(item)
    }

    override suspend fun unFavorite(item: PostDomain) {
        favoritePosts.remove(item)
    }

    override suspend fun getFavoritePosts(): Flow<List<PostDomain>> =
        flow {
            emit(favoritePosts)
        }.flowOn(dispatcher)

    private suspend fun getItemFromNet(userId: String): NetworkResult<List<PostDomain>> {

        val item = convertToNetworkResult(postService.getPosts(userId), mapper = postMapper)

        if (item is NetworkResult.Success) {
            posts = item
        }

        return item
    }

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

class PostMapperNetworkToDomain @Inject constructor() :
    NetworkToDomainMapper<PostNetwork?, List<PostDomain>> {
    override fun mapTo(input: PostNetwork?): List<PostDomain> =
        input?.let {
            it.map { item ->
                PostDomain(item.id ?: 0, item.title ?: "", item.body ?: "")
            }
        } ?: emptyList()
}
