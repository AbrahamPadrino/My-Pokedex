package com.example.mypokedex.data.remote

import com.example.mypokedex.data.entities.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon")
    suspend fun getPokemonList(

    ): Response<PokemonResponse>
}