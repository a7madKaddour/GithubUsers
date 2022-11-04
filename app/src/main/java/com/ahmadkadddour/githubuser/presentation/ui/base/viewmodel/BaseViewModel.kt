package com.ahmadkadddour.githubuser.presentation.ui.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmadkadddour.githubuser.presentation.exception.ExceptionFactory
import com.ahmadkadddour.githubuser.presentation.util.lifecycle.Event
import kotlinx.coroutines.flow.FlowCollector
import java.io.IOException

typealias ErrorHandler = (suspend FlowCollector<*>.(Throwable) -> Unit)

abstract class BaseViewModel : ViewModel() {
    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    private val _toastMessageResource = MutableLiveData<Event<Int>>()
    val toastMessageResource: LiveData<Event<Int>> = _toastMessageResource

    private val _hideKeyboard = MutableLiveData<Event<Boolean>>()
    val hideKeyboard: LiveData<Event<Boolean>> = _hideKeyboard

    protected fun showMessage(message: String) {
        _toastMessage.value = Event(message)
    }

    protected fun showMessage(stringId: Int) {
        _toastMessageResource.value = Event(stringId)
    }

    protected val errorHandler: ErrorHandler =
        { error ->
            error.printStackTrace()
            stopLoading()
            showMessage(error.cause ?: error)

            _isError.value = true
        }

    protected fun showMessage(error: Throwable) {
        val message = error.message
        if (message != null && error !is IOException) showMessage(message)
        else {
            val stringId = ExceptionFactory.getString(error)
            if (stringId > 0) showMessage(stringId)
        }
    }

    protected fun hideKeyboard() {
        _hideKeyboard.value = Event(true)
    }

    protected fun isLoadingValue(): Boolean {
        return isLoading.value == true
    }

    protected fun startLoading() {
        _isError.value = false
        _isLoading.value = true
    }

    protected fun stopLoading() {
        _isLoading.value = false
    }
}