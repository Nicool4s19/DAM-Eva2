package cl.antoane.ufcapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cl.antoane.ufcapp.model.PeleadorDao
import cl.antoane.ufcapp.model.PeleadorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PeleadoresViewModel(private val dao: PeleadorDao) : ViewModel() {

    val top5Peleadores: StateFlow<List<PeleadorModel>> = dao.obtenerTop5()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val peleadoresIniciales = listOf(
            PeleadorModel(1, "Khabib Nurmagomedov", 29, 0, 0, "Peso Ligero", "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/2611557.png&w=350&h=254", 1),
        PeleadorModel(2, "Khalil Rountree", 14, 5, 0, "Peso Semipesado", "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/4028627.png&w=350&h=254", 2),
        PeleadorModel(3, "Ignacio Bahamondes", 15, 5, 0, "Peso Welter", "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/4038116.png&w=350&h=254", 3),
        PeleadorModel(4, "Demetrious Johnson", 30, 5, 1, "Peso Mosca", "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/2512089.png&w=350&h=254", 4),
        PeleadorModel(5, "Ilia Topuria", 14, 0, 0, "Peso Ligero", "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/4350812.png&w=350&h=254", 5)
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertarTodos(peleadoresIniciales)
        }
    }
    class Factory(private val dao: PeleadorDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PeleadoresViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PeleadoresViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}




