package com.ahmadkadddour.githubuser.presentation.ui.main.users

import android.app.ActivityOptions
import android.os.Bundle
import android.view.View
import com.ahmadkadddour.githubuser.BR
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.databinding.FragmentUsersListBinding
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.presentation.callback.OnItemViewClickedListener
import com.ahmadkadddour.githubuser.presentation.ui.base.view.fragment.MVVMFragment
import com.ahmadkadddour.githubuser.presentation.ui.details.UserDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : MVVMFragment<UsersListViewModel, FragmentUsersListBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUsersRecycler()
    }

    private val onUserClicked = OnItemViewClickedListener<UserEntity> { item, view ->
        val options = ActivityOptions
            .makeSceneTransitionAnimation(requireActivity(), view, getString(R.string.image_translation))
        val intent = UserDetailsActivity.getIntent(requireContext(), item.githubHandle, item.imageUrl)
        startActivity(intent, options.toBundle())
    }

    private fun setupUsersRecycler() {
        val adapter = UsersListAdapter(
            onUserClicked,
            requireContext()
        ) {
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