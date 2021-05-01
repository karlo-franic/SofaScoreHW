package com.example.sofascorehw.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofascorehw.CollapsibleToolbarActivity
import com.example.sofascorehw.OnCityClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.adapter.WeatherRecycleAdapter
import com.example.sofascorehw.databinding.FragmentSearchBinding
import com.example.sofascorehw.model.shared.WeathersResponse

class WeathersFragment : Fragment(), OnCityClickListener {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        binding = FragmentSearchBinding.bind(view)
        val root = binding.root

        binding.albumList.layoutManager = LinearLayoutManager(context)
        weatherViewModel.weatherList.observe(viewLifecycleOwner, Observer {
            val adapter = WeatherRecycleAdapter(requireContext(), it, this)
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

        //   weatherViewModel.getSearchedWeathers("san")
        //    weatherViewModel.getSpecificWeather(851128)
        //   weatherViewModel.getSearchedWeathers()

        return root
    }

    override fun onCityItemClicked(position: Int) {

        val all_Cities = mutableListOf<WeathersResponse>()

        weatherViewModel.getInitWeathers().observe(this, Observer { cities ->
            cities.forEach { city ->
                all_Cities += city
            }
        })

        val intent = Intent(this.context, CollapsibleToolbarActivity::class.java)
        intent.putExtra("title", all_Cities[position].title)
        intent.putExtra("type", all_Cities[position].location_type)
        intent.putExtra("woeid", all_Cities[position].woeid)
        intent.putExtra("latt_long", all_Cities[position].latt_long)

        startActivity(intent)
    }
}

