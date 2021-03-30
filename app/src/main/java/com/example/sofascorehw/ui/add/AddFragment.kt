package com.example.sofascorehw.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.example.sofascorehw.R
import com.example.sofascorehw.databinding.FragmentAddBinding
import com.example.sofascorehw.language.MyPreference
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.model.Genre
import com.example.sofascorehw.utilities.InjectorUtils

class AddFragment : Fragment() {

    private lateinit var addViewModel: AddViewModel
    private lateinit var binding: FragmentAddBinding
    lateinit var myPreference: MyPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_add, container, false)
        binding = FragmentAddBinding.bind(view)

        //Language
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultLangAction = sp.getString("lang", "")
/*
        if (defaultLangAction != null) {
            myPreference.setLoginCount(defaultLangAction)
        }
*/
        val factory = InjectorUtils.provideAddViewModelFactory()
        addViewModel = ViewModelProviders.of(this, factory)
            .get(AddViewModel::class.java)

        val genre_value = arrayOf(
            Genre.ROCK.toString(),
            Genre.RAP.toString(),
            Genre.POP.toString(),
            Genre.FOLK.toString(),
            Genre.TECHNO.toString(),
            Genre.CLASSICAL.toString(),
            Genre.ETNO.toString()
        )

        binding.spinnerGenre.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genre_value)

        var enum_Result: String = ""

        binding.spinnerGenre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                enum_Result = genre_value.get(position)
            }
        }
        var enum_Genre: Genre = Genre.ROCK

        var radio_Type: String = ""

        binding.radioResult.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_btn_ep)
                radio_Type = "EP"
            if (checkedId == R.id.radio_btn_lp)
                radio_Type = "LP"
        }

        binding.buttonAddAlbum.setOnClickListener {

            when (enum_Result) {
                Genre.ROCK.toString() -> enum_Genre = Genre.ROCK
                Genre.RAP.toString() -> enum_Genre = Genre.RAP
                Genre.POP.toString() -> enum_Genre = Genre.POP
                Genre.FOLK.toString() -> enum_Genre = Genre.FOLK
                Genre.TECHNO.toString() -> enum_Genre = Genre.TECHNO
                Genre.CLASSICAL.toString() -> enum_Genre = Genre.CLASSICAL
                Genre.ETNO.toString() -> enum_Genre = Genre.ETNO
            }

            val album = Album(
                binding.editTextAlbum.text.toString(),
                binding.editTextBand.text.toString(),
                binding.editTextCount.text.toString(),
                binding.editTextSingle.text.toString(),
                binding.editTextYear.text.toString(),
                binding.editTextCountry.text.toString(),
                binding.editTextCity.text.toString(),
                binding.editTextSold.text.toString(),
                enum_Genre,
                radio_Type,
                binding.editTextImg.text.toString()
            )

            if (binding.editTextAlbum.length() == 0 ||
                binding.editTextBand.length() == 0 ||
                binding.editTextSingle.length() == 0 ||
                binding.editTextCount.length() == 0 ||
                binding.editTextYear.length() == 0 ||
                binding.editTextCountry.length() == 0 ||
                binding.editTextCity.length() == 0 ||
                binding.editTextSold.length() == 0 ||
                binding.editTextImg.length() == 0 ||
                radio_Type == ""
            ) {
                Toast.makeText(
                    view.context,
                    "You haven't filled all the fields.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                addViewModel.addAlbum(album)
                binding.editTextAlbum.setText("")
                binding.editTextBand.setText("")
                binding.editTextSingle.setText("")
                binding.editTextCount.setText("")
                binding.editTextYear.setText("")
                binding.editTextCountry.setText("")
                binding.editTextCity.setText("")
                binding.editTextSold.setText("")
                binding.editTextImg.setText("")
                binding.radioResult.clearCheck()
            }
        }


        return view
    }
}