package com.tolgahan.android.data.repository

import com.tolgahan.android.data.network.model.response.FollowersItem
import com.tolgahan.android.data.network.model.response.SearchRequest
import com.tolgahan.android.data.network.model.response.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getMovieList(): Flow<List<FollowersItem>>

    suspend fun fetchUsers(query: String): SearchRequest

    suspend fun saveMovies(list: List<FollowersItem>)

    fun getMovieDetail(id: String): Flow<FollowersItem>

    suspend fun fetchUserDetail(username: String): UserModel

    suspend fun saveMovie(entity: FollowersItem)

    suspend fun deleteMovies()

    suspend fun deleteMovie(id: String)
}