package com.amicum.share_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(

    val login: String,
    val password: String,

    val fio: String,
    val post: String,

    @PrimaryKey
    val number: String

)