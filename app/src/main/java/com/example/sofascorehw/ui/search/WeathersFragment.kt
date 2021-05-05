package com.example.sofascorehw.ui.search

import android.content.Intent
import android.net.Network
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofascorehw.CollapsibleToolbarActivity
import com.example.sofascorehw.OnCityClickListener
import com.example.sofascorehw.OnFavoriteClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.adapter.WeatherRecycleAdapter
import com.example.sofascorehw.databinding.FragmentSearchBinding
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.SpecificWeatherResponse
import com.example.sofascorehw.model.shared.WeathersResponse

class WeathersFragment : Fragment(), OnCityClickListener, OnFavoriteClickListener {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding
    private var counter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        binding = FragmentSearchBinding.bind(view)
        val root = binding.root

/*
        var zaIzbrisat = WeathersResponse(671072, "San Francisco", "City", 2487956, "40.71455,-74.007118")

        weatherViewModel.getInitWeathers().observe(viewLifecycleOwner, Observer { cities ->
            cities.forEach { city ->
                if (city.title == "Moscow") {
                   // zaIzbrisat = city
                }
            }
        })
        weatherViewModel.deleteRecentWeatherFromDb(requireContext(), zaIzbrisat)
*/
        binding.albumList.layoutManager = LinearLayoutManager(context)
        weatherViewModel.getRecentWeatherFromDb(requireContext())
        weatherViewModel.weatherList.observe(viewLifecycleOwner, Observer {
            val adapter = WeatherRecycleAdapter(requireContext(), it, this, this)
            //    adapter.checkIfFavorite(weatherViewModel)
            binding.albumList.adapter = adapter
        })

        binding.searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchEditText.clearFocus()
                if (query != null) {
                    weatherViewModel.getSearchedWeathers(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

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

        var weatherOne: WeathersResponse = all_Cities[position]
        weatherOne.id = all_Cities[position].woeid

        //  counter += 1

        weatherViewModel.saveRecentWeatherToDb(requireContext(), all_Cities[position])

        val intent = Intent(this.context, CollapsibleToolbarActivity::class.java)
        intent.putExtra("woeid", all_Cities[position].woeid)

        startActivity(intent)
    }

    override fun onFavoriteBtnClicked(position: Int) {
        val all_Cities = mutableListOf<WeathersResponse>()

        weatherViewModel.getInitWeathers().observe(this, Observer { cities ->
            cities.forEach { city ->
                all_Cities += city
            }
        })
        weatherViewModel.getSpecificWeather(all_Cities[position].woeid)

        var weatherOne = FavoriteWeather(
            all_Cities[position].woeid,
            all_Cities[position].title,
            all_Cities[position].location_type,
            all_Cities[position].woeid,
            all_Cities[position].latt_long
        )

        weatherViewModel.saveFavoriteWeatherToDb(requireContext(), weatherOne)
        weatherViewModel.deleteRecentWeatherFromDb(requireContext(), all_Cities[position])

    }
}

