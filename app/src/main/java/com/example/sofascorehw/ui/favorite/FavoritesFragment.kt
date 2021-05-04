package com.example.sofascorehw.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.sofascorehw.CollapsibleToolbarActivity
import com.example.sofascorehw.OnCityClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.databinding.FragmentFavoritesBinding
import com.example.sofascorehw.model.shared.WeathersResponse
import com.example.sofascorehw.ui.search.WeatherViewModel

class FavoritesFragment: Fragment(), OnCityClickListener {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorites, container, false)
        binding = FragmentFavoritesBinding.bind(view)
        val root = binding.root




        return root
    }

        override fun onCityItemClicked(position: Int) {
            val all_Cities = mutableListOf<WeathersResponse>()

            weatherViewModel.getInitWeathers().observe(this, Observer { cities ->
                cities.forEach { city ->
                    all_Cities += city
                }
            })
            weatherViewModel.getSpecificWeather(all_Cities[position].woeid)

            val intent = Intent(this.context, CollapsibleToolbarActivity::class.java)
            intent.putExtra("woeid", all_Cities[position].woeid)

            startActivity(intent)
        }
}