package com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.roomdb.entities.SpendingEntity


@Database(
    entities = [SpendingEntity::class],
    version = 1
)
abstract class SpendingDatabase : RoomDatabase() {
    abstract val dao: SpendingDao
}