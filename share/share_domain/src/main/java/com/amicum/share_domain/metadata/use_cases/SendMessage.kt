package com.amicum.share_domain.metadata.use_cases

import com.amicum.share_domain.metadata.repository.MetadataRepository


// Отправить сообщение для рассылки
class SendMessage(
    private val repository: MetadataRepository
) {
    suspend operator fun invoke(message: String) = repository.sendMessage(message)
}