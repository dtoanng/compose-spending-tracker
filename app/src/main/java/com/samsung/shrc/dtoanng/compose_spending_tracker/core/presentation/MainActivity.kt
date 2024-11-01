package com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.navigation.Screen
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.ui.theme.ComposespendingtrackerTheme
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.presentation.util.Background
import com.samsung.shrc.dtoanng.compose_spending_tracker.spending_details.presentation.SpendingDetailsScreen
import com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.presentation.SpendingOverviewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposespendingtrackerTheme {
                Navigation(modifier = Modifier.fillMaxSize())
            }
        }
    }

    @Composable
    fun Navigation(modifier: Modifier = Modifier) {

        val navController = rememberNavController()
        Background()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Screen.SpendingOverview,
            enterTransition = { slideInHorizontally { it } },
            exitTransition = { slideOutHorizontally { -it } },
            popEnterTransition = { slideInHorizontally { -it } },
            popExitTransition = { slideOutHorizontally { it } }
        ) {
            composable<Screen.SpendingOverview> {
                SpendingOverviewScreen(
                    onBalanceClick = { navController.navigate(Screen.Balance) },
                    onAddSpendingClick = { navController.navigate(Screen.SpendingDetails(-1)) }
                )
            }

            composable<Screen.SpendingDetails> {
                SpendingDetailsScreen(
                    onSaveSpending = {
                        navController.popBackStack()
                    }
                )
            }

            composable<Screen.Balance> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Balance Screen")
                }
            }
        }
    }
}