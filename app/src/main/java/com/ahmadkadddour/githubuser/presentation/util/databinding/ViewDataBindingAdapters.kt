package com.ahmadkadddour.githubuser.presentation.util.databinding

import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adyen.android.assignment.presentation.util.Constants
import com.ahmadkadddour.githubuser.presentation.util.image.loadPictureAndCache
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("android:visibility")
fun viewVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("existence")
fun viewExistence(view: View, isExists: Boolean) {
    view.visibility = if (isExists) View.VISIBLE else View.GONE
}

@BindingAdapter("goneIfEmpty")
fun goneIfEmpty(textView: TextView, check: Boolean) {
    if (check) {
        if (textView.text == null || textView.text.toString() == "") {
            textView.visibility = View.GONE
        }
    }
}

@BindingAdapter(value = ["android:src", "placeHolder"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    loadPictureAndCache(
        imageView,
        url,
        placeHolder
    )
}

@BindingAdapter("android:text")
fun integerText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("android:text")
fun doubleText(textView: TextView, number: Double) {
    textView.text = number.toString()
}

@BindingAdapter("android:textPaintFlags")
fun textPaintFlag(textView: TextView, flags: Int) {
    textView.paintFlags = textView.paintFlags or flags
}

@BindingAdapter("android:strikeThroughText")
fun strikeThroughText(textView: TextView, strikeThrough: Boolean) {
    if (strikeThrough) textPaintFlag(textView, Paint.STRIKE_THRU_TEXT_FLAG)
}

@BindingAdapter("android:colorSchemeResources")
fun swipeRefreshColors(view: SwipeRefreshLayout, color: Int) {
    view.setColorSchemeColors(color)
}

@BindingAdapter("android:orientation")
fun recyclerViewOrientation(recyclerView: RecyclerView, orientation: Int) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, orientation, false)
}

@BindingAdapter("isBold")
fun setBold(view: TextView, isBold: Boolean) {
    view.setTypeface(null, if (isBold) Typeface.BOLD else Typeface.NORMAL)
}

@BindingAdapter("hint")
fun setHint(textView: TextView, stringId: Int) {
    if (textView.text.isEmpty()) {
        val text = textView.context.getString(stringId)
        textView.text = text
    }
}

@BindingAdapter("underline")
fun setUnderline(textView: TextView, underline: Boolean) {
    if (underline) {
        textView.paintFlags = (textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG);
    }
}

// SearchView
@BindingAdapter("searchQuery")
fun setSearchQuery(searchView: SearchView, query: String) {
    if (searchView.query != null && searchView.query.toString() != query)
        searchView.setQuery(
            query,
            false
        )
}

@InverseBindingAdapter(attribute = "searchQuery")
fun getSearchQuery(searchView: SearchView): String? {
    return searchView.query?.toString()
}

@BindingAdapter("date")
fun setDate(textView: TextView, date: LocalDate?) {
    textView.text = date?.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT))
}
