package com.tolgahan.android.core.components

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.tolgahan.android.R
import com.tolgahan.android.core.ViewState


class ProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:viewState")
        fun bindViewState(view: ProgressView, viewState: ViewState?) {
            viewState?.let {
                if (viewState == ViewState.LOADING)
                    view.visibility = VISIBLE
                else
                    view.visibility = GONE
            }?: kotlin.run {
                view.visibility = GONE
            }
        }
    }

    init {
        inflate(context, R.layout.view_progress, this)
    }
}