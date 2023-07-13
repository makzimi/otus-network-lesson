package ru.otus.network.start.presentation

import ru.otus.network.start.domain.RaMCharacter

data class CharactersState(
    val items: List<RaMCharacter>,
    val isLoading: Boolean,
    val isError: Boolean,
)
