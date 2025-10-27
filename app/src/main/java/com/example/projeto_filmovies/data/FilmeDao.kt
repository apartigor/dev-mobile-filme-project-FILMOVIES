package com.example.projeto_filmovies.data

import kotlinx.coroutines.flow.Flow

interface FilmeDao {
    fun getFilmes(): Flow<List<Filme>>
    suspend fun insert(filme: Filme)
    suspend fun update(filme: Filme)
    suspend fun delete(filme: Filme)
}