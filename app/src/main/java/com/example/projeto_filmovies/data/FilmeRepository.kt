package com.example.projeto_filmovies.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FilmeRepository(private val filmeDao: FilmeDao) {

    fun getFilmes(): Flow<List<Filme>> = filmeDao.getFilmes()

    suspend fun insert(filme: Filme) {
        filmeDao.insert(filme)
    }

    suspend fun update(filme: Filme) {
        filmeDao.update(filme)
    }

    suspend fun delete(filme: Filme) {
        filmeDao.delete(filme)
    }
}