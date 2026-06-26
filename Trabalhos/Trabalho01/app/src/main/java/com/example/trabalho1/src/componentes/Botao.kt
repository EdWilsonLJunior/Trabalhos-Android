package com.example.trabalho1.src.componentes

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.trabalho1.src.theme.Purple40

@Composable

fun Botao(texto:String, color: Color ,acao: () -> Unit) {
    Button(
        onClick = acao,
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) { Text(texto) }
}