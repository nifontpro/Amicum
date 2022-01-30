package com.amicum.share_domain.metadata.repository

import android.content.Context
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

// Репозиторий для совместных данных
class MetadataRepositoryImpl(
    private val context: Context
) : MetadataRepository {

    // Сообщение
    private val _message = MutableSharedFlow<String>()
    private val message = _message.asSharedFlow()

    // Рассылает сообщение всем подписчикам
    override suspend fun sendMessage(msg: String) {
        _message.emit(msg)
    }

    override fun subscribeMessage(): SharedFlow<String> = message
}