package com.ahmadkadddour.githubuser.presentation.ui.base.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.presentation.ui.base.view.activity.BaseActivity


abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    protected lateinit var rootView: View
    protected lateinit var binding: DB

    protected var baseActivity: BaseActivity? = null

    protected abstract val layoutId: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    protected fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        try {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            rootView = binding.root
        } catch (e: Exception) {
            rootView = inflater.inflate(layoutId, container, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createView(inflater, container)
        setupDataBinding()
        setupToolbar(rootView)
        return rootView
    }

    private fun setupDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    private fun setupToolbar(view: View) {
        try {
            val toolbar: Toolbar = view.findViewById(R.id.toolbar)
            baseActivity?.setSupportActionBar(toolbar)
            changeToolbarTitle("")
        } catch (ignored: Exception) {
        }
        try {
            view.findViewById<View>(R.id.back_button).setOnClickListener { onBackPressed() }
        } catch (ignored: Exception) {
        }
    }

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }

    protected fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    protected fun showMessage(stringId: Int) {
        showMessage(getString(stringId))
    }

    protected fun hideKeyboard() {
        var currentFocus = activity?.currentFocus
        if (currentFocus == null) currentFocus = View(context)
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    fun changeToolbarTitle(title: String) {
        baseActivity?.changeToolbarTitle(title)
    }

    fun setActivityFullScreen() {
        val windowInsetsController =
            WindowCompat.getInsetsController(requireActivity().window, requireView())
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    fun exitActivityFullScreen() {
        val windowInsetsController =
            WindowCompat.getInsetsController(requireActivity().window, requireView())
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
    }
}