package com.samsung.shrc.dtoanng.compose_spending_tracker.balance.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.CoreRepository
import kotlinx.coroutines.launch

class BalanceViewModel(private val coreRepository: CoreRepository) : ViewModel() {

    var state by mutableStateOf(BalanceState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                balance = coreRepository.getBalance()
            )
        }
    }

    fun OnAction(action: BalanceAction) {

        when (action) {
            is BalanceAction.OnBalanceChanged -> {
                viewModelScope.launch {
                    state = state.copy(
                        balance = action.newBalance
                    )
                }
            }

            is BalanceAction.OnBalanceSaved -> {
                viewModelScope.launch {
                    coreRepository.updateBalance(state.balance)
                }
            }
        }
    }
}