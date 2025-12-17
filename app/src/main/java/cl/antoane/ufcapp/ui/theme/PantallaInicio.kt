package cl.antoane.ufcapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.antoane.ufcapp.R

    @Composable
    fun PantallaInicio(navController: NavController) {

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.fondoblancoufc),
                contentDescription = "Fondo inicio",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Iniciar Sesión", color = Color.White)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("formulario") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Registrarse", color = Color.White)
                }
            }
        }

    }




