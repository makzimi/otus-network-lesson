package ru.otus.network.presentation

import ru.otus.network.domain.RaMCharacter

data class CharactersState(
    val items: List<RaMCharacter>,
    val isLoading: Boolean,
    val isError: Boolean,
)
