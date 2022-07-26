package com.tolgahan.android.di

import androidx.databinding.library.BuildConfig
import com.tolgahan.android.core.config.Configuration
import com.tolgahan.android.core.factories.FlowCallAdapterFactory
import com.tolgahan.android.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @AfterLogin
    fun provideOtherApiClient(
        @AfterLogin configuration: Configuration,
        okHttpClient: OkHttpClient
    ): ApiClient = apiClient(configuration, okHttpClient)

    private fun apiClient(configuration: Configuration, okHttpClient: OkHttpClient): ApiClient {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(configuration.apiUrl())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    private val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return@lazy logging
    }

    private val headerInterceptor: Interceptor by lazy {
        Interceptor { it ->
            val requestBuilder = it.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Build", "500")

            return@Interceptor it.proceed(requestBuilder.build())
        }
    }
}