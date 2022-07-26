package com.tolgahan.android.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tolgahan.android.core.ViewState
import com.tolgahan.android.core.extension.onCollectOffline
import com.tolgahan.android.core.extension.onCollectOnline
import com.tolgahan.android.core.extension.onError
import com.tolgahan.android.core.viewmodel.BaseViewModel
import com.tolgahan.android.data.network.model.response.FollowersItem
import com.tolgahan.android.data.network.model.response.UserModel
import com.tolgahan.android.domain.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val userListUseCase: GetMovieListUseCase,
) : BaseViewModel() {

    val scrollPosition = MutableLiveData<Int>()
    var list = MutableLiveData<List<FollowersItem>>()
    val viewState = MutableLiveData<ViewState>()
    val searchText = MutableLiveData<String>()

    init {
        viewState.value=(ViewState.EMPTY_RESULT)
    }

    /**
     *
     *      * Online data offline data ile eşitse "Yeni Gönderi" butonunu açma eksik
     *      * İlk giriş senaryosunda offline data yokken "Yeni Gönderi" butonu açma eksik
     *      * onExpired ısrarı navigation ile yönlenmek, daha generic bi yapıyla daha üst katmanlarda çözülürbilir
     *
     */
     fun getList(searchKey: String){
        viewModelScope.launch {
            userListUseCase
                .invoke(searchKey)
                .onStart {
                    viewState.postValue(ViewState.LOADING)
                }.onError {
                    viewState.postValue(ViewState.ERROR)
                }.onCollectOffline { offlineList ->
                    if (offlineList.isNullOrEmpty()) {
                        viewState.value=(ViewState.LOADING)
                    } else {
                        list.value =(offlineList)
                        viewState.value= (ViewState.RESULT)
                    }
                }.onCollectOnline { onlineList ->
                    if (onlineList.isNullOrEmpty()) {
                        viewState.value=(ViewState.EMPTY_RESULT)
                    } else {
                        list.value=(onlineList)
                        viewState.value=(ViewState.RESULT_MORE)
                    }
                }
                .collect()
        }
    }
}