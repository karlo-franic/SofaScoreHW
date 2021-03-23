package com.example.sofascorehw

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.sofascorehw.databinding.ActivityAlbumBinding
import com.example.sofascorehw.ui.add.AddFragment
import com.example.sofascorehw.ui.albums.AlbumsFragment

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addFragment = AddFragment()
        val albumsFragment = AlbumsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, addFragment)
            commit()
        }

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_add -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment, addFragment)
                        commit()
                    }
                }
                R.id.navigation_albums -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment, albumsFragment)
                        commit()
                    }
                }
            }
            true
        }
    }
}