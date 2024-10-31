package com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.di

import com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation.SpendingOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val spendingOverviewModule = module {
    viewModel {
        SpendingOverviewViewModel(get(), get())
    }
}