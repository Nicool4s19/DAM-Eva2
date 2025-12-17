package cl.antoane.ufcapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PeleadorDao {
    @Query("SELECT * FROM peleadores ORDER BY ranking ASC LIMIT 5")
    fun obtenerTop5(): Flow<List<PeleadorModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertarTodos(peleadores: List<PeleadorModel>)
}