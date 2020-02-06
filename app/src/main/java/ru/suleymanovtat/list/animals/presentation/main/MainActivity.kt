package ru.suleymanovtat.list.animals.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.suleymanovtat.list.animals.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.constraintLayout, MainFragment.newInstance())
                .commit()
        }
    }
}
