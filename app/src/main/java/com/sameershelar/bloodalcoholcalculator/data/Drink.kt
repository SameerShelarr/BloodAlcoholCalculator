package com.sameershelar.bloodalcoholcalculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sameershelar.bloodalcoholcalculator.utils.Constants.DrinkType

@Entity(tableName = "drink_table")
data class Drink(
    val name: String,
    val type: DrinkType,
    val volume: Int,
    val quantity: Int = 0,
    val abv: Double,
    val imageResId: Int = -1,
    var isSelected: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
