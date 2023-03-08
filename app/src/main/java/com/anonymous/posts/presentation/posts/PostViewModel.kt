package com.anonymous.posts.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.posts.domain.doFavorite.DoFavoriteUseCase
import com.anonymous.posts.domain.doUnfavorite.DoUnFavoriteUseCase
import com.anonymous.posts.domain.getPosts.GetPostUseCase
import com.anonymous.posts.domain.getPosts.PostDomain
import com.anonymous.posts.domain.utils.NetworkResult
import com.anonymous.posts.presentation.posts.mapper.DomainToUIMapper
import com.anonymous.posts.presentation.utils.UIState
import com.anonymous.posts.ui.theme.component.items.properties.PostUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val useCase: GetPostUseCase,
    private val doFavoriteUseCase: DoFavoriteUseCase,
    private val doUnFavoriteUseCase: DoUnFavoriteUseCase,
    private val mapper: DomainToUIMapper<List<PostDomain>, List<PostUI>>
) : ViewModel() {
    private val _posts = MutableStateFlow<UIState<List<PostUI>>>(UIState.Nothing())
    val posts: StateFlow<UIState<List<PostUI>>> = _posts

    fun onUiEvent(eventName: PostListUiEvent) {
        when (eventName) {
            is PostListUiEvent.GetPost -> getPosts(userId = eventName.userId)
            is PostListUiEvent.DoFavorite -> doFavorite(eventName.postID)
            is PostListUiEvent.DoUnFavorite -> doUnFavorite(eventName.postID)
        }
    }

    private fun getPosts(userId: String) {
        viewModelScope.launch {
            useCase.invoke(userId).collectLatest {
                parseAPI(it)
            }
        }
    }

    private fun parseAPI(result: NetworkResult<List<PostDomain>>) {
        when (result) {
            is NetworkResult.Success -> {
                _posts.value = UIState.Success(mapper.map(result.data))
            }
            is NetworkResult.Error -> {
            }
        }
    }

    private fun doFavorite(postID: Int) {
        viewModelScope.launch {
            doFavoriteUseCase.invoke(postID).collectLatest {
            }
        }
    }

    private fun doUnFavorite(postID: Int) {
        viewModelScope.launch {
            doUnFavoriteUseCase.invoke(postID).collectLatest {
            }
        }
    }
}

sealed class PostListUiEvent {
    data class GetPost(val userId: String) : PostListUiEvent()
    data class DoFavorite(val postID: Int) : PostListUiEvent()
    data class DoUnFavorite(val postID: Int) : PostListUiEvent()
}
