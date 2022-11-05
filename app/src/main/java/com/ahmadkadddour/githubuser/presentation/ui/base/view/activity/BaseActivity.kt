package com.ahmadkadddour.githubuser.presentation.ui.base.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import android.widget.Toast
import android.view.WindowManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import com.ahmadkadddour.githubuser.R

abstract class BaseActivity : AppCompatActivity() {
    protected var isActivityRunning = false
        private set

    protected abstract val layoutId: Int

    override fun onResume() {
        super.onResume()
        isActivityRunning = true
    }

    override fun onPause() {
        super.onPause()
        isActivityRunning = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        setupToolbar()
    }

    protected open fun setView() {
        setContentView(layoutId)
    }

    private fun setupToolbar() {
        try {
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            if (toolbar != null) {
                setSupportActionBar(toolbar)
                changeToolbarTitle("")
            }
        } catch (ignored: Exception) {
        }
        try {
            findViewById<View>(R.id.back_button).setOnClickListener { onBackPressed() }
        } catch (ignored: Exception) {
        }
    }

    protected fun showMessage(@StringRes messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }

    protected fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun hideKeyboard() {
        var currentFocus = currentFocus
        if (currentFocus == null) currentFocus = View(this)
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }

    fun changeToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun setFullScreen() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}