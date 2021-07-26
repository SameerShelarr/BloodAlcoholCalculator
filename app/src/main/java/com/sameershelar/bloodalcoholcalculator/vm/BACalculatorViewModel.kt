package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.*
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BACalculatorViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private val addedDrinksMutableLive: MutableLiveData<MutableList<Drink>> = MutableLiveData()
    val addedDrinksLive: LiveData<MutableList<Drink>> by this::addedDrinksMutableLive

    private val baCalculatorChannel = Channel<BACalculatorEvents>()
    val baCalculatorTaskEvent = baCalculatorChannel.receiveAsFlow()

    fun getPreferencesFlow() = preferencesManager.preferencesFlow.asLiveData()

    fun updateIsPreferencesSelected(isPreferencesSelected: Boolean) = viewModelScope.launch {
        preferencesManager.setPreferenceSelected(isPreferencesSelected)
    }

    fun addDrink(drink: Drink) {
        addedDrinksMutableLive.value?.let {
            it += listOf(drink)
            addedDrinksMutableLive.value = it
        }
    }

    fun removeDrink(drink: Drink) {
        addedDrinksMutableLive.value?.let {
            it -= linkedSetOf(drink)
            addedDrinksMutableLive.value = it
        }
    }

    fun calculateAndShowBACAndTimeUntilSober(addedDrinks: List<Drink>) {
        TODO()
    }

    sealed class BACalculatorEvents {
        data class OnCalculationComplete(val bacLevel: Double, val timeWhenSober: Date) :
            BACalculatorEvents()
    }
}