package com.ahmadkadddour.githubuser.presentation.ui.base.view.fragment

import android.content.Context
import androidx.databinding.ViewDataBinding
import android.view.ViewGroup
import android.view.LayoutInflater
import android.os.Bundle
import android.graphics.drawable.ColorDrawable
import androidx.databinding.DataBindingUtil
import android.widget.Toast
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment

abstract class BaseFragmentDialog<DB : ViewDataBinding> : DialogFragment() {
    protected lateinit var binding: DB

    protected abstract val layoutId: Int

    protected fun setFullScreen() {
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = requireActivity()
        binding.executePendingBindings()
        return binding.root
    }

    protected fun showMessage(stringId: Int) {
        showMessage(getString(stringId))
    }

    protected fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        var currentFocus = activity?.currentFocus
        if (currentFocus == null) currentFocus = View(context)
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}