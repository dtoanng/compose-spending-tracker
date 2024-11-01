package com.samsung.shrc.dtoanng.compose_spending_tracker.spending_details.di

import com.samsung.shrc.dtoanng.compose_spending_tracker.spending_details.domain.UpsertSpendingUseCase
import com.samsung.shrc.dtoanng.compose_spending_tracker.spending_details.presentation.SpendingDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val spendingDetailsModule = module {
    single { UpsertSpendingUseCase(get()) }
    viewModel { SpendingDetailsViewModel(get()) }
}