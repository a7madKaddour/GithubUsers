package com.ahmadkadddour.githubuser.presentation.ui.main.search

import com.ahmadkadddour.githubuser.BR
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.databinding.FragmentSearchUserBinding
import com.ahmadkadddour.githubuser.presentation.ui.base.view.fragment.MVVMFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUserFragment : MVVMFragment<SearchUserViewModel, FragmentSearchUserBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_search_user
    override val viewModelClass: Class<SearchUserViewModel>
        get() = SearchUserViewModel::class.java
    override val viewModelId: Int
        get() = BR.viewModel
}