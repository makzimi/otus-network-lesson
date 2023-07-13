package ru.otus.network.start.domain


class CharactersRepository() {
    suspend fun getAllCharacters(): Result<List<RaMCharacter>> = runCatching {
        listOf<Unit>()
            .map { toDomain() }
    }

    private fun toDomain(): RaMCharacter {
        return RaMCharacter(
            id = 0,
            name = "",
            image = "",
        )
    }
}
