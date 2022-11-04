package com.ahmadkadddour.githubuser.presentation.util.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import android.widget.Spinner
import android.widget.ArrayAdapter
import com.ahmadkadddour.githubuser.presentation.ui.base.adapter.BaseListAdapter

@BindingAdapter("android:loadData")
fun <T> loadData(pager: ViewPager2, data: List<T>?) {
    val adapter = pager.adapter
    if (adapter is BaseListAdapter<*, *>) {
        (adapter as? BaseListAdapter<T, *>)?.submitData(data ?: emptyList())
    }
}

@BindingAdapter("android:loadData")
fun <T> loadRecyclerData(recyclerView: RecyclerView, data: List<T>?) {
    val adapter = recyclerView.adapter
    if (adapter is BaseListAdapter<*, *>) {
        (adapter as? BaseListAdapter<T, *>)?.submitData(data ?: emptyList())
    }
}

@BindingAdapter("android:loadData")
fun <T> loadSpinnerData(spinner: Spinner, data: List<T>?) {
    val adapter = spinner.adapter
    if (adapter is ArrayAdapter<*>) {
        adapter.clear()
        (adapter as? ArrayAdapter<T>)?.addAll(data ?: emptyList())
    }
}