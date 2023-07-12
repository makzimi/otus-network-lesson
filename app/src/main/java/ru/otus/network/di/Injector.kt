package ru.otus.network.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.otus.network.data.RAMRetrofitService
import ru.otus.network.domain.CharactersRepository
import ru.otus.network.presentation.CustomViewModelFactory
import ru.otus.network.presentation.finish.FinishCustomViewModelFactory

class Injector(private val context: Context) {

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun provideRAMService(): RAMRetrofitService {
        val retrofit =
            Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(RAMRetrofitService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(RAMRetrofitService::class.java)
    }

    private fun provideRepository(): CharactersRepository {
        return CharactersRepository(provideRAMService())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return CustomViewModelFactory(provideRepository())
    }

    fun provideFinishViewModelFactory(): ViewModelProvider.Factory {
        return FinishCustomViewModelFactory(provideRepository())
    }
}