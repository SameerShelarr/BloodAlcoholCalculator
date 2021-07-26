package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.DrinkDao
import com.sameershelar.bloodalcoholcalculator.data.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDrinksViewModel @Inject constructor(
    private val drinksDao: DrinkDao,
) : ViewModel() {

    var drinksList: MutableList<Drink> = mutableListOf()

    private val addDrinkChannel = Channel<AddDrinkEvent>()
    val addDrinkTaskEvent = addDrinkChannel.receiveAsFlow()

    fun getAllDrinksLive() = drinksDao.getAll()

    fun addSelectedDrinkOrShowError(error: String): Drink? {
        return if (drinksList.any { drink -> drink.isSelected }) {
            drinksList.first { drink -> drink.isSelected }
        } else {
            viewModelScope.launch {
                addDrinkChannel.send(AddDrinkEvent.ShowSelectDrinkSnack(error))
            }
            null
        }
    }

    sealed class AddDrinkEvent {
        data class ShowSelectDrinkSnack(val message: String) : AddDrinkEvent()
    }
}