package com.sameershelar.bloodalcoholcalculator.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink

class DrinkConverter {

    companion object {

        @JvmStatic
        @TypeConverter
        fun fromDrinkToJson(drink: Drink) =
            Gson().toJson(drink)

        @JvmStatic
        @TypeConverter
        fun fromJsonToDrink(json: String): Drink =
            Gson().fromJson(json, Drink::class.java)

    }

}