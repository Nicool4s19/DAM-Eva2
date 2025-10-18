package cl.antoane.ufcapp.viewmodel


import androidx.lifecycle.ViewModel
import cl.antoane.ufcapp.model.Peleador

class PeleadorViewModel : ViewModel() {

    private val peleadores = listOf(
        Peleador(
            "Demetrious Johnson",
            "Mighty Mouse",
            "Estados Unidos",
            "Peso Mosca",
            "25-4-1",
            "https://cdn.ufc.com/fighter-images/Demetrious-Johnson.png"
        ),
        Peleador(
            "Khalil Rountree",
            "The War Horse",
            "Estados Unidos",
            "Peso Semicompleto",
            "13-5-0",
            "https://cdn.ufc.com/fighter-images/Khalil-Rountree.png"
        ),
        Peleador(
            "Islam Makhachev",
            "———————————",
            "Rusia",
            "Peso Ligero",
            "25-1-0",
            "https://cdn.ufc.com/fighter-images/Islam-Makhachev.png"
        ),
        Peleador(
            "Alex Pereira",
            "Poatan",
            "Brasil",
            "Peso Semicompleto",
            "10-2-0",
            "https://cdn.ufc.com/fighter-images/Alex-Pereira.png"
        )
    )

    fun obtenerPeleadores(): List<Peleador> {
        return peleadores
    }
}