package com.tolgahan.android.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolgahan.android.data.network.model.response.FollowersItem
import com.tolgahan.android.data.network.model.response.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(list: List<FollowersItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(entity: FollowersItem)

    @Query("SELECT * FROM FollowersItem")
    fun getMovies(): Flow<List<FollowersItem>>

    @Query("SELECT * FROM FollowersItem WHERE id=:id")
    fun getMovie(id: String): Flow<FollowersItem>

    @Query("DELETE FROM FollowersItem")
    fun deleteMovies()

    @Query("DELETE FROM FollowersItem WHERE id=:id")
    fun deleteMovie(id:String)
}