package ru.otus.network.domain

import ru.otus.network.data.RAMRetrofitService

class CharactersRepository(private val api: RAMRetrofitService) {
    suspend fun getAllCharacters(): Result<List<RaMCharacter>> = runCatching {
        api.getCharacters().results
            .map { dto ->
                RaMCharacter(
                    id = dto.id,
                    name = dto.name,
                    image = dto.image,
                )
            }
    }
}
