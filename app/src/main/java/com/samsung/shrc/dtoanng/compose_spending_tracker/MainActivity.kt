package com.samsung.shrc.dtoanng.compose_spending_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samsung.shrc.dtoanng.compose_spending_tracker.ui.theme.ComposespendingtrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposespendingtrackerTheme {
            }
        }
    }
}