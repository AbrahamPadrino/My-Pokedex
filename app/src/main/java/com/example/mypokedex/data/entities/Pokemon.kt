package com.example.mypokedex.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val id: Long, val name: String, val hp: Int, val attack: Int, val defense: Int,
    val speed: Int, val type: PokemonType, val imageUrl: String, val soundId: Int
) :
    Parcelable {

    enum class PokemonType {
        GRASS, FIRE, WATER, FIGHTER, ELECTRIC
    }
}