package com.example.sofascorehw.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofascorehw.CollapsibleToolbarActivity
import com.example.sofascorehw.OnCityClickListener
import com.example.sofascorehw.R
import com.example.sofascorehw.adapter.WeatherRecycleAdapter
import com.example.sofascorehw.databinding.FragmentSearchBinding
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.networking.model.WeathersResponse

class WeathersFragment : Fragment(), OnCityClickListener {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_albums, container, false)
        binding = FragmentSearchBinding.bind(view)
        val root = binding.root
/*
        val factory = InjectorUtils.provideAlbumsViewModelFactory()
        weatherViewModel = ViewModelProviders.of(this, factory)
            .get(WeatherViewModel::class.java)
*/
        binding.albumList.layoutManager = LinearLayoutManager(context)
        weatherViewModel.weatherList.observe(viewLifecycleOwner, Observer {
            val adapter = WeatherRecycleAdapter(requireContext(), it, this)
            binding.albumList.adapter = adapter
        })

     //   weatherViewModel.getSearchedWeathers("san")
    //    weatherViewModel.getSpecificWeather(851128)
     //   weatherViewModel.getSearchedWeathers()
/*
        val all_Albums = mutableListOf<String>()

        val recyc_titles = mutableListOf<String>()
        val recyc_band = mutableListOf<String>()
        val recyc_img = mutableListOf<String>()

        weatherViewModel.getAlbums().observe(this, Observer { albums ->
            albums.forEach { album ->
                all_Albums += album.toString()

                recyc_titles += album.name
                recyc_band += album.band
                recyc_img += album.img
            }
        })

        binding.albumList.layoutManager = LinearLayoutManager(context)
        binding.albumList.adapter =
            RecycleAdapter(recyc_titles, recyc_band, recyc_img, this)
*/
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

    /*
    override fun onAlbumItemClicked(position: Int) {

        val all_Albums = mutableListOf<Album>()

        weatherViewModel.getAlbums().observe(this, Observer { albums ->
            albums.forEach { album ->
                all_Albums += album
            }
        })

        val intent = Intent(this.context, CollapsibleToolbarActivity::class.java)
        intent.putExtra("name", all_Albums[position].name)
        intent.putExtra("band", all_Albums[position].band)
        intent.putExtra("single", all_Albums[position].single)
        intent.putExtra("country", all_Albums[position].country)
        intent.putExtra("city", all_Albums[position].city)
        intent.putExtra("year", all_Albums[position].year)
        intent.putExtra("song_count", all_Albums[position].song_count)
        intent.putExtra("genre", all_Albums[position].genre)
        intent.putExtra("sold", all_Albums[position].sold)
        intent.putExtra("img", all_Albums[position].img)

        startActivity(intent)
    }
*/

}

