package com.example.projeto_filmovies.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.projeto_filmovies.data.Filme

@Composable
fun AddEditFilmeView(
    filme: Filme?,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var titulo by remember { mutableStateOf(filme?.titulo ?: "") }
    var ano by remember { mutableStateOf(filme?.ano?.toString() ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título do Filme") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = ano,
            onValueChange = { ano = it },
            label = { Text("Ano de Estreia") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        Button(
            onClick = {
                onSave(titulo, ano)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Salvar")
        }
        Button(
            onClick = onCancel,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Cancelar")
        }
    }
}