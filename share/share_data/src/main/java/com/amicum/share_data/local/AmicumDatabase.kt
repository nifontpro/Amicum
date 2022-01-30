package com.amicum.share_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amicum.share_data.local.dao.UserDao
import com.amicum.share_data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AmicumDatabase : RoomDatabase() {

    abstract val userDao: UserDao
}