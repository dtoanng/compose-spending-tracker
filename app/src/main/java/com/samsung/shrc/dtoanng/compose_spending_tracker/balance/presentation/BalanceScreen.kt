package com.samsung.shrc.dtoanng.compose_spending_tracker.balance.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.ComposespendingtrackerTheme
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.montserrat
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.util.Background
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.util.balanceStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceScreen(
    state: BalanceState,
    onAction: (BalanceAction) -> Unit,
    onSaveClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(start = 12.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Update Balance",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = montserrat,
                            color = MaterialTheme.colorScheme.primary
                        ),
                    )
                },
            )
        }
    ) { innerPadding ->
        Background()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "$${state.balance}",
                style = balanceStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(38.dp))
            OutlinedTextField(
                value = state.balance.toString(),
                onValueChange = {
                    onAction(BalanceAction.OnBalanceChanged(it.toDoubleOrNull() ?: 0.0))
                },
                label = {
                    Text(text = "Enter balance", color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = montserrat,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(38.dp))
            OutlinedButton(onClick = onSaveClick) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save balance",
                        modifier = Modifier.size(33.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Save",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BalanceScreenPreview() {
    ComposespendingtrackerTheme {
        BalanceScreen(state = BalanceState(), onAction = {}, onSaveClick = {})
    }
}