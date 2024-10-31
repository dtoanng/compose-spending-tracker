package com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation.util

import java.time.ZonedDateTime

fun ZonedDateTime.formatDate(): String {
    return "$dayOfMonth-$monthValue-$year"
}