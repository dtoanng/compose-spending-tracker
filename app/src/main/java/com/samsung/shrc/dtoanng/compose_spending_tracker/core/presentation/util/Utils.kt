package com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.montserrat

@Composable
fun Background(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.3f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    )
}

val balanceStyle = TextStyle(
    fontFamily = montserrat,
    fontWeight = FontWeight.Bold,
    fontSize = 35.sp,
    color = Color.Blue.copy(alpha = 0.4f)
)