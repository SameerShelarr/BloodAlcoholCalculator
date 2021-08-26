package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.DrinkDao
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDrinksAndDrinksHistoryViewModel @Inject constructor(
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

    fun sortDrinksAccordingToCategorySelected(checkedId: Int) =
        when (checkedId) {
            R.id.all_chip -> {
                drinksList
            }
            R.id.beer_chip -> {
                drinksList.filter { drink -> drink.type == Constants.DrinkType.BEER }
            }

            R.id.whisky_chip -> {
                drinksList.filter { drink -> drink.type == Constants.DrinkType.WHISKY }
            }

            R.id.vodka_chip -> {
                drinksList.filter { drink -> drink.type == Constants.DrinkType.VODKA }
            }

            R.id.rum_chip -> {
                drinksList.filter { drink -> drink.type == Constants.DrinkType.RUM }
            }
            R.id.wine_chip -> {
                drinksList.filter { drink -> drink.type == Constants.DrinkType.WINE }
            }

            R.id.gin_chip -> {
                drinksList.filter { drink -> drink.type == Constants.DrinkType.GIN }
            }

            R.id.brandy_chip -> {
                drinksList.filter { drink -> drink.type == Constants.DrinkType.BRANDY }
            }

            else -> {
                arrayListOf()
            }
        }

    sealed class AddDrinkEvent {
        data class ShowSelectDrinkSnack(val message: String) : AddDrinkEvent()
    }
}