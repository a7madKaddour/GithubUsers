package com.ahmadkadddour.githubuser.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.usecase.SearchUserUseCase
import com.ahmadkadddour.githubuser.presentation.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : BaseViewModel() {
    private val _userLiveData = MutableLiveData<UserEntity>()
    val userLiveData: LiveData<UserEntity> = _userLiveData

    var handle = ""
    private set

    fun fetchUser(handle: String) {
        if (isLoadingValue()) return
        this.handle = handle
        startLoading()
        searchUserUseCase.execute(
            SearchUserUseCase.SearchUserUseCaseParams(handle)
        ).onEach {
            stopLoading()
            _userLiveData.value = it
        }
            .catch(errorHandler)
            .launchIn(viewModelScope)
    }
}