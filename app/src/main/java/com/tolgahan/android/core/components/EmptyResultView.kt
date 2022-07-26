package com.tolgahan.android.core.components

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.tolgahan.android.R
import com.tolgahan.android.core.ViewState


class EmptyResultView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:viewState")
        fun bindViewState(view: EmptyResultView, viewState: ViewState?) {
            viewState?.let {
                if (viewState == ViewState.EMPTY_RESULT)
                    view.visibility = VISIBLE
                else
                    view.visibility = GONE
            } ?: kotlin.run {
                view.visibility = GONE
            }
        }
    }

    init {
        inflate(context, R.layout.empty_result_view, this)
    }
}
