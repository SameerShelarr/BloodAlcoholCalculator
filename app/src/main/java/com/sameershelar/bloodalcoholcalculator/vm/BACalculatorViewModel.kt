package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.*
import com.sameershelar.bloodalcoholcalculator.data.PreferencesManager
import com.sameershelar.bloodalcoholcalculator.data.dao.AddedDrinkDao
import com.sameershelar.bloodalcoholcalculator.data.tables.AddedDrink
import com.sameershelar.bloodalcoholcalculator.utils.getGenderConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BACalculatorViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    addedDrinkDao: AddedDrinkDao,
) : ViewModel() {

    private val bacMutableLive: MutableLiveData<Double> = MutableLiveData()
    val bacLive: LiveData<Double> by this::bacMutableLive

    private val timeUntilSoberMutableLive: MutableLiveData<Double> = MutableLiveData()
    val timeUntilSoberLive: LiveData<Double> by this::timeUntilSoberMutableLive

    val addedDrinksLive = addedDrinkDao.getAllLive()

    fun getPreferencesFlow() = preferencesManager.preferencesFlow.asLiveData()

    fun updateIsPreferencesSelected(isPreferencesSelected: Boolean) = viewModelScope.launch {
        preferencesManager.setPreferenceSelected(isPreferencesSelected)
    }

    fun calculateAndShowBACAndTimeUntilSober(addedDrinks: List<AddedDrink>) {

        val bacInPercent = calculateBacInPercentForDrinks(addedDrinks)
        val timeUntilSober = bacInPercent / 0.015
        bacMutableLive.value = bacInPercent
        timeUntilSoberMutableLive.value = timeUntilSober
    }

    private fun calculateBacInPercentForDrinks(addedDrinks: List<AddedDrink>): Double =
        runBlocking {
            val abvInGram = getAbvInGramsForAddedDrinks(addedDrinks)
            val weightInGram = preferencesManager.getWeight() * 1000.0
            val genderConstant = getGenderConstant(gender = preferencesManager.getGender())
            val highestStartTime = findMaxStartTime(addedDrinks)

            val abv =
                ((abvInGram / (weightInGram * genderConstant)) * 100.0) - ((highestStartTime / 60) * 0.015)

            return@runBlocking if (abv < 0) 0.0 else abv
        }

    private fun findMaxStartTime(drinks: List<AddedDrink>): Int {
        var max = 0
        return if (drinks.isNotEmpty()) {
            drinks.forEach { addedDrink ->
                if (addedDrink.drink.startedMinsAgo > max) max = addedDrink.drink.startedMinsAgo
            }
            max
        } else {
            max
        }
    }

    private fun getAbvInGramsForAddedDrinks(addedDrinks: List<AddedDrink>): Double =
        addedDrinks.sumOf { addedDrink -> ((addedDrink.drink.volume / 100.0) * addedDrink.drink.abv) * addedDrink.drink.quantity }
}