package com.tolgahan.android.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BeforeLogin

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AfterLogin