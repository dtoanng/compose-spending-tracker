package com.samsung.shrc.dtoanng.compose_spending_tracker.core.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object SpendingOverview: Screen

    @Serializable
    data class SpendingDetails(val spendingId: Int = -1): Screen

    @Serializable
    data object Balance: Screen
}