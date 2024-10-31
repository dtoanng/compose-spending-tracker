package com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation

sealed interface SpendingOverviewAction {
    data object LoadSpendingOverviewAndBalance : SpendingOverviewAction
    data class OnDateChange(val newDate: Int) : SpendingOverviewAction
    data class OnDeleteSpending(val spendingId: Int) : SpendingOverviewAction
}