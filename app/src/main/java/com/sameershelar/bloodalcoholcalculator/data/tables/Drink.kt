package com.sameershelar.bloodalcoholcalculator.data.tables

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sameershelar.bloodalcoholcalculator.utils.Constants.DrinkType
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "drink_table")
data class Drink(
    val name: String,
    val type: DrinkType,
    val volume: Int,
    var quantity: Int = 0,
    val abv: Double,
    val imageResId: Int = -1,
    var isSelected: Boolean = false,
    var startedMinsAgo: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) : Parcelable