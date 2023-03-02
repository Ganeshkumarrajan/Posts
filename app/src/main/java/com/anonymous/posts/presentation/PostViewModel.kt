package com.anonymous.posts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.posts.domain.GetPostUseCase
import com.anonymous.posts.domain.NetworkResult
import com.anonymous.posts.domain.PostDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val useCase: GetPostUseCase,
private val mapper:DomainToUIMapper<List<PostDomain>, List<PostUI>>) : ViewModel() {
    private val _posts = MutableStateFlow<UIState<List<PostUI>>>(UIState.Nothing())
    val posts: StateFlow<UIState<List<PostUI>>> = _posts

    fun getPosts(userId: String) {
        viewModelScope.launch {
            useCase.invoke(userId).collectLatest {
                parseAPI(it)
            }
        }
    }

    private fun parseAPI(result: NetworkResult<List<PostDomain>>){
        when(result){
            is NetworkResult.Success ->{
                _posts.value = UIState.Success(mapper.map(result.data))
            }
            is NetworkResult.Error ->{

            }
        }
    }

}

data class PostUI(val id: Int, val title: String, val description: String,val isFavorite:Boolean)


interface DomainToUIMapper<I, O> {
    fun map(input: I): O
}

class PostMapperDomainToUi: DomainToUIMapper<List<PostDomain>, List<PostUI>>{
    override fun map(input: List<PostDomain>): List<PostUI> =
        input.map {
            PostUI(it.id,it.title,it.body,false)
        }


}

sealed class UIState<T> {
    class Nothing<T> : UIState<T>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val message: String) : UIState<T>()
    data class Loading<T>(val data: Boolean = true) : UIState<T>()
}
