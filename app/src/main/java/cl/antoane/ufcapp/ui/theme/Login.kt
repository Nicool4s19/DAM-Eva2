package cl.antoane.ufcapp.ui.theme

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import cl.antoane.ufcapp.model.AppDataBase
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavController) {

    val context = LocalContext.current
    val usuarioDao = AppDataBase.getDatabase(context).usuarioDao()
    val scope = rememberCoroutineScope()

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    var errorMsg by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }

    val correoValido = remember(correo) {
        correo.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

    val contrasenaValida = remember(contrasena) {
        contrasena.isNotBlank() && contrasena.length >= 6
    }

    val loginHabilitado = correoValido && contrasenaValida

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Iniciar Sesión", fontSize = 24.sp, color = Color.Red)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            isError = !correoValido && correo.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = !contrasenaValida && contrasena.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                scope.launch {
                    val usuario = usuarioDao.obtenerUsuario(correo)

                    if (usuario == null) {
                        errorMsg = "El correo no está registrado."
                        mostrarError = true
                    } else if (usuario.contrasena != contrasena) {
                        errorMsg = "Contraseña incorrecta."
                        mostrarError = true
                    } else {
                        navController.navigate("menu") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
            },
            enabled = loginHabilitado,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (loginHabilitado) Color.Red else Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate("formulario") }) {
            Text("¿No tienes cuenta? Registrarse")
        }
    }

    if (mostrarError) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Error de inicio de sesión") },
            text = { Text(errorMsg) },
            confirmButton = {
                Button(onClick = { mostrarError = false }) {
                    Text("OK")
                }
            }
        )
    }
}
