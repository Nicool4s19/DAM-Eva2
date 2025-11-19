package cl.antoane.ufcapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.antoane.ufcapp.viewmodel.FormularioViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import cl.antoane.ufcapp.R
import cl.antoane.ufcapp.model.AppDataBase

@Composable
fun Formulario(
    viewModel: FormularioViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val usuarioDao = AppDataBase.getDatabase(context).usuarioDao()
    val scope = rememberCoroutineScope()

    var abrirModal by remember { mutableStateOf(false) }
    var modalError by remember { mutableStateOf(false) }
    var cargando by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.ufc),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            alpha = 0.3f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "UFC APP - Registro",
                color = Color.Red,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = viewModel.formulario.nombre,
                onValueChange = { viewModel.formulario.nombre = it },
                label = { Text("Ingresa nombre", color = Color.Black) },
                isError = !viewModel.verificarNombre()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.formulario.correo,
                onValueChange = { viewModel.formulario.correo = it },
                label = { Text("Ingresa correo", color = Color.Black) },
                isError = !viewModel.verificarCorreo()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.formulario.edad,
                onValueChange = { viewModel.formulario.edad = it },
                label = { Text("Ingresa edad", color = Color.Black) },
                isError = !viewModel.verificarEdad()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.formulario.contrasena,
                onValueChange = { viewModel.formulario.contrasena = it },
                label = { Text("Ingresa contraseña", color = Color.Black) },
                visualTransformation = PasswordVisualTransformation(),
                isError = !viewModel.verificarContrasena()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = viewModel.formulario.terminos,
                    onCheckedChange = { viewModel.formulario.terminos = it }
                )
                Text(text = "Acepta los términos y condiciones", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                enabled = viewModel.verificarFormulario() && !cargando,
                onClick = {
                    scope.launch {
                        val ex = viewModel.correoExiste(usuarioDao)

                        if (ex) {
                            modalError = true
                        } else {
                            val ok = viewModel.registrarUsuario(usuarioDao)

                            if (ok) abrirModal = true
                        }
                    }
                }
            ) {
                Text("Registrarse")
            }
        }

        if (abrirModal) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Registro exitoso") },
                text = { Text("Tu cuenta ha sido creada correctamente.") },
                confirmButton = {
                    Button(
                        onClick = {
                            abrirModal = false
                            cargando = true
                        }
                    ) {
                        Text("Continuar")
                    }
                }
            )
        }

        if (modalError) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Error") },
                text = { Text("El correo ya está registrado. Intente iniciar sesión.") },
                confirmButton = {
                    Button(onClick = { modalError = false }) {
                        Text("OK")
                    }
                }
            )
        }

        if (cargando) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Red)
            }

            LaunchedEffect(Unit) {
                delay(2000)
                cargando = false
                navController.navigate("login") {
                    popUpTo("formulario") { inclusive = true }
                }
            }
        }
    }
}
