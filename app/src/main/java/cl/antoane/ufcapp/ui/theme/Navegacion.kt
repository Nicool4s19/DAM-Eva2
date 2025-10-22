package cl.antoane.ufcapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.antoane.ufcapp.viewmodel.FormularioViewModel
import cl.antoane.ufcapp.viewmodel.PeleadoresViewModel


@Composable
fun Navegacion(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "formulario") {
        composable("formulario") {
            val viewModel: FormularioViewModel = viewModel()
            Formulario(navController = navController, viewModel = viewModel)
        }
        composable("menu") {
            Menu(navController = navController)
        }
        composable("peleadores") {
            val viewModel: PeleadoresViewModel = viewModel()
            Peleadores(navController = navController, viewModel = viewModel)
        }
        composable("gps") {
            Gps(navController = navController)
        }
    }
}
