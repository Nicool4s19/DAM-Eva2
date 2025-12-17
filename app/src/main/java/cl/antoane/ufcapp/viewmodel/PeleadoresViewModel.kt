package cl.antoane.ufcapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cl.antoane.ufcapp.api.ApiClient
import cl.antoane.ufcapp.api.PeleadorApi
import cl.antoane.ufcapp.model.PeleadorDao
import cl.antoane.ufcapp.model.PeleadorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class PeleadoresViewModel(private val dao: PeleadorDao) : ViewModel() {

    private val api = ApiClient.retrofit.create(PeleadorApi::class.java)

    val top5Peleadores: StateFlow<List<PeleadorModel>> = dao.obtenerTop5()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    init {
        Log.d("PeleadoresVM", "INIT ViewModel")   //log inmediato

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val top5 = api.obtenerTop5()
                Log.d("PeleadoresVM", "TOP5 API SIZE = ${top5.size}")

                dao.insertarTodos(top5)

                val enRoom = dao.obtenerTop5().first()
                Log.d("PeleadoresVM", "TOP5 ROOM SIZE = ${enRoom.size}")
            } catch (e: Exception) {
                Log.e("PeleadoresVM", "Error obteniendo top5", e)
            }
        }
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

