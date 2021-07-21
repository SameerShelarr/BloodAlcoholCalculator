package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.ViewModel
import com.sameershelar.bloodalcoholcalculator.DrinkDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDrinksViewModel @Inject constructor(
    private val drinksDao: DrinkDao,
) : ViewModel()  {

    fun getAllDrinksLive() = drinksDao.getAll()
}