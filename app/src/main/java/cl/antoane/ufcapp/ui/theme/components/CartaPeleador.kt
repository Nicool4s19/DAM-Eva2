package cl.antoane.ufcapp.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cl.antoane.ufcapp.model.Peleador
import coil.compose.rememberAsyncImagePainter

@Composable
fun CartaPeleador(peleador: Peleador) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(peleador.imagen),
                contentDescription = peleador.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = peleador.nombre)
            Text(text = "Apodo: ${peleador.apodo}")
            Text(text = "País: ${peleador.pais}")
            Text(text = "Record: ${peleador.record}")
            Text(text = "Division: ${peleador.division}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
