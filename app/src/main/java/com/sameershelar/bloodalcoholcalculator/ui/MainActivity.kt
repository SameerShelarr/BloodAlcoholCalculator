package com.sameershelar.bloodalcoholcalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sameershelar.bloodalcoholcalculator.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_BloodAlcoholCalculator)
        setContentView(R.layout.activity_main)
    }
}