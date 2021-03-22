package com.example.sofascorehw.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.sofascorehw.R
import com.example.sofascorehw.model.Album
import com.example.sofascorehw.model.Genre
import com.example.sofascorehw.utilities.InjectorUtils

class AddFragment : Fragment() {

    private lateinit var addViewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_add, container, false)

        val factory = InjectorUtils.provideAddViewModelFactory()
        addViewModel = ViewModelProviders.of(this, factory)
            .get(AddViewModel::class.java)

        val button_add_album: Button = view.findViewById(R.id.button_add_album)

        val editText_album: EditText = view.findViewById(R.id.editText_album)
        val editText_band: EditText = view.findViewById(R.id.editText_band)
        val editText_single: EditText = view.findViewById(R.id.editText_single)
        val editText_count: EditText = view.findViewById(R.id.editText_count)
        val spinner_genre: Spinner = view.findViewById(R.id.spinner_genre)

        val genre_value = arrayOf(
            Genre.ROCK.toString(),
            Genre.RAP.toString(),
            Genre.POP.toString(),
            Genre.FOLK.toString(),
            Genre.TECHNO.toString(),
            Genre.CLASSICAL.toString(),
            Genre.ETNO.toString()
        )

        spinner_genre.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genre_value)

        var enum_Result : String = ""

        spinner_genre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                enum_Result = genre_value.get(position)
            }
        }

        var enum_Genre : Genre = Genre.ROCK

        when (enum_Result) {
            Genre.ROCK.toString() -> enum_Genre = Genre.ROCK
            Genre.RAP.toString() -> enum_Genre = Genre.RAP
            Genre.POP.toString() -> enum_Genre = Genre.POP
            Genre.FOLK.toString() -> enum_Genre = Genre.FOLK
            Genre.TECHNO.toString() -> enum_Genre = Genre.TECHNO
            Genre.CLASSICAL.toString() -> enum_Genre = Genre.CLASSICAL
            Genre.ETNO.toString() -> enum_Genre = Genre.ETNO
        }

        button_add_album.setOnClickListener {
            val album = Album(
                editText_album.text.toString(),
                editText_band.text.toString(),
                editText_single.text.toString(),
                editText_count.text.toString(),
                enum_Genre
            )
            addViewModel.addAlbum(album)
            editText_album.setText("")
            editText_band.setText("")
            editText_single.setText("")
            editText_count.setText("")
        }

        return view
    }
}