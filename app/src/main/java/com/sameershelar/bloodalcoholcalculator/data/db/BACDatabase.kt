package com.sameershelar.bloodalcoholcalculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sameershelar.bloodalcoholcalculator.data.converters.DrinkConverter
import com.sameershelar.bloodalcoholcalculator.data.dao.AddedDrinkDao
import com.sameershelar.bloodalcoholcalculator.data.dao.DrinkDao
import com.sameershelar.bloodalcoholcalculator.data.tables.AddedDrink
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink
import com.sameershelar.bloodalcoholcalculator.di.AppModule.ApplicationScope
import com.sameershelar.bloodalcoholcalculator.utils.Constants.DrinkType.*
import com.sameershelar.bloodalcoholcalculator.utils.addDrinksData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Drink::class, AddedDrink::class], version = 1, exportSchema = false)
@TypeConverters(DrinkConverter::class)
abstract class BACDatabase : RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
    abstract fun addedDrinkDao(): AddedDrinkDao

    class Callback @Inject constructor(
        private val database: Provider<BACDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().drinkDao()

            applicationScope.launch {
                addDrinksData(dao)
            }
        }
    }
}