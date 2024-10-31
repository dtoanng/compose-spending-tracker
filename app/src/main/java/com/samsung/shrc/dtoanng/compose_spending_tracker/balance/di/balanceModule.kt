package com.samsung.shrc.dtoanng.compose_spending_tracker.balance.di

import com.samsung.shrc.dtoanng.compose_spending_tracker.balance.presentation.BalanceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val balanceModule = module {
    viewModel {
        BalanceViewModel(get())
    }
}