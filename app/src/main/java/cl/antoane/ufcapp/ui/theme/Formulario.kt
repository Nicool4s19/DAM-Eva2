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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.antoane.ufcapp.viewmodel.RegistroViewModel

@Composable
fun Formulario(navController: NavController) {

    val registroVm: RegistroViewModel = viewModel()

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var repetirContrasena by remember { mutableStateOf("") }

    val correoValido = remember(correo) {
        correo.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

    val contrasenaValida = remember(contrasena) {
        contrasena.length >= 6
    }

    val contrasenasIguales = remember(contrasena, repetirContrasena) {
        contrasena == repetirContrasena && repetirContrasena.isNotBlank()
    }

    val formularioValido =
        correoValido && contrasenaValida && contrasenasIguales && !registroVm.cargando

    LaunchedEffect(registroVm.ok) {
        if (registroVm.ok) {
            registroVm.limpiarEstado()
            navController.navigate("menu") {
                popUpTo("formulario") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Registro de Usuario",
            fontSize = 24.sp,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            isError = correo.isNotBlank() && !correoValido,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = contrasena.isNotBlank() && !contrasenaValida,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = repetirContrasena,
            onValueChange = { repetirContrasena = it },
            label = { Text("Repetir contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = repetirContrasena.isNotBlank() && !contrasenasIguales,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                registroVm.registrar(
                    correo = correo,
                    contrasena = contrasena
                )
            },
            enabled = formularioValido,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (formularioValido) Color.Red else Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (registroVm.cargando) {
                CircularProgressIndicator(strokeWidth = 2.dp)
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text("Registrarse", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate("login") }) {
            Text("¿Ya tienes cuenta? Iniciar sesión")
        }
    }

    registroVm.errorMsg?.let { msg ->
        AlertDialog(
            onDismissRequest = { registroVm.limpiarEstado() },
            title = { Text("Error de registro") },
            text = { Text(msg) },
            confirmButton = {
                Button(onClick = { registroVm.limpiarEstado() }) {
                    Text("OK")
                }
            }
        )
    }
}
