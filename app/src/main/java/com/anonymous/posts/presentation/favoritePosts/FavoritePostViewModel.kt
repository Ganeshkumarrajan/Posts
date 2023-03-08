package com.anonymous.posts.presentation.favoritePosts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.posts.domain.getFavorite.GetFavoritePostUseCase
import com.anonymous.posts.domain.getPosts.PostDomain
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
class FavoritePostViewModel @Inject constructor(
    private val useCase: GetFavoritePostUseCase,
    private val mapper: DomainToUIMapper<List<PostDomain>, List<PostUI>>
) : ViewModel() {

    private val _posts = MutableStateFlow<UIState<List<PostUI>>>(UIState.Nothing())
    val posts: StateFlow<UIState<List<PostUI>>> = _posts

    fun getPosts() {
        viewModelScope.launch {
            useCase.invoke().collectLatest {
                parseAPI(it)
            }
        }
    }

    private fun parseAPI(result: List<PostDomain>) {
        _posts.value = UIState.Success(mapper.map(result))
    }
}
