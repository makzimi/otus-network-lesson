package ru.otus.network.finish.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?,
)

// for kotlinx serialization
/*
@Serializable
data class ResponseDto(
    @SerialName("count") val count: Int,
    @SerialName("pages") val pages: Int,
    @SerialName("next") val next: String?,
    @SerialName("prev") val prev: String?,
)
*/