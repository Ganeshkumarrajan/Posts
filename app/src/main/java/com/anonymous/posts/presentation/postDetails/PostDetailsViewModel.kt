package com.anonymous.posts.presentation.postDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.posts.domain.getPostDetailsWithComments.GetPostWithCommentsUseCase
import com.anonymous.posts.domain.getPostDetailsWithComments.PostWithCommentsDomain
import com.anonymous.posts.presentation.postDetails.model.PostDetailsWithCommentsUI
import com.anonymous.posts.presentation.posts.mapper.DomainToUIMapper
import com.anonymous.posts.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val useCase: GetPostWithCommentsUseCase,
    private val commentsMapper: DomainToUIMapper<PostWithCommentsDomain?, PostDetailsWithCommentsUI>
) : ViewModel() {
    private val _posts = MutableStateFlow<UIState<PostDetailsWithCommentsUI>>(UIState.Nothing())
    val posts: StateFlow<UIState<PostDetailsWithCommentsUI>> = _posts

    fun getPosts(postID: Int) {
        viewModelScope.launch {
            useCase.invoke(postID).collectLatest {
                parseAPI(it)
            }
        }
    }

    private fun parseAPI(result: PostWithCommentsDomain?) {
        result?.let {
            _posts.value = UIState.Success(commentsMapper.map(result))
        }
    }
}
