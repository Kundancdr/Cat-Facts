package com.example.catfacts.data

import com.example.catfacts.models.catfacts
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("fact")
    suspend fun getRandomFact() : Response<catfacts>
}