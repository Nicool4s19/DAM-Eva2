package cl.antoane.ufcapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotificacionesViewModel : ViewModel() {

    var cargando by mutableStateOf(false)
        private set

    var respuestaApi by mutableStateOf<String?>(null)
        private set

    var errorMsg by mutableStateOf<String?>(null)
        private set


    fun obtenerNotificacion() {
        cargando = true
        errorMsg = null

        viewModelScope.launch {
            try {
                delay(1000)

                respuestaApi =
                    "🔔 Notificación\n\n" +
                            "Tienes nuevas actualizaciones disponibles.\n" +
                            "Revisa las promociones activas."

            } catch (e: Exception) {
                errorMsg = "Error al obtener la notificación"
            } finally {
                cargando = false
            }
        }
    }

    fun limpiar() {
        respuestaApi = null
        errorMsg = null
    }
}
