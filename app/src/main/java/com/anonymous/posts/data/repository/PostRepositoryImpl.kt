package com.anonymous.posts.data.repository

import com.anonymous.posts.data.mapper.NetworkToDomainMapper
import com.anonymous.posts.data.model.PostCommentsNetwork
import com.anonymous.posts.data.model.PostNetwork
import com.anonymous.posts.data.model.PostNetworkItem
import com.anonymous.posts.data.service.PostService
import com.anonymous.posts.data.utils.convertToNetworkResult
import com.anonymous.posts.data.utils.convertToNetworkResultWithOtWrapper
import com.anonymous.posts.domain.PostRepository
import com.anonymous.posts.domain.getComments.CommentsDomain
import com.anonymous.posts.domain.getPostById.PostDetailsDomain
import com.anonymous.posts.domain.getPosts.PostDomain
import com.anonymous.posts.domain.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val postMapper: NetworkToDomainMapper<PostNetwork?, List<PostDomain>>,
    private val postDetailsMapper: NetworkToDomainMapper<PostNetworkItem?, PostDetailsDomain>,
    private val commentsMapper: NetworkToDomainMapper<PostCommentsNetwork?, List<CommentsDomain>>,
    private val dispatcher: CoroutineDispatcher,
) : PostRepository {

    private val favoritePosts = mutableListOf<PostDomain>()
    private var posts = mutableListOf<PostDomain>()

    override suspend fun getPosts(userId: String): Flow<NetworkResult<List<PostDomain>>> =
        flow<NetworkResult<List<PostDomain>>> {
            if (posts.isEmpty()) {
                val result = getItemFromNet(userId)

                if (result is NetworkResult.Success) {
                    posts.addAll(result.data)
                }

                emit(result)
            } else {
                emit(NetworkResult.Success(posts))
            }
        }.flowOn(dispatcher)

    override suspend fun doFavorite(postID: Int): Flow<Boolean> = flow {
        val item = posts.first {
            it.id == postID
        }

        item.isFavorite = true
        emit(favoritePosts.add(item))
    }

    override suspend fun unFavorite(postID: Int): Flow<Boolean> = flow {
        val item = posts.first {
            it.id == postID
        }

        item.isFavorite = false
        emit(favoritePosts.remove(item))
    }

    override suspend fun getFavoritePosts(): Flow<List<PostDomain>> =
        flow {
            emit(favoritePosts)
        }.flowOn(dispatcher)

    override suspend fun getPostDetails(postId: Int): Flow<PostDetailsDomain?> = flow {
        emit(
            convertToNetworkResultWithOtWrapper(
                postService.getPostDetails(postId.toString()),
                postDetailsMapper
            )
        )
    }

    override suspend fun getComments(postId: Int): Flow<List<CommentsDomain>> = flow {
        emit(
            convertToNetworkResultWithOtWrapper(
                postService.getComments(postId.toString()),
                commentsMapper
            )?.let {
                it
            } ?: emptyList()
        )
    }

    private suspend fun getItemFromNet(userId: String): NetworkResult<List<PostDomain>> =
        convertToNetworkResult(postService.getPosts(userId), mapper = postMapper)
}
