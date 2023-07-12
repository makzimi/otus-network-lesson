package ru.otus.network

import android.app.Application
import ru.otus.network.di.Injector
import ru.otus.network.di.InjectorProvider

class NetworkApplication : Application(), InjectorProvider {

    private lateinit var _injector: Injector

    override val injector: Injector
        get() = _injector

    override fun onCreate() {
        super.onCreate()

        _injector = Injector(this)
    }
}
