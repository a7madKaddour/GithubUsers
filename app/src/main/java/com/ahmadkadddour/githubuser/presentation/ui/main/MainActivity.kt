package com.ahmadkadddour.githubuser.presentation.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.databinding.ActivityMainBinding
import com.ahmadkadddour.githubuser.presentation.ui.base.view.activity.BindingActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomBar()
    }

    private fun setupBottomBar() {
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_main)
        setupWithNavController(binding.bottomNavigationView, navController)
    }
}