package com.example.sofascorehw.ui.search

import android.content.Intent
import android.net.Network
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import com.example.sofascorehw.model.shared.WeathersResponse
import android.widget.AutoCompleteTextView
import java.time.LocalDateTime

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
    //    weatherViewModel.deleteAllRecentWeatherFromDb(requireContext())
    //    weatherViewModel.deleteAllFavoriteWeatherFromDb(requireContext())

        binding.albumList.layoutManager = LinearLayoutManager(context)
        weatherViewModel.getRecentWeatherFromDb(requireContext())
        weatherViewModel.weatherRecentWrapperList.observe(viewLifecycleOwner, Observer {
            val adapter = WeatherRecycleAdapter(requireContext(), it, this, this)
            binding.albumList.adapter = adapter
        })


        binding.searchEditText.threshold = 2
        var query = ""

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun afterTextChanged(s: Editable?) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                query = s.toString()
                if (s?.length!! > 1) {
                    weatherViewModel.getSearchedWeathers(query)

                    var titleList : MutableList<String> = ArrayList()
                    weatherViewModel.weatherSearchList.observe(viewLifecycleOwner, Observer { cities ->
                        cities.forEach { city ->
                            titleList.add(city.title)
                        }
                    })

                    var searchAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, titleList)
                    binding.searchEditText.setAdapter(searchAdapter)
                }
            }
        })

        binding.searchEditText.setOnItemClickListener { parent, view, position, id ->
            val all_Cities = mutableListOf<WeathersResponse>()

            weatherViewModel.weatherSearchList.observe(viewLifecycleOwner, Observer { cities ->
                cities.forEach { city ->
                    all_Cities += city
                }
            })

          //  weatherViewModel.getSpecificWeather(all_Cities[position].woeid)

            var weatherOne: WeathersResponse = all_Cities[position]
            weatherOne.id = all_Cities[position].woeid
            weatherOne.date = LocalDateTime.now().toString()

            weatherViewModel.saveRecentWeatherToDb(requireContext(), weatherOne)

            val intent = Intent(this.context, CollapsibleToolbarActivity::class.java)
            intent.putExtra("woeid", all_Cities[position].woeid)

            startActivity(intent)
        }



        return root
    }

    override fun onCityItemClicked(position: Int) {

        val all_Cities = mutableListOf<WeathersResponse>()

        weatherViewModel.weatherList.observe(this, Observer { cities ->
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

        weatherViewModel.weatherList.observe(this, Observer { cities ->
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
            all_Cities[position].latt_long,
            1
        )

        weatherViewModel.saveFavoriteWeatherToDb(requireContext(), weatherOne)
        weatherViewModel.deleteRecentWeatherFromDb(requireContext(), all_Cities[position])

    }
}


