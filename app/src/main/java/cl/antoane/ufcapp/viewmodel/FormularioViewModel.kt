package cl.antoane.ufcapp.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import cl.antoane.ufcapp.model.FormularioModel

class FormularioViewModel : ViewModel() {
    var formulario = FormularioModel()
    fun verificarNombre() = formulario.nombre.isNotBlank()
    fun verificarCorreo() = Patterns.EMAIL_ADDRESS.matcher(formulario.correo).matches()
    fun verificarEdad() = formulario.edad.toIntOrNull()?.let { it in 18..100 } ?: false
    fun verificarContrasena() = formulario.contrasena.length >= 6

    fun verificarFormulario() = verificarNombre() &&
                                verificarCorreo() &&
                                verificarEdad() &&
                                verificarContrasena() &&
                                formulario.terminos
}
