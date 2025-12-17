package cl.antoane.ufcapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.antoane.ufcapp.model.AppDataBase
import cl.antoane.ufcapp.viewmodel.PeleadoresViewModel
import cl.antoane.ufcapp.viewmodel.PeleadoresViewModelFactory
import androidx.navigation.NavController

@Composable
fun PeleadoresScreenWrapper(navController: NavController) {

    val context = LocalContext.current
    val database = AppDataBase.getDatabase(context)

    val viewModel: PeleadoresViewModel = viewModel(
        factory = PeleadoresViewModelFactory(database.peleadorDao())
    )

    Peleadores(
        navController = navController,
        viewModel = viewModel
    )
}

