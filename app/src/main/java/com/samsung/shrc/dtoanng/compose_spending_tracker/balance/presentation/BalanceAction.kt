package com.samsung.shrc.dtoanng.compose_spending_tracker.balance.presentation

sealed interface BalanceAction {
    data class OnBalanceChanged(val newBalance: Double) : BalanceAction
    data object OnBalanceSaved : BalanceAction
}