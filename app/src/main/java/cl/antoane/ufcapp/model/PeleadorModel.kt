package cl.antoane.ufcapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peleadores")
data class PeleadorModel(
    @PrimaryKey val id: Int,
    val nombre: String,
    val victorias: Int,
    val derrotas: Int,
    val empates: Int,
    val division: String,
    val imagenUrl: String,
    val ranking: Int
)



