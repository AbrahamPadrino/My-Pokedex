package com.example.mypokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ListFragment.PokemonSelectectListener {
    //1.0 pasar datos de activity al fragment
    private lateinit var detailFragment: DetailFragment
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1.1 pasa datos de activity al fragment
        detailFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as DetailFragment
        //
    }

    override fun onPokemonSelected(pokemon: Pokemon) {
        //1.2 pasa datos de activity al fragment
        detailFragment.setPokemon(pokemon)
        //
    }
}