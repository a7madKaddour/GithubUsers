package com.ahmadkadddour.githubuser.presentation.ui.base.view.activity

import androidx.databinding.ViewDataBinding
import androidx.databinding.DataBindingUtil
import android.os.Bundle

abstract class BindingActivity<DATA_BINDING : ViewDataBinding> : BaseActivity() {
    protected lateinit var binding: DATA_BINDING

    override fun setView() {
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
    }

    protected fun setupDataBinding() {
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}