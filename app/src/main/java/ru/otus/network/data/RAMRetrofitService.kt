package ru.otus.network.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.otus.network.data.dto.RAMResponseDto

interface RAMRetrofitService {

    companion object {
        const val ENDPOINT = "https://rickandmortyapi.com/api/"
    }

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int = 0): RAMResponseDto
}