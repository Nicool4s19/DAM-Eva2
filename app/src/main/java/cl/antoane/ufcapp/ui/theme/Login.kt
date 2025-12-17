package cl.antoane.ufcapp.ui.theme

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.antoane.ufcapp.R
import cl.antoane.ufcapp.viewmodel.LoginViewModel

@Composable
fun Login(navController: NavController) {

    val vm: LoginViewModel = viewModel()

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val correoValido = remember(correo) {
        correo.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

    val contrasenaValida = remember(contrasena) {
        contrasena.length >= 6
    }

    val loginHabilitado = correoValido && contrasenaValida && !vm.cargando

    LaunchedEffect(vm.loginOk) {
        if (vm.loginOk) {
            vm.limpiarEstado()
            navController.navigate("menu") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = "Fondo Login",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.45f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Iniciar Sesión",
                fontSize = 28.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                isError = correo.isNotBlank() && !correoValido,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                isError = contrasena.isNotBlank() && !contrasenaValida,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { vm.login(correo, contrasena) },
                enabled = loginHabilitado,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (vm.cargando) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text("Ingresar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navController.navigate("formulario") }) {
                Text("¿No tienes cuenta? Regístrate", color = Color.White)
            }
        }

        vm.errorMsg?.let { msg ->
            AlertDialog(
                onDismissRequest = { vm.limpiarEstado() },
                title = { Text("Error") },
                text = { Text(msg) },
                confirmButton = {
                    Button(onClick = { vm.limpiarEstado() }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
