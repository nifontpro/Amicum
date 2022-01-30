package com.amicum.share_data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.amicum.share_data.local.entity.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Transaction
    @Query("SELECT * FROM UserEntity WHERE login = :login AND password = :password")
    suspend fun getUser(login: String, password: String): UserEntity

}