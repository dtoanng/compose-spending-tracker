package com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.domain.model.Spending
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.Blur
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.ComposespendingtrackerTheme
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.montserrat
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.util.Background
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.util.balanceStyle
import com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation.util.formatDate
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import org.koin.androidx.compose.koinViewModel

@Composable
fun SpendingOverviewScreen(
    viewModel: SpendingOverviewViewModel = koinViewModel(),
    onBalanceClick: () -> Unit,
    onAddSpendingClick: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.onAction(SpendingOverviewAction.LoadSpendingOverviewAndBalance)
    }

    SpendingViews(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onBalanceClick = onBalanceClick,
        onAddSpendingClick = onAddSpendingClick,
        onDeleteSpendingClick = {
            viewModel.onAction(SpendingOverviewAction.OnDeleteSpending(it))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpendingViews(
    state: SpendingOverviewState,
    onAction: (SpendingOverviewAction) -> Unit,
    onBalanceClick: () -> Unit,
    onAddSpendingClick: () -> Unit,
    onDeleteSpendingClick: (Int) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    val hazeState = remember { HazeState() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(
                scrollBehavior.nestedScrollConnection
            )
            .background(MaterialTheme.colorScheme.background),
        containerColor = Color.Transparent,
        topBar = {
            Column {
                OverviewTopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .hazeChild(hazeState),
                    scrollBehavior = scrollBehavior,
                    balance = state.balance,
                    onBalanceClick = onBalanceClick
                )

                Spacer(modifier = Modifier.height(8.dp))

                DatePickerDropdownMenu(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 8.dp),
                    state = state,
                    onItemClick = { index ->
                        onAction(SpendingOverviewAction.OnDateChange(index))
                    }
                )
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    onAddSpendingClick()
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, Modifier.background(Color.Transparent))
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    ) { innerPadding ->
        Background()
        SpendingList(
            modifier = Modifier.fillMaxSize(),
            paddingValue = innerPadding,
            state = state,
            hazeState = hazeState,
            onDeleteSpendingClick = onDeleteSpendingClick
        )
    }
}

@Composable
fun SpendingList(
    modifier: Modifier = Modifier,
    paddingValue: PaddingValues,
    state: SpendingOverviewState,
    hazeState: HazeState,
    onDeleteSpendingClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .haze(state = hazeState, style = HazeStyle(blurRadius = 13.dp, tint = Blur)),
        contentPadding = PaddingValues(top = paddingValue.calculateTopPadding() + 20.dp, bottom = 80.dp)
    ) {
        itemsIndexed(state.spendingList) { index, spending ->

            SpendingItem(
                spending = spending,
                onDeleteSpending = { onDeleteSpendingClick(index) },
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpendingItem(
    modifier: Modifier = Modifier,
    spending: Spending,
    onDeleteSpending: () -> Unit
) {

    var isDeleteShowing by rememberSaveable {
        mutableStateOf(false)
    }

    val shape = RoundedCornerShape(22.dp)
    Box {
        ElevatedCard(
            shape = shape,
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            ),
            modifier = modifier
                .height(150.dp)
                .padding(horizontal = 16.dp)
                .clip(shape = shape)
                .combinedClickable(
                    onClick = {},
                    onLongClick = {
                        isDeleteShowing = !isDeleteShowing
                    }
                ),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color(spending.color))
                    .padding(horizontal = 18.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = spending.name,
                    maxLines = 1,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 23.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(1.dp))

                SpendingInfo(
                    name = "Price",
                    value = "$${spending.price}"
                )
                SpendingInfo(
                    name = "Kilograms",
                    value = "${spending.kilograms}"
                )
                SpendingInfo(
                    name = "Quantity",
                    value = "${spending.quantity}"
                )
            }

        }

        DropdownMenu(
            expanded = isDeleteShowing,
            onDismissRequest = { isDeleteShowing = false },
            offset = DpOffset(30.dp, 0.dp)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Delete Spending",
                        fontFamily = montserrat
                    )
                },
                onClick = {
                    isDeleteShowing = false
                    onDeleteSpending()
                }
            )
        }
    }

}

@Composable
fun SpendingInfo(
    name: String,
    value: String
) {
    Row {
        Text(
            "$name: ",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
        )
        Text(
            value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OverviewTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    balance: Double,
    onBalanceClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = "$${balance}", style = balanceStyle)
        },
        scrollBehavior = scrollBehavior,
        actions = {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)
                    )
                    .clickable {
                        onBalanceClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        )
    )
}

@Composable
fun DatePickerDropdownMenu(
    modifier: Modifier,
    state: SpendingOverviewState,
    onItemClick: (Int) -> Unit
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.shadow(
            elevation = 0.5.dp,
            shape = RoundedCornerShape(15.dp)
        )
    ) {
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            offset = DpOffset(10.dp, 0.dp),
            modifier = Modifier.heightIn(max = 440.dp)
        ) {

            state.dateList.forEachIndexed { index, zonedDateTime ->

                if (index == 0) {
                    HorizontalDivider()
                }

                Text(
                    text = zonedDateTime.formatDate(),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            isExpanded = false
                            onItemClick(index)
                        }
                )

                HorizontalDivider()
            }

        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { isExpanded = true }
                .padding(horizontal = 15.dp, vertical = 12.dp)
        ) {

            Text(
                text = state.pickedDate.formatDate(),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription = "Pick a date")

        }
    }
}

@Preview
@Composable
fun SpendingOverviewScreenPreview() {
    ComposespendingtrackerTheme {
        SpendingViews(
            state = SpendingOverviewState(),
            onAction = {},
            onBalanceClick = {},
            onAddSpendingClick = {},
            onDeleteSpendingClick = {}
        )
    }
}