package ru.suleymanovtat.list.animals.repository.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.suleymanovtat.list.animals.model.WrapperAnimals

interface ApiService {

    @GET("xim/api.php")
    suspend fun getAnimails(@Query("query") animail: String): WrapperAnimals
}