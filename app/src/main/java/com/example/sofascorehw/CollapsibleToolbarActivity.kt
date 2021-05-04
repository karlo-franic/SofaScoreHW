package com.example.sofascorehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sofascorehw.databinding.ActivityCollapsibleToolbarBinding
import com.example.sofascorehw.model.shared.SpecificWeatherResponse
import com.example.sofascorehw.ui.search.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CollapsibleToolbarActivity : AppCompatActivity() {

    private val weatherViewModel by viewModels<WeatherViewModel>()
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var binding: ActivityCollapsibleToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsible_toolbar)

        binding = ActivityCollapsibleToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coordinatorLayout = binding.coordinatorLayout

        val all_Spec_Cities = mutableListOf<SpecificWeatherResponse>()

        /*
        weatherViewModel.getInitSpecificWeather().observe(this, Observer { cities ->
            cities.forEach { city ->
                all_Spec_Cities += city
            }
        })

         */
        weatherViewModel.getSpecificWeather(intent.getIntExtra("woeid", 0))
        /*
        var specificCity: SpecificWeatherResponse? = weatherViewModel.getInitSpecificWeather().value
        if (specificCity != null) {
            binding.cityTitle.text = specificCity.title
        }
         */

        var someWeather : SpecificWeatherResponse
        weatherViewModel.getInitSpecificWeather().observe(this, Observer {
            someWeather = it
            if (someWeather != null){
                binding.cityTitle.text = someWeather.title
            }
        })




     //   binding.cityTitle.text = intent.getStringExtra("title")
        /*
        binding.editTextAlbum.text = intent.getStringExtra("name")
        binding.editTextBand.text = intent.getStringExtra("band")
        binding.editTextSingle.text = intent.getStringExtra("single")
        binding.editTextYear.text = intent.getStringExtra("year")
        binding.editTextCount.text = intent.getStringExtra("song_count")
*/
        binding.floatingBtn.setOnClickListener {
            val snackbar =
                Snackbar.make(coordinatorLayout, "Added to favorite!", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}