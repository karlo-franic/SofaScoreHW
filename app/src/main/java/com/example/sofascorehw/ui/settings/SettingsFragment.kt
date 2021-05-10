package com.example.sofascorehw.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofascorehw.AlbumActivity
import com.example.sofascorehw.R
import com.example.sofascorehw.adapter.WeatherFavoriteRecycleAdapter
import com.example.sofascorehw.databinding.FragmentFavoritesBinding
import com.example.sofascorehw.databinding.FragmentSettingsBinding
import com.example.sofascorehw.language.MyPreference
import com.example.sofascorehw.ui.about.AboutActivity
import com.example.sofascorehw.ui.favorite.WeatherFavoriteViewModel
import com.example.sofascorehw.ui.search.WeatherViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: FragmentSettingsBinding
    lateinit var myPreference: MyPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        binding = FragmentSettingsBinding.bind(view)
        val root = binding.root
        myPreference = MyPreference(requireContext())
    //    myPreference.setLoginCount(myPreference.getLoginCount())

        val itemsLang = listOf("English", "Hrvatski")
        val adapterLang = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsLang)
        binding.autocompleteLang.setAdapter(adapterLang)
        if (myPreference.getLoginCount() == "en") {
            binding.autocompleteLang.setText("English", false)
        } else {
            binding.autocompleteLang.setText("Hrvatski", false)
        }

   //     var x: String = myPreference.getLoginCount()
   //     var c = 0
        val itemsCity = listOf("Zagreb", "London", "New York", "Tokyo")
        val adapterCity = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsCity)
        binding.autocompleteCity.setAdapter(adapterCity)
        binding.autocompleteCity.setText("Zagreb", false)

        binding.btnInfo.setOnClickListener {
            val intent = Intent(this.context, AboutActivity::class.java)
            startActivity(intent)
        }

        binding.deleteFavoritesListBtn.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.dialog_title_fav))
                .setMessage(resources.getString(R.string.dialog_text_fav))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.clear)) { dialog, which ->
                    weatherViewModel.deleteAllFavoriteWeatherFromDb(requireContext())

                    val intent = Intent(this.context, AlbumActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                .show()
        }

        binding.deleteRecentListBtn.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.dialog_title_rec))
                .setMessage(resources.getString(R.string.dialog_text_rec))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.clear)) { dialog, which ->
                    weatherViewModel.deleteAllRecentWeatherFromDb(requireContext())

                    val intent = Intent(this.context, AlbumActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                .show()
        }

        binding.autocompleteLang.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                myPreference.setLoginCount("en")
            }
            if (position == 1) {
                myPreference.setLoginCount("hr")
            }
            val intent = Intent(this.context, AlbumActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        // loadSettings()
    }

    fun loadSettings() {
        val albumActivity: AlbumActivity = AlbumActivity()
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val lang = sp.getString("lang", "en")

        albumActivity.loadLocate()
    }
}