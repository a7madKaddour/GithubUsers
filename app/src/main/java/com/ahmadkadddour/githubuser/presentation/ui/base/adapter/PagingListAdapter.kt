package com.ahmadkadddour.githubuser.presentation.ui.base.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ahmadkadddour.githubuser.R
import com.ahmadkadddour.githubuser.databinding.LayoutLoadingFooterBinding
import com.ahmadkadddour.githubuser.presentation.callback.OnLoadingPageListener

abstract class PagingListAdapter<T, VH : BaseViewHolder<T, *>>(
    context: Context,
    protected var onLoadingPageListener: OnLoadingPageListener
) : BaseListAdapter<T, VH>(
    context
) {
    companion object {
        const val LOADING = 695
        const val FILLED = 776
    }

    protected var isLoaderAdded = false

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (getItemViewType(position) != LOADING) {
            super.onBindViewHolder(holder, position)
            if (position == itemCount - 1) onLoadingPageListener.startLoading()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1 && isLoaderAdded) LOADING
        else FILLED
    }

    fun addBottomLoader() {
        isLoaderAdded = true
        Handler(Looper.getMainLooper()).post { notifyItemInserted(itemCount - 1) }
    }

    fun removeBottomLoader() {
        isLoaderAdded = false
        notifyItemRemoved(itemCount)
    }

    override fun getItemCount(): Int {
        return if (isLoaderAdded) super.getItemCount() + 1
        else super.getItemCount()
    }

    protected fun provideDefaultBottomLoaderViewHolder(parent: ViewGroup?): LoadingFooterViewHolder {
        val binding: LayoutLoadingFooterBinding = DataBindingUtil.inflate(
            provideInflater(),
            R.layout.layout_loading_footer,
            parent,
            false
        )
        return LoadingFooterViewHolder(binding)
    }
}

class LoadingFooterViewHolder(itemView: LayoutLoadingFooterBinding) :
    BaseViewHolder<Any, LayoutLoadingFooterBinding>(itemView) {
    override fun onBind(item: Any, position: Int) {
    }
}