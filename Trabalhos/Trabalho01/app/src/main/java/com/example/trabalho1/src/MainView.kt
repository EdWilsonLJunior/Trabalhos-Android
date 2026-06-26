package com.example.trabalho1.src

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.trabalho1.src.componentes.CardProfissional
import com.example.trabalho1.src.componentes.Formulario
import com.example.trabalho1.src.modelos.Profissional

@Composable
fun MainView() {

    var profissionais = remember { mutableStateListOf<Profissional>() }
    var profissionalEditando by remember { mutableStateOf<Profissional?>(null) }

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
    ) {
        Formulario(
            profissional = profissionalEditando,
            acaoCadastro = { novoProfissional ->
                val profissionalOriginal = profissionalEditando

                if (profissionalOriginal == null) {
                    profissionais.add(novoProfissional)
                } else {
                    val indice = profissionais.indexOf(profissionalOriginal)
                    if (indice >= 0) {
                        profissionais[indice] = novoProfissional
                    }
                }

                profissionalEditando = null
            }
        )

        CardProfissional(
            profissionais = profissionais,
            acaoRemover = { profissionalRemovido -> profissionais.remove(profissionalRemovido) },
            acaoEditar = { profissional -> profissionalEditando = profissional },
        )
    }
}