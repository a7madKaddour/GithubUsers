package com.ahmadkadddour.githubuser.presentation.ui.main.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.domain.usecase.GetUsersUseCase
import com.ahmadkadddour.githubuser.presentation.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {
    private val _usersLiveData = MutableLiveData<List<UserEntity>>()
    val usersLiveData: LiveData<List<UserEntity>> = _usersLiveData

    var isRefreshing = false
        private set

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        if (isLoadingValue()) return
        startLoading()
        isRefreshing = true
        getUsersUseCase.execute(GetUsersUseCase.GetUsersUserCaseParams())
            .onEach {
                stopLoading()
                isRefreshing = false
                _usersLiveData.value = it
            }
            .catch { error ->
                isRefreshing = false
                errorHandler.invoke(this, error)
            }
            .launchIn(viewModelScope)
    }

    fun fetchMoreUsers() {
        if (isLoadingValue()) return
        startLoading()

        val lastItem = _usersLiveData.value?.lastOrNull()
        getUsersUseCase.execute(
            GetUsersUseCase.GetUsersUserCaseParams(sinceId = lastItem?.id)
        )
            .onEach {
                stopLoading()
                val allData = _usersLiveData.value?.toMutableList() ?: mutableListOf()
                allData.addAll(it)
                _usersLiveData.value = allData
            }
            .catch(errorHandler)
            .launchIn(viewModelScope)
    }
}