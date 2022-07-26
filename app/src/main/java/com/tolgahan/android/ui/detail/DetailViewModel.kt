package com.tolgahan.android.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tolgahan.android.core.ViewState
import com.tolgahan.android.core.viewmodel.BaseViewModel
import com.tolgahan.android.data.network.model.response.FollowersItem
import com.tolgahan.android.domain.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mediaUseCase: GetMovieDetailUseCase,
) : BaseViewModel() {

    val detail = MutableLiveData<FollowersItem>()
    var isClicked = MutableLiveData<Boolean>()
    val viewState = MutableLiveData<ViewState>()

    val id = MutableLiveData<String>()

    fun getMovie(id: String) {
        viewModelScope.launch {
            mediaUseCase
                .invoke(id)
                .collect { response ->
                    response.let {
                        detail.postValue(it)
                    }
                }
        }
    }

    fun favClicked(boolean: Boolean) {
        isClicked.value = boolean
    }
}

