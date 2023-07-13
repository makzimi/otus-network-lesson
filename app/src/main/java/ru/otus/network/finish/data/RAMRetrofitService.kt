package ru.otus.network.finish.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.otus.network.finish.data.dto.RAMResponseDto

interface RAMRetrofitService {
    @GET(CHARACTER_METHOD)
    suspend fun getCharacters(@Query("page") page: Int = 0): RAMResponseDto
}