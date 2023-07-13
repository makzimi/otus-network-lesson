package ru.otus.network.start.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.otus.network.Injector
import ru.otus.network.start.domain.CharactersRepository
import ru.otus.network.start.presentation.CustomViewModelFactory

class InjectorStartImpl(private val context: Context): Injector {

    private fun provideRepository(): CharactersRepository {
        return CharactersRepository()
    }

    override fun provideViewModelFactory(): ViewModelProvider.Factory {
        return CustomViewModelFactory(provideRepository())
    }
}