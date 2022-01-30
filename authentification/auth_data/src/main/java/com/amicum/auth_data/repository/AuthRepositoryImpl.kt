package com.amicum.auth_data.repository

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.amicum.auth_domain.R
import com.amicum.auth_domain.model.AuthUser
import com.amicum.auth_domain.repository.AuthRepository
import com.example.models.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl(
    private val context: Context
) : AuthRepository {

    // Признак успешной регистрации
    private val _isAuthOk = MutableStateFlow(false)
    override fun getAuthState() = _isAuthOk.asStateFlow()

    // Вошедший в систему пользователь
    private val _authUser = MutableStateFlow(AuthUser())
    override fun  getAuthUser() = _authUser.asStateFlow()

    private fun getImageFromResource(@DrawableRes res: Int): ImageBitmap =
        BitmapFactory.decodeResource(context.resources, res).asImageBitmap()

    override suspend fun login(
        login: String,
        password: String
    ): Resource<Unit> {

        return suspendCoroutine {
            it.resume(
                try {
                    when {
                        login == "1" && password == "1" -> {
                            _isAuthOk.value = true
                            _authUser.value = AuthUser(
                                fio = "Иванов И. И.",
                                post = "Горный мастер",
                                number = "1452221",
                                login = login,
                                avatar = getImageFromResource(R.drawable.master)
                            )
                            Resource.Success()
                        }
                        login == "2" && password == "2" -> {
                            _isAuthOk.value = true
                            _authUser.value = AuthUser(
                                fio = "Чернов А. И.",
                                post = "Горный мастер",
                                number = "2235678",
                                login = login,
                                avatar = getImageFromResource(R.drawable.master2)
                            )
                            Resource.Success()
                        }
                        login == "0" && password == "0" -> {
                            Resource.Error(context.getString(R.string.sErrorNotInternet))
                        }
                        else -> {
                            Resource.Error(context.getString(R.string.sErrorIncorrectLoginOrPassword))
                        }
                    }
                } catch (e: Exception) {
                    Resource.Error(e.localizedMessage ?: context.getString(R.string.sErrorUnknown))
                }
            )
        }
    }

    override fun logout() {
        _isAuthOk.value = false
        _authUser.value = AuthUser()
    }
}