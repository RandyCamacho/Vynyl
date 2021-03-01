package com.rcprogramming.vynyl.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rcprogramming.vynyl.dao.UserDao
import com.rcprogramming.vynyl.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class VynylDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var vynylDatabase: VynylDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): VynylDatabase {
            if (vynylDatabase == null) {
                vynylDatabase = Room.databaseBuilder(
                        context
                        , VynylDatabase::class.java
                        , "vynyl.db"
                ).build()
            }
            return vynylDatabase!!
        }
    }
}