package com.ahmadkadddour.githubuser.presentation.ui.base.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, VH : BaseViewHolder<T, *>>(
    protected var context: Context
) : RecyclerView.Adapter<VH>() {

    private var data = mutableListOf<T>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.onBind(item, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getData(): List<T> {
        return data
    }

    fun provideInflater(): LayoutInflater {
        return LayoutInflater.from(context)
    }

    // Clear previous data, then add the new data
    fun submitData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    // Add the data to old data
    fun insertData(data: List<T>) {
        val insertIndex = this.data.size
        this.data.addAll(data)
        notifyItemRangeInserted(insertIndex, data.size)
    }

    fun insertItem(item: T) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun insertItem(item: T, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItem(item: T) {
        val index = data.indexOf(item)
        if (index == -1) return  // not found
        data.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, data.size - 1)
    }

    open fun removeItem(index: Int) {
        if (index < 0) return
        data.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, data.size - 1)
    }

    open fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    val isEmptyData: Boolean
        get() = data.isEmpty()
}