package cl.antoane.ufcapp.ui.theme.screens

import cl.antoane.ufcapp.ui.theme.components.CartaPeleador
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import cl.antoane.ufcapp.viewmodel.PeleadorViewModel
import cl.antoane.ufcapp.model.Peleador

@Composable
fun PeleadorListScreen() {
    val viewModel: PeleadorViewModel = viewModel()
    val peleadores = viewModel.obtenerPeleadores()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(peleadores) { peleador ->
            CartaPeleador(peleador)
        }
    }
}

@Composable
fun CartaPeleador(peleador: Peleador) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(peleador.imagen),
                contentDescription = peleador.nombre,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = peleador.nombre)
                Text(text = "Apodo: ${peleador.apodo}")
                Text(text = "País: ${peleador.pais}")
                Text(text = "División: ${peleador.division}")
                Text(text = "Récord: ${peleador.record}")
            }
        }
    }
}