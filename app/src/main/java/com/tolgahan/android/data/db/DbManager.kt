package com.tolgahan.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tolgahan.android.data.db.dao.UserDao
import com.tolgahan.android.data.network.model.response.FollowersItem

@Database(entities = [FollowersItem::class], version = 1)
abstract class DbManager : RoomDatabase() {
    abstract fun getMovieDao(): UserDao
}