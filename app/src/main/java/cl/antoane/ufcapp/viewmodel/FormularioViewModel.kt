package cl.antoane.ufcapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import cl.antoane.ufcapp.model.FormularioModel
import cl.antoane.ufcapp.model.Usuario
import cl.antoane.ufcapp.model.UsuarioDao

class FormularioViewModel : ViewModel() {

    var formulario = FormularioModel()

    fun verificarNombre() = formulario.nombre.isNotBlank()

    fun verificarCorreo() =
        formulario.correo.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(formulario.correo).matches()

    fun verificarEdad() =
        formulario.edad.toIntOrNull()?.let { it in 18..100 } ?: false

    fun verificarContrasena() = formulario.contrasena.length >= 6

    fun verificarFormulario() =
        verificarNombre() &&
                verificarCorreo() &&
                verificarEdad() &&
                verificarContrasena() &&
                formulario.terminos

    suspend fun correoExiste(dao: UsuarioDao): Boolean {
        val usuario = dao.obtenerUsuario(formulario.correo)
        return usuario != null
    }

    suspend fun registrarUsuario(dao: UsuarioDao): Boolean {

        if (correoExiste(dao)) {
            return false
        }

        val usuario = Usuario(
            correo = formulario.correo,
            contrasena = formulario.contrasena
        )

        dao.insertarUsuario(usuario)

        limpiarFormulario()

        return true
    }

    fun limpiarFormulario() {
        formulario.nombre = ""
        formulario.correo = ""
        formulario.edad = ""
        formulario.contrasena = ""
        formulario.terminos = false
    }
}
