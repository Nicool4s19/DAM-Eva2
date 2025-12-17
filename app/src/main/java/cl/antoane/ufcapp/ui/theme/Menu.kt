package cl.antoane.ufcapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.antoane.ufcapp.R
import cl.antoane.ufcapp.viewmodel.NotificacionesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavController) {

    val notiVm: NotificacionesViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.ufcfondo),
            contentDescription = "Fondo Menú",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.45f))
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("UFC APP", color = Color.Red) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.Red
                    ),
                    actions = {
                        IconButton(onClick = { notiVm.obtenerNotificacion() }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notificaciones",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                // Botón: Ver Top 5 Peleadores
                Button(
                    onClick = { navController.navigate("peleadores") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray.copy(alpha = 0.6f),
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text("Ver Top 5 Peleadores")
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Botón: Ver Mapa y Eventos
                Button(
                    onClick = { navController.navigate("gps") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray.copy(alpha = 0.6f),
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text("Ver Mapa y Eventos Cerca")
                }

                Spacer(modifier = Modifier.height(12.dp))


                Spacer(modifier = Modifier.height(12.dp))

                // Botón: Cerrar sesión
                Button(
                    onClick = {
                        navController.navigate("inicio") {
                            popUpTo("menu") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray.copy(alpha = 0.6f),
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Cerrar sesión",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cerrar sesión")
                }
            }

            // Diálogos de notificaciones
            if (notiVm.cargando) {
                AlertDialog(
                    onDismissRequest = {},
                    title = { Text("Consultando servidor") },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("Obteniendo notificación...")
                        }
                    },
                    confirmButton = {}
                )
            }

            notiVm.respuestaApi?.let { msg ->
                AlertDialog(
                    onDismissRequest = { notiVm.limpiar() },
                    title = { Text("Notificación") },
                    text = { Text(msg) },
                    confirmButton = {
                        Button(onClick = { notiVm.limpiar() }) {
                            Text("OK")
                        }
                    }
                )
            }

            notiVm.errorMsg?.let { msg ->
                AlertDialog(
                    onDismissRequest = { notiVm.limpiar() },
                    title = { Text("Error") },
                    text = { Text(msg) },
                    confirmButton = {
                        Button(onClick = { notiVm.limpiar() }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}




