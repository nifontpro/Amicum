package com.amicum.auth_domain.model

import androidx.compose.ui.graphics.ImageBitmap

data class AuthUser(
    val fio: String = "-",
    val post: String = "",
    val number: String = "",
    val login: String = "",
    val avatar: ImageBitmap? = null
)
