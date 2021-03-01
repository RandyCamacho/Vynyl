package com.rcprogramming.vynyl.dao

import androidx.room.*
import com.rcprogramming.vynyl.entity.UserEntity
import java.util.concurrent.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users WHERE username =:username AND password =:password")
    fun getUser(username: String, password: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Query("DELETE FROM users")
    fun nukeTable()

}