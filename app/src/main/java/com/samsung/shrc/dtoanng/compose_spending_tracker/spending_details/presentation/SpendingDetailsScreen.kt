package com.samsung.shrc.dtoanng.compose_spending_tracker.spending_details.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.montserrat
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.util.Background
import org.koin.androidx.compose.koinViewModel

@Composable
fun SpendingDetailsScreen(
    viewModel: SpendingDetailsViewModel = koinViewModel(),
    onSaveSpending: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.event.collect { event ->
            when (event) {
                SpendingDetailsEvent.SaveFailed -> {
                    Toast.makeText(
                        context,
                        "Error, make sure to enter valid spending info.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                SpendingDetailsEvent.SaveSuccess -> {
                    onSaveSpending()
                }
            }
        }
    }

    SpendingDetailsScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpendingDetailsScreen(
    state: SpendingDetailsState,
    onAction: (SpendingDetailsAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Add Spending",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = montserrat,
                            color = MaterialTheme.colorScheme.primary
                        ),
                    )
                },
                actions = {
                    val shape = RoundedCornerShape(13.dp)
                    val colorBg = MaterialTheme.colorScheme.primary
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(shape)
                            .border(
                                width = 2.dp,
                                color = colorBg.copy(alpha = 0.6f),
                                shape = shape
                            )
                            .background(
                                color = colorBg.copy(alpha = 0.3f)
                            )
                            .clickable { onAction(SpendingDetailsAction.SaveSpending) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save Spending",
                            tint = colorBg,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Background()
        Column(modifier = Modifier.padding(innerPadding)) {

            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(
                value = state.name,
                onValueChange = { onAction(SpendingDetailsAction.UpdateName(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                maxLines = 1,
                textStyle = TextStyle(
                    fontFamily = montserrat,
                    fontSize = 17.sp
                ),
                label = {
                    Text(text = "Name", fontWeight = FontWeight.Medium)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = state.price.toString(),
                onValueChange = {
                    onAction(SpendingDetailsAction.UpdatePrice(it.toDoubleOrNull() ?: 0.0))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                maxLines = 1,
                textStyle = TextStyle(
                    fontFamily = montserrat,
                    fontSize = 17.sp
                ),
                label = {
                    Text(text = "Price", fontWeight = FontWeight.Medium)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                OutlinedTextField(
                    value = state.kilograms.toString(),
                    onValueChange = {
                        onAction(SpendingDetailsAction.UpdateKilograms(it.toDoubleOrNull() ?: 0.0))
                    },
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontFamily = montserrat,
                        fontSize = 17.sp
                    ),
                    label = {
                        Text(text = "Kilograms", fontWeight = FontWeight.Medium)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = state.quantity.toString(),
                    onValueChange = {
                        onAction(SpendingDetailsAction.UpdateQuantity(it.toDoubleOrNull() ?: 0.0))
                    },
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontFamily = montserrat,
                        fontSize = 17.sp
                    ),
                    label = {
                        Text(text = "Quantity", fontWeight = FontWeight.Medium)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpendingDetailsScreenPreview() {
    SpendingDetailsScreen(
        state = SpendingDetailsState(),
        onAction = {}
    )
}