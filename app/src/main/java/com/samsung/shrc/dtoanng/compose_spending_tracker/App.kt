package com.samsung.shrc.dtoanng.compose_spending_tracker

import android.app.Application
import com.samsung.shrc.dtoanng.compose_spending_tracker.balance.di.balanceModule
import com.samsung.shrc.dtoanng.compose_spending_tracker.core.di.coreModule
import com.samsung.shrc.dtoanng.compose_spending_tracker.spendingoverview.di.spendingOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)

            modules(
                coreModule,
                balanceModule,
                spendingOverviewModule
            )
        }

    }


}