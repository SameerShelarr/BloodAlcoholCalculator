package com.sameershelar.bloodalcoholcalculator

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sameershelar.bloodalcoholcalculator.data.Drink

@Dao
interface DrinkDao {

    @Insert
    fun insert(drink: Drink)

    @Query("SELECT * FROM drink_table")
    fun getAll(): LiveData<List<Drink>>
}