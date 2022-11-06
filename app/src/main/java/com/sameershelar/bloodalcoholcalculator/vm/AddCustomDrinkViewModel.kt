package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.data.dao.DrinkDao
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink
import com.sameershelar.bloodalcoholcalculator.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomDrinkViewModel @Inject constructor(
    private val drinksDao: DrinkDao,
) : ViewModel() {

    fun addCustomDrink(drinkName: String, drinkVolume: Int, abv: Double) = viewModelScope.launch {
        val drinkToInsert = Drink(
            name = drinkName,
            type = Constants.DrinkType.CUSTOM,
            volume = drinkVolume,
            abv = abv
        )
        drinksDao.insert(drinkToInsert)
    }
}