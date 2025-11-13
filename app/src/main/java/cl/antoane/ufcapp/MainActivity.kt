package cl.antoane.ufcapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

import cl.antoane.ufcapp.model.AppDataBase
import cl.antoane.ufcapp.ui.theme.*
import cl.antoane.ufcapp.viewmodel.FormularioViewModel
import cl.antoane.ufcapp.viewmodel.PeleadoresViewModel
import cl.antoane.ufcapp.viewmodel.PeleadoresViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UfcApp2Theme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    val navController = rememberNavController()
                    val context = LocalContext.current

                    val database = remember { AppDataBase.getDatabase(context) }
                    val peleadoresViewModel: PeleadoresViewModel = viewModel(
                        factory = PeleadoresViewModelFactory(database.peleadorDao())
                    )
                    val formularioViewModel: FormularioViewModel = viewModel()

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
                            Formulario(viewModel = formularioViewModel, navController = navController)
                        }

                        composable("menu") {
                            Menu(navController = navController)
                        }

                        composable("peleadores") {
                            Peleadores(navController = navController, viewModel = peleadoresViewModel)
                        }

                        composable("mapa") {
                            MapWithGPS()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MapWithGPS() {
    val context = LocalContext.current
    val fusedClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var permissionGranted by remember { mutableStateOf(false) }
    var lastLatLng by remember { mutableStateOf<LatLng?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val fine = results[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarse = results[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        permissionGranted = fine || coarse
    }

    LaunchedEffect(Unit) {
        val fineOk = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarseOk = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (fineOk || coarseOk) {
            permissionGranted = true
        } else {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            fusedClient.lastLocation
                .addOnSuccessListener { loc ->
                    if (loc != null) {
                        lastLatLng = LatLng(loc.latitude, loc.longitude)
                    }
                }
                .addOnFailureListener {
                    lastLatLng = null
                }
        }
    }

    val fallback = LatLng(-33.49936500787212, -70.61654033901539)
    val startTarget = lastLatLng ?: fallback

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startTarget, 15f)
    }

    LaunchedEffect(lastLatLng) {
        lastLatLng?.let { target ->
            cameraPositionState.position = CameraPosition.fromLatLngZoom(target, 16f)
        }
    }

    val properties by remember(permissionGranted) {
        mutableStateOf(
            MapProperties(
                isMyLocationEnabled = permissionGranted
            )
        )
    }
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = true
            )
        )
    }

    val nombreLugar1 = "Duoc UC VS Catolica UFC"
    val lugar1 = LatLng(-33.497672632070476, -70.6126025410391)
    val nombreLugar2 = "Ignacio S (DuocUC) vs Juan P (Santo Tomas)"
    val lugar2 = LatLng(-33.50104607891704, -70.61707122623334)
    val nombreLugar3 = "Diego L (Duoc UC) VS Andres M (AIEP)"
    val lugar3 = LatLng(-33.49774554586376, -70.6178305190539)

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        Marker(state = MarkerState(position = lugar1), title = nombreLugar1)
        Marker(state = MarkerState(position = lugar2), title = nombreLugar2)
        Marker(state = MarkerState(position = lugar3), title = nombreLugar3)
        lastLatLng?.let { Marker(state = MarkerState(position = it), title = "Mi ubicación") }
    }
}
