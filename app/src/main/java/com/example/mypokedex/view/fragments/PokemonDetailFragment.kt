package com.example.mypokedex.view.fragments

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mypokedex.R
import com.example.mypokedex.data.entities.Pokemon
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    //recibir argumentos
    private val args: PokemonDetailFragmentArgs by navArgs()
    //
    private lateinit var imageView: ImageView
    private lateinit var hpText: TextView
    private lateinit var attackText: TextView
    private lateinit var defenseText: TextView
    private lateinit var speedText: TextView
    private lateinit var loadingWheel: ProgressBar
    private lateinit var playFab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        val pokemon = args.pokemon

        imageView = rootView.findViewById(R.id.fragment_detail_image)
        hpText = rootView.findViewById(R.id.fragment_detail_hp)
        attackText = rootView.findViewById(R.id.fragment_detail_attack)
        defenseText = rootView.findViewById(R.id.fragment_detail_defense)
        speedText = rootView.findViewById(R.id.fragment_detail_speed)
        loadingWheel = rootView.findViewById(R.id.loading_wheel)
        playFab = rootView.findViewById(R.id.play_fab)

        setupToolbar(rootView, pokemon.name)

        setPokemon(pokemon)

        playFab.setOnClickListener {
            val mediaPlayer: MediaPlayer = MediaPlayer.create(requireActivity(), pokemon.soundId)
            mediaPlayer.start()
        }
        return rootView
    }

    private fun setupToolbar(rootView: View, name: String) {
        val toolbar = rootView.findViewById<Toolbar>(R.id.detail_toolbar)
        toolbar.title = name

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun setPokemon(pokemon: Pokemon) {
        loadingWheel.visibility = View.VISIBLE
        //Manejando errores de cargado de imagen con Glide
        Glide.with(this).load(pokemon.imageUrl).listener(object: RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                loadingWheel.visibility = View.GONE
                imageView.setImageResource(R.drawable.image_not_supported_fill0_wght400_grad0_opsz24)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                loadingWheel.visibility = View.GONE
                return false
            }
        }).error(R.drawable.image_not_supported_fill0_wght400_grad0_opsz24).into(imageView)
        //

        hpText.text = getString(R.string.hp_format, pokemon.hp)
        attackText.text = getString(R.string.attack_format, pokemon.attack)
        defenseText.text = getString(R.string.defense_format, pokemon.defense)
        speedText.text = getString(R.string.speed_format, pokemon.speed)

    }
}