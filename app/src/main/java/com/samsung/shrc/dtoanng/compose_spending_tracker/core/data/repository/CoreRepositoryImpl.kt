package com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.repository

import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.datastore.PreferenceDataStoreConstants
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.data.datasource.local.datastore.PreferenceDataStoreHelper
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.CoreRepository
import kotlinx.coroutines.flow.first

class CoreRepositoryImpl(private val dataStoreHelper: PreferenceDataStoreHelper) : CoreRepository {
    override suspend fun getBalance(): Double {
        return dataStoreHelper.getPreference(PreferenceDataStoreConstants.CONSTANT_KEY, 0f.toString()).first().toDouble()
    }

    override suspend fun updateBalance(balance: Double) {
        dataStoreHelper.putPreference(PreferenceDataStoreConstants.CONSTANT_KEY, balance.toString())
    }
}