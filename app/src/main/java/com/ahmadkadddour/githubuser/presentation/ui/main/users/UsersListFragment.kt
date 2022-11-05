package com.ahmadkadddour.githubuser.presentation.ui.main.users

import android.os.Bundle
import android.view.View
import com.ahmadkadddour.githubuser.BR
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.databinding.FragmentUsersListBinding
import com.ahmadkadddour.githubuser.presentation.ui.base.view.fragment.MVVMFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : MVVMFragment<UsersListViewModel, FragmentUsersListBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUsersRecycler()
    }

    private fun setupUsersRecycler() {
        val adapter = UsersListAdapter(
            requireContext()
        ){
            viewModel.fetchMoreUsers()
        }
        binding.rvUsers.adapter = adapter
    }

    override val layoutId: Int
        get() = R.layout.fragment_users_list
    override val viewModelClass: Class<UsersListViewModel>
        get() = UsersListViewModel::class.java
    override val viewModelId: Int
        get() = BR.viewModel
}