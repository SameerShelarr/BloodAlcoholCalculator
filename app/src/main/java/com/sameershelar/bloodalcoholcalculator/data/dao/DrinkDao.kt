package com.sameershelar.bloodalcoholcalculator.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink

@Dao
interface DrinkDao {

    @Insert
    suspend fun insert(drink: Drink)

    @Query("SELECT * FROM drink_table")
    fun getAll(): LiveData<List<Drink>>
}