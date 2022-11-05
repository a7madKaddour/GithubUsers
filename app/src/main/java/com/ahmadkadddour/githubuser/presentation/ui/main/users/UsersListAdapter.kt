package com.ahmadkadddour.githubuser.presentation.ui.main.users

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import com.ahmadkadddour.githubuser.databinding.ItemUserBinding
import com.ahmadkadddour.githubuser.domain.model.UserEntity
import com.ahmadkadddour.githubuser.presentation.callback.OnItemViewClickedListener
import com.ahmadkadddour.githubuser.presentation.callback.OnLoadingPageListener
import com.ahmadkadddour.githubuser.presentation.ui.base.adapter.BaseViewHolder
import com.ahmadkadddour.githubuser.presentation.ui.base.adapter.PagingListAdapter

class UsersListAdapter(
    private val onItemViewClickedListener: OnItemViewClickedListener<UserEntity>,
    context: Context,
    onLoadingPageListener: OnLoadingPageListener
) : PagingListAdapter<UserEntity, BaseViewHolder<UserEntity, *>>(context, onLoadingPageListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UserEntity, *> {
        return if (viewType == LOADING) provideDefaultBottomLoaderViewHolder(parent)
        else {
            val binding = ItemUserBinding.inflate(provideInflater(), parent, false)
            UserViewHolder(binding)
        }
    }

    inner class UserViewHolder(
        binding: ItemUserBinding
    ) : BaseViewHolder<UserEntity, ItemUserBinding>(binding) {
        override fun onBind(item: UserEntity, position: Int) {
            binding.root.setOnClickListener {
                onItemViewClickedListener.onItemClicked(item, binding.ivItem)
            }
            binding.user = item
            setFadeAnimation(binding.root);
        }

        private fun setFadeAnimation(view: View) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 300
            view.startAnimation(anim)
        }
    }
}