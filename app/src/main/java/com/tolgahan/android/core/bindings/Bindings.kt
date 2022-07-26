package com.tolgahan.android.core.bindings

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.tolgahan.android.R
import com.tolgahan.android.core.extension.loadImage

@BindingAdapter("bind:visibility")
fun visible(view: View, boolean: Boolean?) {
    boolean?.let {
        if (it)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }
}

@BindingAdapter("bind:image")
fun bindImage(view: AppCompatImageView, url: String?) {
    url?.let {
        view.loadImage(it)
    }
}

@BindingAdapter("bind:itemDecorationColor")
fun setItemDecoration(view: RecyclerView, @ColorInt colorInt: Int?) {
    DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL).apply {
        colorInt?.let {
            setDrawable(ColorDrawable(colorInt))
        }
        view.addItemDecoration(this)
    }
}

@BindingAdapter("bind:favClicked")
fun setFavButtonClicked(view: AppCompatButton, isClicked: Boolean) {
    view.setBackgroundResource(if (isClicked) R.mipmap.star_filled else R.mipmap.star)
}