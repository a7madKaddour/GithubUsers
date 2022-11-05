package com.ahmadkadddour.githubuser.presentation.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.usecase.SearchUserUseCase
import com.ahmadkadddour.githubuser.presentation.ui.base.viewmodel.BaseViewModel
import com.ahmadkadddour.githubuser.presentation.util.lifecycle.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : BaseViewModel() {
    private val _userLiveData = MutableLiveData<Event<UserEntity>>()
    val userLiveData: LiveData<Event<UserEntity>> = _userLiveData

    fun searchUser(username: String) {
        if (isLoadingValue()) return
        startLoading()
        hideKeyboard
        searchUserUseCase.execute(
            SearchUserUseCase.SearchUserUseCaseParams(username)
        ).onEach {
            stopLoading()
            _userLiveData.value = Event(it)
        }
            .catch(errorHandler)
            .launchIn(viewModelScope)
    }
}