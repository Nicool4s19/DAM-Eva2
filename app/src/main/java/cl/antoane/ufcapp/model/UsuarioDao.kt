package cl.antoane.ufcapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM Usuario WHERE correo = :correo LIMIT 1")
    suspend fun obtenerUsuario(correo: String): Usuario?
}
    