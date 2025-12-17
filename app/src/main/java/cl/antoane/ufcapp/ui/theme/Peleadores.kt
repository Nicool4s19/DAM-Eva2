package cl.antoane.ufcapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import cl.antoane.ufcapp.viewmodel.PeleadoresViewModel
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Peleadores(
    navController: NavController, viewModel: PeleadoresViewModel
) {
    val peleadores by viewModel.top5Peleadores.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { navController.navigate("menu") },
                modifier = Modifier.padding(end = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Volver", color = Color.White)
            }

            Text(
                text = "Top 5 Peleadores",
                color = Color.Red,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
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
                            Text(
                                text = peleador.nombre,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text("Victorias: ${peleador.victorias}")
                            Text("Derrotas: ${peleador.derrotas}")
                            Text("Empates: ${peleador.empates}")
                            Text("División: ${peleador.division}")
                            Text("Ranking: ${peleador.ranking}")
                        }
                    }
                }
            }
        }
    }
}
