package com.example.mypokedex.domain

import com.example.mypokedex.data.entities.Pokemon
import com.example.mypokedex.data.entities.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemonList(): List <Pokemon>
    suspend fun getPokemonListFromApi(): PokemonResponse
}