package com.example.mypokedex

//import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity(), ListFragment.PokemonSelectectListener {
    //1.0 pasar datos de activity al fragment
    private lateinit var detailFragment: DetailFragment
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onPokemonSelected(pokemon: Pokemon) {
        findNavController(R.id.main_navigation_container)
            .navigate(ListFragmentDirections.actionListFragmentToPokemonDetailFragment(pokemon))

    }
}