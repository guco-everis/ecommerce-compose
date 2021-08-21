package com.example.ecommerce

import android.app.Application
import com.example.ecommerce.domain.di.DomainModule
import com.example.ecommerce.ui.di.UIModule
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    DomainModule,
                    UIModule
                )
            )
        }
    }

}