package com.example.sofascorehw.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sofascorehw.*
import com.example.sofascorehw.adapter.WeatherFavoriteRecycleAdapter
import com.example.sofascorehw.adapter.WeatherRecycleAdapter
import com.example.sofascorehw.databinding.FragmentFavoritesBinding
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeathersResponse
import com.example.sofascorehw.ui.favorite.order.FavoritesOrderFragment
import com.example.sofascorehw.ui.search.WeatherViewModel
import java.util.*
import kotlin.collections.ArrayList

class FavoritesFragment : Fragment(), OnCityClickListener, OnFavoriteClickListener {

    private val weatherFavoriteViewModel: WeatherFavoriteViewModel by activityViewModels()
    private lateinit var binding: FragmentFavoritesBinding
    var adapter: WeatherFavoriteRecycleAdapter? = null
    private lateinit var communicator: FavoriteFragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorites, container, false)
        binding = FragmentFavoritesBinding.bind(view)
        val root = binding.root
        communicator = activity as FavoriteFragmentCommunicator

        binding.favoriteList.layoutManager = LinearLayoutManager(context)
        weatherFavoriteViewModel.getFavoriteWeatherFromDb(requireContext())
        weatherFavoriteViewModel.weatherFavoriteWrapperList.observe(viewLifecycleOwner, Observer {
            adapter = WeatherFavoriteRecycleAdapter(requireContext(), it, this, this)
            binding.favoriteList.adapter = adapter
        })

        weatherFavoriteViewModel.getFavoriteWeatherFromDb(requireContext())

        binding.reorderBtn.setOnClickListener {
            communicator.toReorder()
        }

        return root
    }

    override fun onCityItemClicked(position: Int) {
        val all_Cities = mutableListOf<FavoriteWeather>()

        weatherFavoriteViewModel.getInitFavoriteWeathers().observe(this, Observer { cities ->
            cities.forEach { city ->
                all_Cities += city
            }
        })
        weatherFavoriteViewModel.getSpecificWeather(all_Cities[position].woeid)

        val intent = Intent(this.context, CollapsibleToolbarActivity::class.java)
        intent.putExtra("woeid", all_Cities[position].woeid)

        startActivity(intent)
    }

    override fun onFavoriteBtnClicked(position: Int) {
        val all_Cities = mutableListOf<FavoriteWeather>()

        weatherFavoriteViewModel.getInitFavoriteWeathers().observe(this, Observer { cities ->
            cities.forEach { city ->
                all_Cities += city
            }
        })

        weatherFavoriteViewModel.deleteFavoriteWeatherFromDb(requireContext(), all_Cities[position])
    }
}