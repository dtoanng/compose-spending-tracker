package com.samsung.shrc.dtoanng.compose_spending_tracker.spending_details.domain

import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model.Spending
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.SpendingDataSourceRepository

class UpsertSpendingUseCase(private val spendingDataSource: SpendingDataSourceRepository) {

    suspend operator fun invoke(spending: Spending): Boolean {

        if (spending.name.isBlank()) {
            return false
        }

        if (spending.price < 0) {
            return false
        }

        if (spending.kilograms < 0) {
            return false
        }

        if (spending.quantity < 0) {
            return false
        }

        spendingDataSource.upsertSpending(spending)
        return true
    }
}