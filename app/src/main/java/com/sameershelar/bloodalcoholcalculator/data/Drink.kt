package com.sameershelar.bloodalcoholcalculator.data

data class Drink(
    val id: Int,
    val name: String,
    val volume: Int,
    val quantity: Int = 0,
    val abv: Int,
    val imageResId: Int = -1,
    var isSelected: Boolean = false,
)
