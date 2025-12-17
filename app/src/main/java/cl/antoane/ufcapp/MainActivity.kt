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
                            Formulario(navController = navController)
                        }

                        composable("menu") {
                            Menu(navController = navController)
                        }

                        composable("peleadores") {
                            Peleadores(
                                navController = navController,
                                viewModel = peleadoresViewModel
                            )
                        }

                        composable("gps") {
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
    val fusedClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var permissionGranted by remember { mutableStateOf(false) }
    var lastLatLng by remember { mutableStateOf<LatLng?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        permissionGranted =
            results[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    results[Manifest.permission.ACCESS_COARSE_LOCATION] == true
    }

    LaunchedEffect(Unit) {
        val fineOk = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseOk = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
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
                    loc?.let {
                        lastLatLng = LatLng(it.latitude, it.longitude)
                    }
                }
        }
    }

    val fallback = LatLng(-33.49936500787212, -70.61654033901539)
    val startTarget = lastLatLng ?: fallback

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(startTarget, 15f)
    }

    LaunchedEffect(lastLatLng) {
        lastLatLng?.let {
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(it, 16f)
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            isMyLocationEnabled = permissionGranted
        ),
        uiSettings = MapUiSettings(
            myLocationButtonEnabled = true,
            zoomControlsEnabled = true
        )
    ) {

        Marker(
            state = MarkerState(
                position = LatLng(-33.497672632070476, -70.6126025410391)
            ),
            title = "Duoc UC VS Católica UFC"
        )

        Marker(
            state = MarkerState(
                position = LatLng(-33.50104607891704, -70.61707122623334)
            ),
            title = "Ignacio S vs Juan P"
        )

        Marker(
            state = MarkerState(
                position = LatLng(-33.49774554586376, -70.6178305190539)
            ),
            title = "Diego L vs Andres M"
        )

        lastLatLng?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Mi ubicación"
            )
        }
    }
}
