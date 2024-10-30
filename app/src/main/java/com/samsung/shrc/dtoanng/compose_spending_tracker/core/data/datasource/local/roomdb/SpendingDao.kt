package com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.roomdb

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.roomdb.entities.SpendingEntity

@Dao
interface SpendingDao {

    @Upsert
    suspend fun upsertSpending(entity: SpendingEntity)

    @Query("SELECT * FROM spendingentity")
    suspend fun getAllSpending(): List<SpendingEntity>

    @Query("SELECT * FROM spendingentity WHERE spendingId = :id")
    suspend fun getSpendingById(id: Int): SpendingEntity

    @Query("SELECT dateTimeUtc FROM spendingentity")
    suspend fun getAllDates() : List<String>

    @Query("SELECT SUM(price) FROM SpendingEntity")
    suspend fun getSpendBalance(): Double?

    @Query("DELETE FROM spendingentity WHERE spendingId = :id")
    suspend fun deleteSpending(id: Int)
}