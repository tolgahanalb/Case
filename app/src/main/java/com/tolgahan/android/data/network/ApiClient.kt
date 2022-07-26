package com.tolgahan.android.data.network

import com.tolgahan.android.data.network.model.response.SearchRequest
import com.tolgahan.android.data.network.model.response.UserModel
import retrofit2.http.*


interface ApiClient {

    @GET("/users/{username}")
    suspend fun detailUser(@Path("username") username: String): UserModel


    @GET("/search/users")
    suspend fun searchUser(
        @Query("q") username: String?
    ): SearchRequest
}