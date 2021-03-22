package com.example.sofascorehw

import android.content.ClipData
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sofascorehw.ui.add.AddFragment
import com.example.sofascorehw.ui.albums.AlbumsFragment

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val addFragment = AddFragment()
        val albumsFragment = AlbumsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, addFragment)
            commit()
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
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

/*
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_add, R.id.navigation_albums
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
*/
    }
}