package com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.mapper

import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.roomdb.entities.SpendingEntity
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model.Spending
import java.time.Instant
import java.time.ZoneId

fun SpendingEntity.toSpending(): Spending = Spending(
    spendingId = spendingId ?: 0,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = Instant.parse(dateTimeUtc).atZone(ZoneId.of("UTC"))
)

fun Spending.toSpendingEntity(): SpendingEntity = SpendingEntity(
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)

fun Spending.toNewSpendingEntity(): SpendingEntity = SpendingEntity(
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)