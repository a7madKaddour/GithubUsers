package com.ahmadkadddour.githubuser.presentation.ui.base.view.activity

import com.ahmadkadddour.githubuser.presentation.ui.base.viewmodel.BaseViewModel
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelStoreOwner
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahmadkadddour.githubuser.presentation.util.lifecycle.EventObserver

abstract class MVVMActivity<VM : BaseViewModel, DATA_BINDING : ViewDataBinding> : BindingActivity<DATA_BINDING>() {

    protected lateinit var viewModel: VM

    protected abstract val viewModelId: Int
    protected abstract val viewModelClass: Class<VM>

    protected fun provideViewModelStoreOwner(): ViewModelStoreOwner {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        setupBaseObservers()
    }

    protected fun provideViewModel(): VM {
        return ViewModelProvider(provideViewModelStoreOwner()).get(viewModelClass)
    }

    protected fun setupBaseObservers() {
        if (viewModelId > 0) binding.setVariable(viewModelId, viewModel)
        viewModel.toastMessage.observe(this, EventObserver(this::showMessage))
        viewModel.toastMessageResource.observe(this, EventObserver(this::showMessage))
        viewModel.hideKeyboard.observe(this, EventObserver { hideKeyboard() })
    }
}