package cl.antoane.ufcapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.antoane.ufcapp.model.PeleadorDao

class PeleadoresViewModelFactory(private val dao: PeleadorDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeleadoresViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PeleadoresViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
