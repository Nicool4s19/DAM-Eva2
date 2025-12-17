package cl.antoane.ufcapp

import org.junit.Assert.*
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun correoVacio_noEsValido() {
        val correo = ""
        val esValido = correo.isNotBlank()
        assertFalse(esValido)
    }

    @Test
    fun contrasenaCorta_noEsValida() {
        val contrasena = "123"
        val esValida = contrasena.length >= 6
        assertFalse(esValida)
    }

    @Test
    fun contrasenaLarga_esValida() {
        val contrasena = "123456"
        val esValida = contrasena.length >= 6
        assertTrue(esValida)
    }
}
