package com.anonymous.posts.domain.getPostDetailsWithComments

import com.anonymous.posts.domain.getComments.GetCommentsUseCase
import com.anonymous.posts.domain.getPostById.GetPostByIdUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

interface GetPostWithCommentsUseCase {
    suspend fun invoke(postID: Int): Flow<PostWithCommentsDomain?>
}

class GetPostWithCommentsUseCaseImpl(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val useCase: GetPostByIdUseCase,
    private val dispatcher: CoroutineDispatcher,
) : GetPostWithCommentsUseCase {
    override suspend fun invoke(postID: Int): Flow<PostWithCommentsDomain?> {

        val postDetailsFlow = useCase.invoke(postID)
        val commentsFlow = getCommentsUseCase.invoke(postID)

        return postDetailsFlow.combine(commentsFlow) { i1, i2 ->
            PostWithCommentsDomain(i1, i2)
        }.flowOn(dispatcher)
    }
}
