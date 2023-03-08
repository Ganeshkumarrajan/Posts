package com.anonymous.posts

import com.anonymous.posts.domain.getPostDetailsWithComments.GetPostWithCommentsUseCase
import com.anonymous.posts.presentation.postDetails.PostDetailsViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.posts.domain.getComments.CommentsDomain
import com.anonymous.posts.domain.getPostById.PostDetailsDomain
import com.anonymous.posts.domain.getPostDetailsWithComments.PostWithCommentsDomain
import com.anonymous.posts.presentation.postDetails.model.PostDetailsWithCommentsUI
import com.anonymous.posts.presentation.posts.mapper.DomainToUIMapper
import com.anonymous.posts.presentation.utils.UIState
import com.anonymous.posts.ui.theme.component.items.properties.CommentItemProperties
import com.anonymous.posts.ui.theme.component.items.properties.PostDetailsUI
import io.mockk.coEvery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PostDetailsViewModelTest {
    private val useCase: GetPostWithCommentsUseCase = mockk()
    private lateinit var viewModel: PostDetailsViewModel
    private val commentsMapper: DomainToUIMapper<PostWithCommentsDomain?,
            PostDetailsWithCommentsUI> = mockk()


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = PostDetailsViewModel(useCase, commentsMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPostWithDetailsSuccessCase() {
        runTest {
            val keyWord = "ValidKayWord"
            coEvery {
                useCase.invoke(1)
            } returns getFakePostDetailResult()

            coEvery {
                commentsMapper.map(getFakeSearchDomain())
            } returns getFakePostDetailsWithCommentsUI()

            viewModel.getPosts(1)

           assert( viewModel.posts.value is UIState.Success)
        }
    }

    private fun getFakePostDetailResult(): Flow<PostWithCommentsDomain> = flow {
        emit(getFakeSearchDomain())
    }

    private fun getFakeSearchDomain(): PostWithCommentsDomain =
        PostWithCommentsDomain(
            PostDetailsDomain("title", "body"),
            commentsDomain = listOf(CommentsDomain("body", "email", "name"))
        )


    private fun getFakePostDetailsWithCommentsUI() =
        PostDetailsWithCommentsUI(
            PostDetailsUI("title", "description"),
            listOf(CommentItemProperties("name", "email", "body"))
        )
}
