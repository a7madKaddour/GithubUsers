package com.ahmadkadddour.githubuser.presentation.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.ahmadkadddour.githubuser.BR
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.databinding.ActivityUserDetailsBinding
import com.ahmadkadddour.githubuser.presentation.ui.base.view.activity.MVVMActivity
import com.ahmadkadddour.githubuser.presentation.util.databinding.loadImage
import com.ahmadkadddour.githubuser.presentation.util.navigation.NavigationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : MVVMActivity<UserDetailsViewModel, ActivityUserDetailsBinding>() {
    companion object {
        private const val EXTRA_HANDLE = "handle"
        private const val EXTRA_IMAGE = "image"

        fun getIntent(context: Context, handle: String, imageUrl: String?): Intent {
            return Intent(context, UserDetailsActivity::class.java).apply {
                putExtra(EXTRA_HANDLE, handle)
                putExtra(EXTRA_IMAGE, imageUrl)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntentExtras()
        setupClickListeners()
    }

    private fun checkIntentExtras() {
        val handle = intent.getStringExtra(EXTRA_HANDLE)
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE)
        loadImage(
            binding.ivUser,
            imageUrl,
            ContextCompat.getDrawable(this, R.color.gray)
        )
        if (handle != null) viewModel.fetchUser(handle)
    }

    private fun setupClickListeners() {
        binding.buttonVisitProfile.setOnClickListener {
            val url = viewModel.userLiveData.value?.githubUrl
            if (url != null) {
                startActivity(NavigationUtil.openBrowserIntent(url))
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_user_details
    override val viewModelId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<UserDetailsViewModel>
        get() = UserDetailsViewModel::class.java
}