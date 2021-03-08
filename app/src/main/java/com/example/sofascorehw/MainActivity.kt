package com.example.sofascorehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonForTextView : Button = findViewById(R.id.buttonForTextView)
        val textViewOnScreen : TextView = findViewById(R.id.textViewOnScreen)

        val buttonTextOdd : String = getString(R.string.button_name_odd)
        val buttonTextEven : String = getString(R.string.button_name_even)
        val TextViewOdd : String = getString(R.string.textview_change_odd)
        val TextViewEven : String = getString(R.string.textview_change_even)

        var counter = 0

        buttonForTextView.setOnClickListener {

            if (counter % 2 == 0) {
                textViewOnScreen.text = "$counter $TextViewEven"
                buttonForTextView.text = buttonTextEven
            }
            else {
                textViewOnScreen.text = "$counter $TextViewOdd"
                buttonForTextView.text = buttonTextOdd
            }

            counter = counter.inc()




        }
    }
}