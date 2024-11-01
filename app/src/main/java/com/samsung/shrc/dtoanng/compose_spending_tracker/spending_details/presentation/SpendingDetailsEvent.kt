package com.samsung.shrc.dtoanng.compose_spending_tracker.spending_details.presentation

sealed interface SpendingDetailsEvent {
    data object SaveSuccess : SpendingDetailsEvent
    data object SaveFailed : SpendingDetailsEvent
}