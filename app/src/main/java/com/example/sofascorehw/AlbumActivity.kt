package com.example.sofascorehw

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.sofascorehw.databinding.ActivityAlbumBinding
import com.example.sofascorehw.language.MyContextWrapper
import com.example.sofascorehw.language.MyPreference
import com.example.sofascorehw.ui.favorite.FavoritesFragment
import com.example.sofascorehw.ui.favorite.order.FavoritesOrderFragment
import com.example.sofascorehw.ui.search.WeathersFragment
import com.example.sofascorehw.ui.settings.SettingsFragment
import java.util.*

class AlbumActivity : AppCompatActivity(), FavoriteFragmentCommunicator {

    private lateinit var binding: ActivityAlbumBinding
    lateinit var context: Context
    lateinit var myPreference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settingsFragment = SettingsFragment()
        val searchFragment = WeathersFragment()
        val favoritesFragment = FavoritesFragment()

        //Language
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val defaultLangAction = sp.getString("lang", "")

        if (defaultLangAction != null) {
            myPreference.setLoginCount(defaultLangAction)
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, searchFragment)
            commit()
        }

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment, searchFragment)
                        commit()
                    }
                }
                R.id.navigation_favorite -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment, favoritesFragment)
                        commit()
                    }
                }
                R.id.navigation_settings -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.nav_host_fragment, settingsFragment)
                        commit()
                    }
                }
            }
            true
        }
    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
        startActivity(Intent(this, AlbumActivity::class.java))
    }

    fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocate(language)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase, lang))
    }

    override fun toReorder() {
        val favoritesOrderFragment = FavoritesOrderFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, favoritesOrderFragment)
            commit()
        }
    }

    override fun toFavorite() {
        val favoritesFragment1 = FavoritesFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, favoritesFragment1)
            commit()
        }
    }

/*
    fun changeLang() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)

        val lang = sp.getString("lang", "")

        if(lang == "hr") {

        } else if (lang == "en") {
            context = LocaleHelper.setLocale(this, "en")
            resources = context.getResources()
            messageView.setText(resources.getString(R.string.language))
        }
    }
    */
}