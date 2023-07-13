package ru.otus.network.finish.data.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
)

// for kotlinx serialization
/*
@Serializable
data class CharacterDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
)
*/