package com.tolgahan.android.core.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tolgahan.android.R
import com.tolgahan.android.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform


fun AppCompatImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_foreground)
        .centerCrop()
        .into(this)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun <T> Flow<Resource<T>>.onError(action: suspend (Throwable?) -> Unit): Flow<Resource<T>> =
    transform { value ->
        if (value.error is Resource.Error<*>) {
            action(value.error)
        }
        return@transform emit(value)
    }

fun <T> Flow<Resource<T>>.onCollectOnline(action: suspend (T?) -> Unit): Flow<Resource<T>> =
    transform { value ->
        if (value is Resource.SuccessOnline<*>) {
            action(value.data)
        }
        return@transform emit(value)
    }

fun <T> Flow<Resource<T>>.onCollectOffline(action: suspend (T?) -> Unit): Flow<Resource<T>> =
    transform { value ->
        if (value is Resource.SuccessOffline<*>) {
            action(value.data)
        }
        return@transform emit(value)
    }