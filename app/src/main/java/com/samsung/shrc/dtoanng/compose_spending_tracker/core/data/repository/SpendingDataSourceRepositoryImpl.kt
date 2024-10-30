package com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.repository

import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.roomdb.SpendingDao
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.mapper.toNewSpendingEntity
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.mapper.toSpending
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model.Spending
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.SpendingDataSourceRepository
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class SpendingDataSourceRepositoryImpl(private val dao: SpendingDao) : SpendingDataSourceRepository {
    override suspend fun getAllSpending(): List<Spending> {
        return dao.getAllSpending().map { it.toSpending() }
    }

    override suspend fun getSpendingByDate(dateTime: ZonedDateTime): List<Spending> {
        return dao.getAllSpending()
            .map { it.toSpending() }
            .filter { spending: Spending ->
                spending.dateTimeUtc.dayOfMonth == dateTime.dayOfMonth
                        && spending.dateTimeUtc.month == dateTime.month
                        && spending.dateTimeUtc.year == dateTime.year
            }
    }

    override suspend fun getAllDates(): List<ZonedDateTime> {
        val uniqueDates = mutableSetOf<ZonedDateTime>()
        return dao.getAllDates().map {
            Instant.parse(it).atZone(ZoneId.of("UTC"))
        }.filter {
            uniqueDates.add(it)
        }
    }

    override suspend fun getSpending(id: Int): Spending {
        return dao.getSpendingById(id).toSpending()
    }

    override suspend fun upsertSpending(spending: Spending) {
        dao.upsertSpending(spending.toNewSpendingEntity())
    }

    override suspend fun getSpendBalance(): Double {
        return dao.getSpendBalance() ?: 0.0
    }

    override suspend fun deleteSpending(id: Int) {
        dao.deleteSpending(id)
    }

}