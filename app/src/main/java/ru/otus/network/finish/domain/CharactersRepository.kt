package ru.otus.network.finish.domain

import ru.otus.network.finish.data.RAMKtorService
import ru.otus.network.finish.data.RAMRetrofitService
import ru.otus.network.finish.data.dto.CharacterDto

class CharactersRepository(
    private val api: RAMRetrofitService,
    private val ktorService: RAMKtorService,
) {
    suspend fun getAllCharacters(): Result<List<RaMCharacter>> = runCatching {
        //ktorService.getCharacters().results
        api.getCharacters().results
            .map(::toDomain)
    }

    private fun toDomain(dto: CharacterDto): RaMCharacter {
        return RaMCharacter(
            id = dto.id,
            name = dto.name,
            image = dto.image,
        )
    }
}
