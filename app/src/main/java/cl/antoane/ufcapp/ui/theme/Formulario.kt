package cl.antoane.ufcapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.antoane.ufcapp.viewmodel.FormularioViewModel
import kotlinx.coroutines.delay
import cl.antoane.ufcapp.R

@Composable
fun Formulario(
    viewModel: FormularioViewModel,
    navController: NavController
) {
    var abrirModal by remember { mutableStateOf(false) }
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
                text = "UFC APP",
                color = Color.Red,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            val selectionColors = LocalTextSelectionColors.current

            OutlinedTextField(
                value = viewModel.formulario.nombre,
                onValueChange = { viewModel.formulario.nombre = it },
                label = { Text("Ingresa nombre", color = Color.Black) },
                isError = !viewModel.verificarNombre(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    errorTextColor = Color.Red,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.formulario.correo,
                onValueChange = { viewModel.formulario.correo = it },
                label = { Text("Ingresa correo", color = Color.Black) },
                isError = !viewModel.verificarCorreo(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    errorTextColor = Color.Red,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.formulario.edad,
                onValueChange = { viewModel.formulario.edad = it },
                label = { Text("Ingresa edad", color = Color.Black) },
                isError = !viewModel.verificarEdad(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    errorTextColor = Color.Red,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.formulario.contrasena,
                onValueChange = { viewModel.formulario.contrasena = it },
                label = { Text("Ingresa contraseña", color = Color.Black) },
                isError = !viewModel.verificarContrasena(),
                visualTransformation = PasswordVisualTransformation(), // Oculta los caracteres
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    errorTextColor = Color.Red,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = viewModel.formulario.terminos,
                    onCheckedChange = { viewModel.formulario.terminos = it }
                )
                Text("Acepta los términos y condiciones", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                enabled = viewModel.verificarFormulario() && !cargando,
                onClick = {
                    if (viewModel.verificarFormulario()) {
                        abrirModal = true
                    }
                }
            ) {
                Text("Ingresar")
            }
        }

        if (abrirModal) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Registro exitoso") },
                text = { Text("Registrado, Bienvenido a UfcApp") },
                confirmButton = {
                    Button(
                        onClick = {
                            abrirModal = false
                            cargando = true
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        if (cargando) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Red)
            }

            LaunchedEffect(Unit) {
                delay(2000)
                cargando = false
                navController.navigate("menu") {
                    popUpTo("formulario") { inclusive = true }
                }
            }
        }
    }
}
