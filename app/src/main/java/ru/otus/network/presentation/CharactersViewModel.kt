package ru.otus.network.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.otus.network.domain.CharactersRepository
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(initialValue())
    val state: StateFlow<CharactersState> = _state.asStateFlow() // read only

    private val sharedFlow = MutableSharedFlow<CharactersState>(
        replay = 0, extraBufferCapacity = 1,
    )

    private fun initialValue(): CharactersState {
        return CharactersState(
            items = listOf(),
            isLoading = true,
            isError = false,
        )
    }

    init {
        requestCharacters()

        viewModelScope.launch {
            sharedFlow.emit(initialValue())
        }

        sharedFlow.tryEmit(initialValue())
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
