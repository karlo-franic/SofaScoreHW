package com.example.sofascorehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sofascorehw.databinding.ActivityCollapsibleToolbarBinding
import com.example.sofascorehw.model.shared.specificweather.SpecificWeather
import com.example.sofascorehw.ui.search.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

        val all_Spec_Cities = mutableListOf<SpecificWeather>()

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

        var someWeather : SpecificWeather
        weatherViewModel.weatherOne.observe(this, Observer {
            someWeather = it
            if (someWeather != null){
                binding.cityBigTitle.text = someWeather.title

                var date = LocalDate.parse(someWeather.consolidatedWeather[0].applicableDate, DateTimeFormatter.ISO_DATE)
                binding.date.text = "${date.dayOfWeek.toString().take(3)}, ${date.month} ${date.dayOfMonth}"
                binding.time.text = "${someWeather.consolidatedWeather[0].created.take(16).takeLast(5)}"
                binding.conditionText.text = someWeather.consolidatedWeather[0].weatherStateName

                binding.todayTemp.text = "${someWeather.consolidatedWeather[0].theTemp.toInt()}°"

                when (someWeather.consolidatedWeather[0].weatherStateAbbr) {
                    "c" -> binding.todayIcon.setImageResource(R.drawable.ic_c)
                    "h" -> binding.todayIcon.setImageResource(R.drawable.ic_h)
                    "hc" -> binding.todayIcon.setImageResource(R.drawable.ic_hc)
                    "hr" -> binding.todayIcon.setImageResource(R.drawable.ic_hr)
                    "lc" -> binding.todayIcon.setImageResource(R.drawable.ic_lc)
                    "lr" -> binding.todayIcon.setImageResource(R.drawable.ic_lr)
                    "s" -> binding.todayIcon.setImageResource(R.drawable.ic_s)
                    "sl" -> binding.todayIcon.setImageResource(R.drawable.ic_sl)
                    "sn" -> binding.todayIcon.setImageResource(R.drawable.ic_sn)
                    "t" -> binding.todayIcon.setImageResource(R.drawable.ic_t)
                    else -> {
                        binding.todayIcon.setImageResource(R.drawable.ic_lc)
                    }
                }

                binding.minMaxResult.text = "${someWeather.consolidatedWeather[0].minTemp.toInt()}° / ${someWeather.consolidatedWeather[0].maxTemp.toInt()}°"
                binding.windResult.text = "${(someWeather.consolidatedWeather[0].windSpeed.toInt()*1.6).toInt()} km/h (${someWeather.consolidatedWeather[0].windDirectionCompass})"
                binding.humidityResult.text = "${someWeather.consolidatedWeather[0].humidity}%"
                binding.pressureResult.text = "${someWeather.consolidatedWeather[0].airPressure.toInt()} hPa"
                binding.visibilityResult.text = "${(someWeather.consolidatedWeather[0].visibility.toInt()*1.6).toInt()} km"
                binding.accuracyResult.text = "${someWeather.consolidatedWeather[0].predictability}%"

                date = LocalDate.parse(someWeather.consolidatedWeather[1].applicableDate, DateTimeFormatter.ISO_DATE)
                binding.firstDayText.text = "${date.dayOfWeek.toString().toUpperCase().take(3)}"
                date = LocalDate.parse(someWeather.consolidatedWeather[2].applicableDate, DateTimeFormatter.ISO_DATE)
                binding.secondDayText.text = "${date.dayOfWeek.toString().toUpperCase().take(3)}"
                date = LocalDate.parse(someWeather.consolidatedWeather[3].applicableDate, DateTimeFormatter.ISO_DATE)
                binding.thirdDayText.text = "${date.dayOfWeek.toString().toUpperCase().take(3)}"
                date = LocalDate.parse(someWeather.consolidatedWeather[4].applicableDate, DateTimeFormatter.ISO_DATE)
                binding.fourthDayText.text = "${date.dayOfWeek.toString().toUpperCase().take(3)}"
                date = LocalDate.parse(someWeather.consolidatedWeather[5].applicableDate, DateTimeFormatter.ISO_DATE)
                binding.fifthDayText.text = "${date.dayOfWeek.toString().toUpperCase().take(3)}"

                binding.firstDayTemp.text = "${someWeather.consolidatedWeather[1].theTemp.toInt()}°"
                binding.secondDayTemp.text = "${someWeather.consolidatedWeather[2].theTemp.toInt()}°"
                binding.thirdDayTemp.text = "${someWeather.consolidatedWeather[3].theTemp.toInt()}°"
                binding.fourthDayTemp.text = "${someWeather.consolidatedWeather[4].theTemp.toInt()}°"
                binding.fifthDayTemp.text = "${someWeather.consolidatedWeather[5].theTemp.toInt()}°"

                when (someWeather.consolidatedWeather[1].weatherStateAbbr) {
                    "c" -> binding.firstDayIcon.setImageResource(R.drawable.ic_c)
                    "h" -> binding.firstDayIcon.setImageResource(R.drawable.ic_h)
                    "hc" -> binding.firstDayIcon.setImageResource(R.drawable.ic_hc)
                    "hr" -> binding.firstDayIcon.setImageResource(R.drawable.ic_hr)
                    "lc" -> binding.firstDayIcon.setImageResource(R.drawable.ic_lc)
                    "lr" -> binding.firstDayIcon.setImageResource(R.drawable.ic_lr)
                    "s" -> binding.firstDayIcon.setImageResource(R.drawable.ic_s)
                    "sl" -> binding.firstDayIcon.setImageResource(R.drawable.ic_sl)
                    "sn" -> binding.firstDayIcon.setImageResource(R.drawable.ic_sn)
                    "t" -> binding.firstDayIcon.setImageResource(R.drawable.ic_t)
                    else -> {
                        binding.firstDayIcon.setImageResource(R.drawable.ic_lc)
                    }
                }

                when (someWeather.consolidatedWeather[2].weatherStateAbbr) {
                    "c" -> binding.secondDayIcon.setImageResource(R.drawable.ic_c)
                    "h" -> binding.secondDayIcon.setImageResource(R.drawable.ic_h)
                    "hc" -> binding.secondDayIcon.setImageResource(R.drawable.ic_hc)
                    "hr" -> binding.secondDayIcon.setImageResource(R.drawable.ic_hr)
                    "lc" -> binding.secondDayIcon.setImageResource(R.drawable.ic_lc)
                    "lr" -> binding.secondDayIcon.setImageResource(R.drawable.ic_lr)
                    "s" -> binding.secondDayIcon.setImageResource(R.drawable.ic_s)
                    "sl" -> binding.secondDayIcon.setImageResource(R.drawable.ic_sl)
                    "sn" -> binding.secondDayIcon.setImageResource(R.drawable.ic_sn)
                    "t" -> binding.secondDayIcon.setImageResource(R.drawable.ic_t)
                    else -> {
                        binding.secondDayIcon.setImageResource(R.drawable.ic_lc)
                    }
                }

                when (someWeather.consolidatedWeather[3].weatherStateAbbr) {
                    "c" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_c)
                    "h" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_h)
                    "hc" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_hc)
                    "hr" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_hr)
                    "lc" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_lc)
                    "lr" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_lr)
                    "s" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_s)
                    "sl" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_sl)
                    "sn" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_sn)
                    "t" -> binding.thirdDayIcon.setImageResource(R.drawable.ic_t)
                    else -> {
                        binding.thirdDayIcon.setImageResource(R.drawable.ic_lc)
                    }
                }

                when (someWeather.consolidatedWeather[4].weatherStateAbbr) {
                    "c" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_c)
                    "h" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_h)
                    "hc" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_hc)
                    "hr" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_hr)
                    "lc" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_lc)
                    "lr" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_lr)
                    "s" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_s)
                    "sl" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_sl)
                    "sn" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_sn)
                    "t" -> binding.fourthDayIcon.setImageResource(R.drawable.ic_t)
                    else -> {
                        binding.fourthDayIcon.setImageResource(R.drawable.ic_lc)
                    }
                }

                when (someWeather.consolidatedWeather[5].weatherStateAbbr) {
                    "c" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_c)
                    "h" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_h)
                    "hc" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_hc)
                    "hr" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_hr)
                    "lc" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_lc)
                    "lr" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_lr)
                    "s" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_s)
                    "sl" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_sl)
                    "sn" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_sn)
                    "t" -> binding.fifthDayIcon.setImageResource(R.drawable.ic_t)
                    else -> {
                        binding.fifthDayIcon.setImageResource(R.drawable.ic_lc)
                    }
                }
            }
        })

        binding.backBtnCity.setOnClickListener {
            finish()
        }
    }
}