package com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository

interface CoreRepository {
    suspend fun updateBalance(balance: Double)
    suspend fun getBalance(): Double
}