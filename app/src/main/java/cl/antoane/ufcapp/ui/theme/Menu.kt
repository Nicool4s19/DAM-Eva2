package cl.antoane.ufcapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.antoane.ufcapp.R



@Composable
fun Menu(navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.ufcfondo)
    val DarkRed = Color(0xFF8B0000)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.9f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {
                Text(
                    text = "UFC APP",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        letterSpacing = 2.sp,
                        shadow = Shadow(Color.Black.copy(alpha = 0.7f), blurRadius = 8f)
                    ),
                    modifier = Modifier.offset(x = 2.dp, y = 2.dp),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "UFC APP",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        brush = Brush.linearGradient(listOf(Color.Red, DarkRed)),
                        letterSpacing = 2.sp,
                        shadow = Shadow(Color.DarkGray, blurRadius = 4f)
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            val buttonModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)

            Button(
                onClick = { navController.navigate("peleadores") },
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.4f))
            ) {
                Text("Ver Top 5 Peleadores", color = Color.White, fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate("mapa") },
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.4f))
            ) {
                Text("Ver Mapa y Eventos Cerca", color = Color.White, fontSize = 18.sp)
            }

            Button(
                onClick = {},
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.4f))
            ) {
                Text("Notificaciones", color = Color.White, fontSize = 18.sp)
            }

            Button(
                onClick = {
                    navController.navigate("inicio") {
                        popUpTo("menu") { inclusive = true }
                    }
                },
                modifier = buttonModifier,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.4f))
            ) {
                Text("Cerrar sesión", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
