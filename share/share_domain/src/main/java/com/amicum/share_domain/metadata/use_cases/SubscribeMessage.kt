package com.amicum.share_domain.metadata.use_cases

import com.amicum.share_domain.metadata.repository.MetadataRepository

// Подписка на рассылаемое сообщение
class SubscribeMessage(
    private val repository: MetadataRepository
) {
    operator fun invoke() = repository.subscribeMessage()
}