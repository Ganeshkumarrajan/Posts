package com.anonymous.posts.domain.getComments

import com.anonymous.posts.domain.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class CommentsDomain(
    val body: String,
    val email: String,
    val name: String,
)

interface GetCommentsUseCase {
    suspend fun invoke(postID: Int): Flow<List<CommentsDomain>>
}

class GetCommentsDomainImplUseCase @Inject constructor(private val repository: PostRepository) :
    GetCommentsUseCase {

    override suspend fun invoke(postID: Int): Flow<List<CommentsDomain>> =
        repository.getComments(postID)
}
