package ru.otus.network.finish.presentation

import ru.otus.network.finish.domain.RaMCharacter

data class CharactersState(
    val items: List<RaMCharacter>,
    val isLoading: Boolean,
    val isError: Boolean,
)
