package com.tolgahan.android.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tolgahan.android.core.Event
import com.tolgahan.android.core.constants.AppConstants
import com.tolgahan.android.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel() : BaseViewModel() {

    private val navigateToList = MutableLiveData<Event<Unit>>()

    init {
        viewModelScope.launch {
            delay(AppConstants.DELAY)
            navigateToList.postValue(Event(Unit))
        }
    }
}