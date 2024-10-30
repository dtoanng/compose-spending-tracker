package com.samsung.shrc.dtoanng.compose_spending_tracker.core.di

import androidx.room.Room
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.datastore.PreferenceDataStoreHelper
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.roomdb.SpendingDatabase
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.repository.CoreRepositoryImpl
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.repository.SpendingDataSourceRepositoryImpl
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.CoreRepository
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.SpendingDataSourceRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpendingDatabase::class.java,
            "spending_database_db"
        ).build()
    }

    single {
        PreferenceDataStoreHelper(androidApplication())
    }

    single { get<SpendingDatabase>().dao }

    singleOf(::SpendingDataSourceRepositoryImpl).bind<SpendingDataSourceRepository>()
    singleOf(::CoreRepositoryImpl).bind<CoreRepository>()
}