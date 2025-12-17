package cl.antoane.ufcapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.antoane.ufcapp.api.ApiClient
import cl.antoane.ufcapp.api.LoginRequest
import cl.antoane.ufcapp.api.UsuarioApi
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val api = ApiClient.retrofit.create(UsuarioApi::class.java)

    var cargando by mutableStateOf(false)
        private set

    var errorMsg by mutableStateOf<String?>(null)
        private set

    var loginOk by mutableStateOf(false)
        private set

    fun login(correo: String, contrasena: String) {
        errorMsg = null
        cargando = true

        viewModelScope.launch {
            try {
                api.login(LoginRequest(correo, contrasena))
                loginOk = true
            } catch (e: Exception) {
                loginOk = false
                errorMsg = "No se pudo iniciar sesión. Revisa credenciales o servidor."
            } finally {
                cargando = false
            }
        }
    }

    fun limpiarEstado() {
        errorMsg = null
        loginOk = false
    }
}
