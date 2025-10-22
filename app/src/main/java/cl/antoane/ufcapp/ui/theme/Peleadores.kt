package cl.antoane.ufcapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import cl.antoane.ufcapp.viewmodel.PeleadoresViewModel

@Composable
fun Peleadores(
    navController: NavController, viewModel: PeleadoresViewModel) {
    val peleadores by viewModel.top5Peleadores.collectAsState(initial = emptyList())


        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(peleadores, key = { it.id }) { peleador ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(peleador.imagenUrl),
                            contentDescription = "Imagen de ${peleador.nombre}",
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = peleador.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(text = "Victorias: ${peleador.victorias}")
                            Text(text = "Derrotas: ${peleador.derrotas}")
                            Text(text = "Empates: ${peleador.empates}")
                            Text(text = "División: ${peleador.division}")
                            Text(text = "Ranking: ${peleador.ranking}")
                        }
                    }
                }
            }
        }
    }


