package com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository

import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model.Spending
import java.time.ZonedDateTime

interface SpendingDataSourceRepository {
    suspend fun getAllSpending(): List<Spending>
    suspend fun getSpendingByDate(dateTime: ZonedDateTime): List<Spending>
    suspend fun getAllDates(): List<ZonedDateTime>
    suspend fun getSpending(id: Int): Spending
    suspend fun upsertSpending(spending: Spending)
    suspend fun getSpendBalance(): Double
    suspend fun deleteSpending(id: Int)
}