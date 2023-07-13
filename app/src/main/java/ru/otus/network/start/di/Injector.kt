package ru.otus.network.start.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.otus.network.Injector
import ru.otus.network.start.domain.CharactersRepository
import ru.otus.network.start.presentation.CustomViewModelFactory

class InjectorStartImpl(private val context: Context): Injector {

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun provideRepository(): CharactersRepository {
        return CharactersRepository()
    }

    override fun provideViewModelFactory(): ViewModelProvider.Factory {
        return CustomViewModelFactory(provideRepository())
    }
}