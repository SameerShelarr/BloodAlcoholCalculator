package com.sameershelar.bloodalcoholcalculator.data.tables

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sameershelar.bloodalcoholcalculator.data.converters.DrinkConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "added_drink_table")
data class AddedDrink(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(
        typeAffinity = ColumnInfo.TEXT
    ) @TypeConverters(DrinkConverter::class)
    var drink: Drink,

    ) : Parcelable
