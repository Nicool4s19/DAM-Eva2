package cl.antoane.ufcapp.api

import cl.antoane.ufcapp.model.PeleadorModel
import retrofit2.http.GET

interface PeleadorApi {

    @GET("peleadores/top5")
    suspend fun obtenerTop5(): List<PeleadorModel>
}
