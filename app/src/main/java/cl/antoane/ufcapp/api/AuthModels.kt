package cl.antoane.ufcapp.api


data class RegistroRequest(val correo: String, val contrasena: String)
data class LoginRequest(val correo: String, val contrasena: String)
data class AuthResponse(val id: Long, val correo: String)
