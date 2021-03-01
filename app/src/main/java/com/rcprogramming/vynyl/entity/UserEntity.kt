package com.rcprogramming.vynyl.entity

import android.view.inspector.IntFlagMapping
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @ColumnInfo(name = "username")
        var username: String,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "password")
        var password: String
)