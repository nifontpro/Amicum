package com.amicum.share_domain.metadata.repository

import kotlinx.coroutines.flow.SharedFlow

/**
 * Репозиторий для совместных данных (сообщений и др. управляющих данных)
 */

interface MetadataRepository {

    // Рассылает сообщение всем подписчикам
    suspend fun sendMessage(msg: String)

    fun subscribeMessage(): SharedFlow<String>
}