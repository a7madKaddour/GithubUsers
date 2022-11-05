package com.ahmadkadddour.githubuser.presentation.ui.main.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.ahmadkadddour.githubuser.BR
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.databinding.FragmentSearchUserBinding
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.presentation.ui.base.view.fragment.MVVMFragment
import com.ahmadkadddour.githubuser.presentation.ui.details.UserDetailsActivity
import com.ahmadkadddour.githubuser.presentation.util.lifecycle.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUserFragment : MVVMFragment<SearchUserViewModel, FragmentSearchUserBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerListeners()
        registerObservers()
    }

    private fun registerListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) viewModel.searchUser(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }

    private fun registerObservers() {
        viewModel.userLiveData.observe(
            viewLifecycleOwner,
            EventObserver { user: UserEntity? ->
                if (user != null) {
                    openUserDetailsActivity(user)
                }
            }
        )
    }

    private fun openUserDetailsActivity(user: UserEntity) {
        startActivity(
            UserDetailsActivity.getIntent(requireContext(), user.githubHandle, user.imageUrl)
        )
    }

    override val layoutId: Int
        get() = R.layout.fragment_search_user
    override val viewModelClass: Class<SearchUserViewModel>
        get() = SearchUserViewModel::class.java
    override val viewModelId: Int
        get() = BR.viewModel
}