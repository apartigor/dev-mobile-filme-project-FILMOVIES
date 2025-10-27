package com.example.projeto_filmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.projeto_filmovies.data.Filme
import com.example.projeto_filmovies.ui.theme.FilmoviesTheme
import com.example.projeto_filmovies.view.AddEditFilmeView
import com.example.projeto_filmovies.view.ListaFilmesView
import com.example.projeto_filmovies.viewmodel.FilmeViewModel

class MainActivity : ComponentActivity() {

    private val filmeViewModel: FilmeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmoviesTheme {
                FilmoviesApp(filmeViewModel = filmeViewModel)
            }
        }
    }
}

@Composable
fun FilmoviesApp(filmeViewModel: FilmeViewModel) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Lista) }

    when (val screen = currentScreen) {
        is Screen.Lista -> ListaFilmesView(
            filmeViewModel = filmeViewModel,
            onAddClick = { currentScreen = Screen.AddEdit(null) },
            onEditClick = { currentScreen = Screen.AddEdit(it) }
        )
        is Screen.AddEdit -> AddEditFilmeView(
            filme = screen.filme,
            onSave = { titulo, ano ->
                if (screen.filme == null) {
                    filmeViewModel.salvarFilme(titulo, ano)
                } else {
                    val anoInt = ano.toIntOrNull() ?: 0
                    filmeViewModel.atualizarFilme(screen.filme.copy(titulo = titulo, ano = anoInt))
                }
                currentScreen = Screen.Lista
            },
            onCancel = { currentScreen = Screen.Lista }
        )
    }
}

sealed class Screen {
    object Lista : Screen()
    data class AddEdit(val filme: Filme?) : Screen()
}