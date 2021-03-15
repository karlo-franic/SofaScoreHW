package com.example.sofascorehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sofascorehw.view.AddFragment
import com.example.sofascorehw.view.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addFragment = AddFragment()
        val listFragment = ListFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, addFragment)
            commit()
        }

        val buttonFragment1 = findViewById<Button>(R.id.buttonFragment1)
        val buttonFragment2 = findViewById<Button>(R.id.buttonFragment2)

        buttonFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, addFragment)
                commit()
            }
        }

        buttonFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, listFragment)
                commit()
            }
        }


    }


}