package ru.otus.network

import android.app.Application
import ru.otus.network.finish.di.InjectorFinishImpl
import ru.otus.network.start.di.InjectorStartImpl

class NetworkApplication : Application(), InjectorProvider {

    // Change this to switch between start and finish packages
    val isStartPackage = true

    private lateinit var _injector: Injector

    override val injector: Injector
        get() = _injector

    override fun onCreate() {
        super.onCreate()

        _injector = if (isStartPackage) {
            InjectorStartImpl(this)
        } else {
            InjectorFinishImpl(this)
        }
    }
}
