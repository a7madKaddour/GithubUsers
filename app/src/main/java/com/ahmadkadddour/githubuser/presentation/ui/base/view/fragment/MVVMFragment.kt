package com.ahmadkadddour.githubuser.presentation.ui.base.view.fragment

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmadkadddour.githubuser.presentation.ui.base.viewmodel.BaseViewModel
import com.ahmadkadddour.githubuser.presentation.util.lifecycle.EventObserver

abstract class MVVMFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseFragment<DB>() {
    protected lateinit var viewModel: VM

    protected abstract val viewModelClass: Class<VM>
    protected abstract val viewModelId: Int

    protected open val viewModelOwner: ViewModelStoreOwner
        get() = this

    protected fun provideViewModel(): VM {
        return ViewModelProvider(viewModelOwner)[viewModelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setupBaseObservers()
        return rootView
    }

    protected fun setupBaseObservers() {
        if (viewModelId > 0) binding.setVariable(viewModelId, viewModel)
        val owner = viewLifecycleOwner

        viewModel.toastMessageResource.observe(
            owner,
            EventObserver { stringId: Int -> showMessage(stringId) }
        )

        viewModel.toastMessage.observe(
            owner,
            EventObserver { message: String -> showMessage(message) }
        )

        viewModel.hideKeyboard.observe(
            owner,
            EventObserver { hideKeyboard() }
        )
    }
}