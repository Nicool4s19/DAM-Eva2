package cl.antoane.ufcapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navegacion(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") {
            PantallaInicio(navController = navController)
        }

        composable("login") {
            Login(navController = navController)
        }

        composable("formulario") {
            // ✅ Formulario YA NO recibe ViewModel por parámetro
            Formulario(navController = navController)
        }

        composable("menu") {
            Menu(navController = navController)
        }

        composable("peleadores") {
            //  PeleadoresViewModel se inyecta desde MainActivity (con Factory)
            // Aquí solo llamamos a la pantalla
            PeleadoresScreenWrapper(navController = navController)
        }

        composable("gps") {
            Gps(navController = navController)
        }
    }
}
