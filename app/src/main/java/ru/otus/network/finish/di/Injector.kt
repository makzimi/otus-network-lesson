package ru.otus.network.finish.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.otus.network.Injector
import ru.otus.network.finish.data.ENDPOINT
import ru.otus.network.finish.data.RAMKtorService
import ru.otus.network.finish.data.RAMRetrofitService
import ru.otus.network.finish.data.RAMKtorServiceImpl
import ru.otus.network.finish.domain.CharactersRepository
import ru.otus.network.finish.presentation.CustomViewModelFactory

class InjectorFinishImpl(private val context: Context): Injector {

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
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(provideJson().asConverterFactory("application/json".toMediaType()))
                .build()
        return retrofit.create(RAMRetrofitService::class.java)
    }

    private fun provideJson(): Json {
        return Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    private fun provideRepository(): CharactersRepository {
        return CharactersRepository(
            provideRAMService(),
            provideRAMKtorService(),
        )
    }

    private fun provideRAMKtorService(): RAMKtorService {
        return RAMKtorServiceImpl(provideKtorClient_CIO())
    }

    override fun provideViewModelFactory(): ViewModelProvider.Factory {
        return CustomViewModelFactory(provideRepository())
    }

    private fun provideKtorClient_CIO(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) { json(provideJson()) }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("DBG: Logger Ktor -> $message")
                    }
                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    println("DBG: Http status: ${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    private fun provideKtorClient_Android(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
        }
    }

    private fun provideKtorClient_OkHttp(): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                preconfigured = provideOkHttpClient()
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
        }
    }
}