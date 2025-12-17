package cl.antoane.ufcapp.api

import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApi {
    @POST("usuarios/registro")
    suspend fun registro(@Body req: RegistroRequest): AuthResponse

    @POST("usuarios/login")
    suspend fun login(@Body req: LoginRequest): AuthResponse
}
