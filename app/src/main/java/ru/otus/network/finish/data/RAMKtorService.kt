package ru.otus.network.finish.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.otus.network.finish.data.dto.RAMResponseDto

interface RAMKtorService {
    suspend fun getCharacters(): RAMResponseDto
}

class RAMKtorServiceImpl(
    private val httpClient: HttpClient,
): RAMKtorService {
    override suspend fun getCharacters(): RAMResponseDto {
        return httpClient.get("$ENDPOINT/$CHARACTER_METHOD").body()
    }
}
