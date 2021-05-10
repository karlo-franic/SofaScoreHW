package com.example.sofascorehw.ui.favorite.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sofascorehw.*
import com.example.sofascorehw.adapter.ReorderFavoriteRecycleAdapter
import com.example.sofascorehw.adapter.WeatherFavoriteRecycleAdapter
import com.example.sofascorehw.adapter.WeatherRecycleAdapter
import com.example.sofascorehw.databinding.FragmentFavoritesBinding
import com.example.sofascorehw.databinding.FragmentFavoritesReorderBinding
import com.example.sofascorehw.model.shared.FavoriteWeather
import com.example.sofascorehw.model.shared.WeathersResponse
import com.example.sofascorehw.ui.favorite.WeatherFavoriteViewModel
import com.example.sofascorehw.ui.search.WeatherViewModel
import java.util.*
import kotlin.collections.ArrayList

class FavoritesOrderFragment : Fragment(), OnCityClickListener, OnFavoriteClickListener {

    private val weatherFavoriteViewModel: WeatherFavoriteViewModel by activityViewModels()
    private lateinit var binding: FragmentFavoritesReorderBinding
    var adapter: ReorderFavoriteRecycleAdapter? = null
    private lateinit var communicator: FavoriteFragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorites_reorder, container, false)
        binding = FragmentFavoritesReorderBinding.bind(view)
        val root = binding.root
        communicator = activity as FavoriteFragmentCommunicator

        binding.favoriteList.layoutManager = LinearLayoutManager(context)
        weatherFavoriteViewModel.getFavoriteWeatherFromDb(requireContext())
        weatherFavoriteViewModel.weatherFavoriteWrapperList.observe(viewLifecycleOwner, Observer {
            adapter = ReorderFavoriteRecycleAdapter(requireContext(), it, this, this)
            binding.favoriteList.adapter = adapter
        })

        weatherFavoriteViewModel.getFavoriteWeatherFromDb(requireContext())

        val itemTouchHelperCallback = ItemTouchHelper(
            object :
                ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    var position_viewHolder = viewHolder.adapterPosition
                    var position_target = target.adapterPosition

                    Collections.swap(
                        weatherFavoriteViewModel.weatherFavoriteList.value,
                        position_viewHolder,
                        position_target
                    )
                    adapter?.notifyItemMoved(position_viewHolder, position_target)

                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                }

            })

        itemTouchHelperCallback.attachToRecyclerView(binding.favoriteList)

        var order_position: Int = 1

        binding.reorderBtn.setOnClickListener {
            weatherFavoriteViewModel.weatherFavoriteList.observe(
                viewLifecycleOwner,
                Observer { cities ->
                    cities.forEach { city ->
                        city.order = order_position
                        weatherFavoriteViewModel.updateFavoriteWeatherFromDb(requireContext(), city)
                        order_position += 1
                    }
                })

            communicator.toFavorite()
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