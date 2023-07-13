package ru.otus.network.finish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.otus.network.finish.domain.CharactersRepository
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(loadingState())
    val state: StateFlow<CharactersState> = _state.asStateFlow()

    private fun loadingState() = CharactersState(
        items = listOf(),
        isLoading = true,
        isError = false,
    )

    init {
        requestCharacters()
    }

    fun refresh() {
        requestCharacters()
    }

    private fun requestCharacters() {
        viewModelScope.launch {
            repository.getAllCharacters()
                .onSuccess { characters ->
                    _state.value = CharactersState(
                        items = characters,
                        isLoading = false,
                        isError = false,
                    )
                }
                .onFailure {
                    _state.value = CharactersState(
                        items = listOf(),
                        isLoading = false,
                        isError = true,
                    )
                }
        }
    }
}
