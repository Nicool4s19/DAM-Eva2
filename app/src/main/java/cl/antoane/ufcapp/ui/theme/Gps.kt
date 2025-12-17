package cl.antoane.ufcapp.ui.theme

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.antoane.ufcapp.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun Gps(navController: NavController) {
    val context = LocalContext.current
    var location by remember { mutableStateOf<Location?>(null) }
    var permisoConcedido by remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        Log.d("Gps", "Permiso concedido: $granted")
        permisoConcedido = granted
    }

    LaunchedEffect(permisoConcedido) {
        if (permisoConcedido) {
            try {
                obtenerUbicacion(context) { loc ->
                    location = loc
                }
            } catch (se: SecurityException) {
                Log.e("Gps", "Error permiso ubicación", se)
                permisoConcedido = false
                location = null
            }
        } else {
            location = null
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = if (location != null)
            CameraPosition.fromLatLngZoom(LatLng(location!!.latitude, location!!.longitude), 17f)
        else
            CameraPosition.fromLatLngZoom(LatLng(-33.499365, -70.616540), 13f)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ufcfondoubi),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.7f)),
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Permitir ubicación", color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (permisoConcedido) {
                if (location != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState,
                            properties = MapProperties(isMyLocationEnabled = permisoConcedido),
                            uiSettings = MapUiSettings(myLocationButtonEnabled = true),
                        ) {
                            Marker(
                                state = MarkerState(LatLng(location!!.latitude, location!!.longitude)),
                                title = "Tu ubicación"
                            )
                        }
                    }
                } else {
                    Text(
                        text = "Presiona para obtener tu ubicación",
                        color = Color.White
                    )
                }
            } else {
                Text(
                    text = "Permiso de ubicación no concedido",
                    color = Color.White
                )
            }
        }
    }
}

@SuppressLint("MissingPermission")
fun obtenerUbicacion(context: Context, onLocation: (Location?) -> Unit) {
    Log.d("Gps", "Solicitando actualización de ubicación")
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
    fusedLocationClient.requestLocationUpdates(
        locationRequest,
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                Log.d("Gps", "Ubicación recibida: ${result.lastLocation}")
                onLocation(result.lastLocation)
                fusedLocationClient.removeLocationUpdates(this)
            }
        },
        Looper.getMainLooper()
    )
}

