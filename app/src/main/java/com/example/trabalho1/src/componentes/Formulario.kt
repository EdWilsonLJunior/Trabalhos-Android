package com.example.trabalho1.src.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.material3.MenuAnchorType
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.trabalho1.src.enums.Cargo
import com.example.trabalho1.src.modelos.Profissional
import com.example.trabalho1.src.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(profissional: Profissional?, acaoCadastro: (Profissional) -> Unit) {

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cargo by remember { mutableStateOf(Cargo.SELECIONE) }


    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(profissional) {
        if (profissional != null) {
            nome = profissional.nome
            email = profissional.email
            cargo = profissional.cargo
        } else {
            nome = ""
            email = ""
            cargo = Cargo.SELECIONE
        }
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = cargo.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Cargo") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Cargo.entries.forEach { opcao ->
                    DropdownMenuItem(
                        text = { Text(opcao.name) },
                        onClick = {
                            cargo = opcao
                            expanded = false
                        }
                    )
                }
            }
        }
        Botao(
            texto = if (profissional == null) "Adicionar" else "Alterar",
            color = Purple40,
        ) {
            acaoCadastro(Profissional(nome, email, cargo))

            nome = ""
            email = ""
            cargo = Cargo.SELECIONE

            focusManager.clearFocus()
        }
    }


}