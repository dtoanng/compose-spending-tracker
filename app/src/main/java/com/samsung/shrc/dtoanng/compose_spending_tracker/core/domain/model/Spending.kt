package com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model

import java.time.ZonedDateTime

data class Spending(
    val spendingId: Int?,
    val name: String,
    val price: Double,
    val kilograms: Double,
    val quantity: Double,
    val dateTimeUtc: ZonedDateTime,
    val color: Int = 0
)
