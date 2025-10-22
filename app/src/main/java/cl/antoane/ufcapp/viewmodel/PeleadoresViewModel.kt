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
            PeleadorModel(1, "Khabib Nurmagomedov", 29, 0, 0, "Peso Ligero", "https://example.com/khabib.jpg", 1),
        PeleadorModel(2, "Khalil Rountree", 14, 5, 0, "Peso Semipesado", "https://example.com/khalil.jpg", 2),
        PeleadorModel(3, "Ignacio Bahamondes", 15, 5, 0, "Peso Welter", "https://example.com/ignacio.jpg", 3),
        PeleadorModel(4, "Demetrious Johnson", 30, 5, 1, "Peso Mosca", "https://example.com/demetrious.jpg", 4),
        PeleadorModel(5, "Ilia Topuria", 14, 0, 0, "Peso Ligero", "https://example.com/ilia.jpg", 5)
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




