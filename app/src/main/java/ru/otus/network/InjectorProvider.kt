package ru.otus.network

import androidx.lifecycle.ViewModelProvider

interface InjectorProvider {
    val injector: Injector
}

interface Injector {
    fun provideViewModelFactory(): ViewModelProvider.Factory
}