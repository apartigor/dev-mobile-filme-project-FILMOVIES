package com.example.projeto_filmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto_filmovies.data.Filme
import com.example.projeto_filmovies.data.FilmeRepository
import com.example.projeto_filmovies.data.InMemoryFilmeDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmeViewModel : ViewModel() {

    private val repository = FilmeRepository(InMemoryFilmeDao())

    private val _filmes = MutableStateFlow<List<Filme>>(emptyList())
    val filmes: StateFlow<List<Filme>> = _filmes.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFilmes().collect {
                _filmes.value = it
            }
        }
    }

    fun salvarFilme(titulo: String, ano: String) {
        val anoInt = ano.toIntOrNull() ?: 0
        viewModelScope.launch {
            repository.insert(Filme(0, titulo.uppercase(), anoInt))
        }
    }

    fun atualizarFilme(filme: Filme) {
        viewModelScope.launch {
            repository.update(filme.copy(titulo = filme.titulo.uppercase()))
        }
    }

    fun excluirFilme(filme: Filme) {
        viewModelScope.launch {
            repository.delete(filme)
        }
    }
}