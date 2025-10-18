package cl.antoane.ufcapp.repository

import cl.antoane.ufcapp.model.Peleador

class PeleadorRepository {

    fun getPeleador(): List<Peleador>{
        return listOf(
            Peleador(
                "Demetrious Johnson",
                "Mighty Mouse",
                "USA",
                "25-4-1",
                "Peso mosca",
                "https://cdn.ufc.com/fighter-images/Demetrious-Johnson.png"
            ),
            Peleador(
                "Khalil Rountree",
                "The War Horse",
                "USA",
                "13-5-0",
                "Peso Semicompleto",
                "https://cdn.ufc.com/fighter-images/Khalil-Rountree.png"
            ),
            Peleador(
                "Islam Makhachev",
                "———————————",
                "Rusia",
                "25-1-0",
                "Peso Ligero",
                "https://cdn.ufc.com/fighter-images/Islam-Makhachev.png"
            ),
            Peleador(
                "Alex Pereira",
                "Poatan",
                "Brasil",
                "10-2-0",
                "Peso Semicompleto",
                "https://cdn.ufc.com/fighter-images/Alex-Pereira.png"
            )
        )
    }
}