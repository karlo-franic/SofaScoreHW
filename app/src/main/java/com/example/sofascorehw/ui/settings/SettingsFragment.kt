package com.example.sofascorehw.ui.settings

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.sofascorehw.AlbumActivity
import com.example.sofascorehw.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
 /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        loadSettings()
    }
*/
    fun loadSettings() {
        val albumActivity : AlbumActivity = AlbumActivity()
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val lang = sp.getString("lang", "en")

        albumActivity.loadLocate()
    }
}