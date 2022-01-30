package com.amicum.share_domain.amicum_data.use_cases

import com.amicum.share_domain.amicum_data.repository.AmicumRepository

data class GetShifts(
    private val repository: com.amicum.share_domain.amicum_data.repository.AmicumRepository
) {
    operator fun invoke() = repository.shifts
}
