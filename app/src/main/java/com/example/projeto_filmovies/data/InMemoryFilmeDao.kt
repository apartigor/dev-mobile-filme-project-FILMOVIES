package com.example.projeto_filmovies.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class InMemoryFilmeDao : FilmeDao {

    private val filmesFlow = MutableStateFlow<List<Filme>>(emptyList())
    private val filmes = mutableListOf<Filme>()
    private var nextId = 1

    override fun getFilmes(): Flow<List<Filme>> = filmesFlow

    override suspend fun insert(filme: Filme) {
        filmes.add(filme.copy(id = nextId++))
        filmesFlow.value = filmes.toList()
    }

    override suspend fun update(filme: Filme) {
        val index = filmes.indexOfFirst { it.id == filme.id }
        if (index != -1) {
            filmes[index] = filme
            filmesFlow.value = filmes.toList()
        }
    }

    override suspend fun delete(filme: Filme) {
        filmes.removeIf { it.id == filme.id }
        filmesFlow.value = filmes.toList()
    }
}