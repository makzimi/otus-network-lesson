package ru.otus.network.finish.data.dto

import com.google.gson.annotations.SerializedName

data class RAMResponseDto(
    @SerializedName("info") val info: ResponseDto?,
    @SerializedName("results") val results: List<CharacterDto>,
    @SerializedName("error") val error: String?,
)

// for kotlinx serialization
/*
@Serializable
data class RAMResponseDto(
    @SerialName("info") val info: ResponseDto?,
    @SerialName("results") val results: List<CharacterDto>,
    @SerialName("error") val error: String?
)
*/