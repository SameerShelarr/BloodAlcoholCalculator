package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.*
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.data.PreferencesManager
import com.sameershelar.bloodalcoholcalculator.utils.getGenderConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BACalculatorViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private val addedDrinksMutableLive: MutableLiveData<MutableList<Drink>> = MutableLiveData()
    val addedDrinksLive: LiveData<MutableList<Drink>> by this::addedDrinksMutableLive

    private val bacMutableLive: MutableLiveData<Double> = MutableLiveData()
    val bacLive: LiveData<Double> by this::bacMutableLive

    private val timeUntilSoberMutableLive: MutableLiveData<Double> = MutableLiveData()
    val timeUntilSoberLive: LiveData<Double> by this::timeUntilSoberMutableLive

    private val baCalculatorChannel = Channel<BACalculatorEvents>()
    val baCalculatorTaskEvent = baCalculatorChannel.receiveAsFlow()

    fun getPreferencesFlow() = preferencesManager.preferencesFlow.asLiveData()

    fun updateIsPreferencesSelected(isPreferencesSelected: Boolean) = viewModelScope.launch {
        preferencesManager.setPreferenceSelected(isPreferencesSelected)
    }

    fun addDrink(drink: Drink) {
        if (addedDrinksMutableLive.value.isNullOrEmpty()) {
            addedDrinksMutableLive.value = mutableListOf(drink)
        } else {
            addedDrinksMutableLive.value?.let {
                it += listOf(drink)
                addedDrinksMutableLive.value = it
            }
        }
    }

    fun removeDrink(drink: Drink) {
        addedDrinksMutableLive.value?.let {
            it -= linkedSetOf(drink)
            addedDrinksMutableLive.value = it
        }
    }

    fun calculateAndShowBACAndTimeUntilSober(addedDrinks: List<Drink>) {
        val bacInPercent = calculateBacInPercentForDrinks(addedDrinks)
        val timeUntilSober = bacInPercent / 0.015
        bacMutableLive.value = bacInPercent
        timeUntilSoberMutableLive.value = timeUntilSober
    }

    private fun calculateBacInPercentForDrinks(addedDrinks: List<Drink>): Double = runBlocking {
        val abvInGram = getAbvInGramsForAddedDrinks(addedDrinks)
        val weightInGram = preferencesManager.getWeight() * 1000.0
        val genderConstant = getGenderConstant(gender = preferencesManager.getGender())

        return@runBlocking (abvInGram / (weightInGram * genderConstant)) * 100.0
    }

    private fun getAbvInGramsForAddedDrinks(addedDrinks: List<Drink>): Double =
        addedDrinks.sumOf { drink -> ((drink.volume / 100.0) * drink.abv) * drink.quantity }

    sealed class BACalculatorEvents {
        data class OnCalculationComplete(val bacLevel: Double, val timeWhenSober: Date) :
            BACalculatorEvents()
    }
}