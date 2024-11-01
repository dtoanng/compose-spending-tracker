package com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model.Spending
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.CoreRepository
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.repository.SpendingDataSourceRepository
import com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation.util.randomColor
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class SpendingOverviewViewModel(
    private val spendingDataSourceRepository: SpendingDataSourceRepository,
    private val coreRepository: CoreRepository
) : ViewModel() {

    var state by mutableStateOf(SpendingOverviewState())
        private set

    fun onAction(action: SpendingOverviewAction) {
        when (action) {
            is SpendingOverviewAction.LoadSpendingOverviewAndBalance -> {
                loadSpendingListAndBalance()
            }

            is SpendingOverviewAction.OnDateChange -> {
                //todo
            }

            is SpendingOverviewAction.OnDeleteSpending -> {
                //todo
            }
        }
    }

    private fun loadSpendingListAndBalance() {
        viewModelScope.launch {
            val allDates = spendingDataSourceRepository.getAllDates()

            state = state.copy(
                spendingList = getSpendingListByDate(allDates.lastOrNull() ?: ZonedDateTime.now()),
                balance = coreRepository.getBalance() - spendingDataSourceRepository.getSpendBalance(),
                dateList = allDates.reversed()
            )

//            state = state.copy(
//                spendingList = dummy
//            )
        }
    }

    private suspend fun getSpendingListByDate(date: ZonedDateTime): List<Spending> {
        return spendingDataSourceRepository.getSpendingByDate(date).reversed().map { it.copy(color = randomColor()) }
    }

}

val dummy  = listOf(
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
    Spending(
        price = 23.3,
        name = "name",
        kilograms = 24.4,
        dateTimeUtc = ZonedDateTime.now(),
        color = randomColor(),
        quantity = 34.3,
        spendingId = null
    ),
)