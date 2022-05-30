package com.example.trainingmoonshotapp.app.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FossilApi {
    @GET("/nh/fossils/individuals")
    suspend fun getFossils(@Query("api_key") apiKey: String): List<FossilBE>

    @GET("/nh/fossils/individuals/{name}")
    suspend fun getFossil(@Path("name") name: String, @Query("api_key") apiKey: String): FossilBE
}

