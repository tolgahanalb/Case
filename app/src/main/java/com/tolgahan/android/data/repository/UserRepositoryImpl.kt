package com.tolgahan.android.data.repository

import androidx.room.withTransaction
import com.tolgahan.android.data.db.DbManager
import com.tolgahan.android.data.network.ApiClient
import com.tolgahan.android.data.network.model.response.FollowersItem
import com.tolgahan.android.data.network.model.response.SearchRequest
import com.tolgahan.android.data.network.model.response.UserModel
import com.tolgahan.android.di.AfterLogin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @AfterLogin private val apiClient: ApiClient,
    private val dbManager: DbManager
) : UserRepository {

    override suspend fun fetchUsers(query: String): SearchRequest {
        return apiClient.searchUser(query)
    }

    override fun getMovieList(): Flow<List<FollowersItem>> {
        return dbManager.getMovieDao().getMovies()
    }

    override suspend fun saveMovies(list: List<FollowersItem>) {
        dbManager.withTransaction {
            dbManager.getMovieDao().saveMovies(list)
        }
    }

    override suspend fun fetchUserDetail(username: String): UserModel {
        return apiClient.detailUser(username)
    }

    override fun getMovieDetail(id: String): Flow<FollowersItem> {
        return dbManager.getMovieDao().getMovie(id)
    }

    override suspend fun saveMovie(entity: FollowersItem) {
        dbManager.withTransaction {
            dbManager.getMovieDao().saveMovie(entity)
        }
    }

    override suspend fun deleteMovies() {
        dbManager.withTransaction {
            dbManager.getMovieDao().deleteMovies()
        }
    }
    override suspend fun deleteMovie(id: String) {
        dbManager.withTransaction {
            dbManager.getMovieDao().deleteMovie(id)
        }
    }

}