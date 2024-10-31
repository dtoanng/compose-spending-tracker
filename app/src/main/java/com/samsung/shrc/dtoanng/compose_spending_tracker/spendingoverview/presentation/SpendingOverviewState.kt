package com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation

import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model.Spending
import java.time.ZonedDateTime

data class SpendingOverviewState(
    val spendingList: List<Spending> = emptyList(),
    val dateList: List<ZonedDateTime> = emptyList(),
    val balance: Double = 0.0,
    val pickedDate: ZonedDateTime = ZonedDateTime.now(),
    val isDropDownMenuVisible: Boolean = false
)