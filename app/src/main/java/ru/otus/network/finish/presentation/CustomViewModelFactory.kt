package ru.otus.network.finish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.otus.network.finish.domain.CharactersRepository

class CustomViewModelFactory(private val repository: CharactersRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharactersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
