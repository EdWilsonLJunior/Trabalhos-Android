package com.example.trabalho1.src.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabalho1.src.enums.Cargo
import com.example.trabalho1.src.modelos.Profissional

private fun cargoIcone(cargo: Cargo): ImageVector = when (cargo) {
    Cargo.ADMINISTRATIVO -> Icons.Filled.AdminPanelSettings
    Cargo.FINANCEIRO     -> Icons.Filled.Payments
    Cargo.GERENCIA       -> Icons.Filled.ManageAccounts
    Cargo.SUPORTE        -> Icons.Filled.SupportAgent
    Cargo.SELECIONE      -> Icons.Filled.HelpOutline
}

private fun cargoCorFundo(cargo: Cargo): Color = when (cargo) {
    Cargo.ADMINISTRATIVO -> Color(0xFFE3F2FD)
    Cargo.FINANCEIRO     -> Color(0xFFE8F5E9)
    Cargo.GERENCIA       -> Color(0xFFF3E5F5)
    Cargo.SUPORTE        -> Color(0xFFFFF3E0)
    Cargo.SELECIONE      -> Color(0xFFF5F5F5)
}

private fun cargoCorAcento(cargo: Cargo): Color = when (cargo) {
    Cargo.ADMINISTRATIVO -> Color(0xFF1565C0)
    Cargo.FINANCEIRO     -> Color(0xFF2E7D32)
    Cargo.GERENCIA       -> Color(0xFF6A1B9A)
    Cargo.SUPORTE        -> Color(0xFFE65100)
    Cargo.SELECIONE      -> Color(0xFF757575)
}

@Composable
fun CardProfissional(
    profissionais: List<Profissional>,
    acaoRemover: (Profissional) -> Unit,
    acaoEditar: (Profissional) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(profissionais) { profissional ->
            val corFundo  = cargoCorFundo(profissional.cargo)
            val corAcento = cargoCorAcento(profissional.cargo)
            val icone     = cargoIcone(profissional.cargo)

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = corFundo),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(corAcento, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icone,
                            contentDescription = profissional.cargo.name,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = profissional.nome,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = corAcento
                        )
                        Text(
                            text = profissional.email,
                            fontSize = 13.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = profissional.cargo.name,
                            fontSize = 12.sp,
                            color = corAcento.copy(alpha = 0.8f)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Botao(texto = "Remover", color = Color(0xFFD32F2F)) {
                        acaoRemover(profissional)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Botao(texto = "Editar", color = corAcento) {
                        acaoEditar(profissional)
                    }
                }
            }
        }
    }
}