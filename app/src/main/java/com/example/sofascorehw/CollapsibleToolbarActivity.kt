package com.example.sofascorehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.sofascorehw.databinding.ActivityCollapsibleToolbarBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CollapsibleToolbarActivity : AppCompatActivity() {

    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var binding: ActivityCollapsibleToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsible_toolbar)

        binding = ActivityCollapsibleToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coordinatorLayout = binding.coordinatorLayout

        binding.editTextAlbum.text = intent.getStringExtra("name")
        binding.editTextBand.text = intent.getStringExtra("band")
        binding.editTextSingle.text = intent.getStringExtra("single")
        binding.editTextCity.text = intent.getStringExtra("city")
        binding.editTextYear.text = intent.getStringExtra("year")
        binding.editTextCount.text = intent.getStringExtra("song_count")
        binding.editTextSold.text = intent.getStringExtra("sold")

        var urlImg: String? = intent.getStringExtra("img")

        Picasso.get().load(urlImg)
            .placeholder(com.example.sofascorehw.R.drawable.coffeeresized)
            .error(com.example.sofascorehw.R.drawable.coffeeresized)
            .into(binding.largeImg)

        var x: Int = 1

        binding.floatingBtn.setOnClickListener {
            val snackbar =
                Snackbar.make(coordinatorLayout, "Added to favorite!", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }
}