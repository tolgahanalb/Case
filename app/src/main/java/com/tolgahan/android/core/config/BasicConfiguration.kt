package com.tolgahan.android.core.config

import com.tolgahan.android.core.constants.AppConstants


class BasicConfiguration : Configuration {
    override fun apiUrl(): String {
        return AppConstants.BasicUrl
    }
}