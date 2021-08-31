package com.sameershelar.bloodalcoholcalculator.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sameershelar.bloodalcoholcalculator.data.tables.AddedDrink
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink

@Dao
interface AddedDrinkDao {

    @Insert
    suspend fun insert(addedDrink: AddedDrink)

    @Update
    suspend fun update(addedDrink: AddedDrink)

    @Query("DELETE FROM added_drink_table WHERE drink =:drink")
    suspend fun delete(drink: Drink)

    @Query("SELECT * FROM added_drink_table")
    fun getAllLive(): LiveData<List<AddedDrink>>

    @Query("SELECT * FROM added_drink_table")
    suspend fun getAll(): List<AddedDrink>

    @Query("DELETE FROM added_drink_table")
    suspend fun deleteAll()
}