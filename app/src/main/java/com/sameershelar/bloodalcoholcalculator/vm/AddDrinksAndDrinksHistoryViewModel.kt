package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.dao.AddedDrinkDao
import com.sameershelar.bloodalcoholcalculator.data.dao.DrinkDao
import com.sameershelar.bloodalcoholcalculator.data.tables.AddedDrink
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink
import com.sameershelar.bloodalcoholcalculator.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDrinksAndDrinksHistoryViewModel @Inject constructor(
    private val drinksDao: DrinkDao,
    private val addedDrinkDao: AddedDrinkDao,
) : ViewModel() {

    var drinksList: MutableList<Drink> = mutableListOf()

    private val addDrinkChannel = Channel<AddDrinkEvent>()
    val addDrinkTaskEvent = addDrinkChannel.receiveAsFlow()

    val addedDrinksLive = addedDrinkDao.getAllLive()

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

    fun addDrink(drink: Drink) = CoroutineScope(Dispatchers.IO).launch {
        val addedDrinksList = addedDrinkDao.getAll()
        if (checkIfDrinkAlreadyPresent(addedDrinksList, drink)) {
            val alreadyAddedDrink =
                addedDrinksList.firstOrNull { addedDrink -> addedDrink.drink.id == drink.id }
            alreadyAddedDrink?.let {
                alreadyAddedDrink.drink.quantity = alreadyAddedDrink.drink.quantity + drink.quantity
                addedDrinkDao.update(alreadyAddedDrink)
            }
        } else {
            addedDrinkDao.insert(AddedDrink(drink = drink))
        }
    }

    private fun checkIfDrinkAlreadyPresent(
        addedDrinksList: List<AddedDrink>,
        drink: Drink
    ) = addedDrinksList.any { addedDrink -> addedDrink.drink.id == drink.id }

    fun removeDrink(drink: Drink) = CoroutineScope(Dispatchers.IO).launch {
        addedDrinkDao.delete(drink)
    }

    sealed class AddDrinkEvent {
        data class ShowSelectDrinkSnack(val message: String) : AddDrinkEvent()
    }

    fun deleteAllAddedDrinks() = viewModelScope.launch {
        addedDrinkDao.deleteAll()
    }
}