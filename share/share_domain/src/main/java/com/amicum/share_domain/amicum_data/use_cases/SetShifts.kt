package com.amicum.share_domain.amicum_data.use_cases

import com.amicum.share_domain.amicum_data.model.Shift

data class SetShifts(
    private val repository: com.amicum.share_domain.amicum_data.repository.AmicumRepository
) {
    operator fun invoke(shiftList: List<Shift>) =
        repository.setShiftList(shiftList)
}
