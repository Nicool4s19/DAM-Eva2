package cl.antoane.ufcapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.antoane.ufcapp.api.ApiClient
import cl.antoane.ufcapp.api.RegistroRequest
import cl.antoane.ufcapp.api.UsuarioApi
import kotlinx.coroutines.launch

class RegistroViewModel : ViewModel() {

    private val api = ApiClient.retrofit.create(UsuarioApi::class.java)

    var cargando by mutableStateOf(false)
        private set

    var ok by mutableStateOf(false)
        private set

    var errorMsg by mutableStateOf<String?>(null)
        private set

    fun registrar(correo: String, contrasena: String) {
        errorMsg = null
        cargando = true

        viewModelScope.launch {
            try {
                api.registro(RegistroRequest(correo, contrasena))
                ok = true
            } catch (e: Exception) {
                ok = false
                errorMsg = "No se pudo registrar. Puede que el correo ya exista o el servidor no esté arriba."
            } finally {
                cargando = false
            }
        }
    }

    fun limpiarEstado() {
        ok = false
        errorMsg = null
    }
}
