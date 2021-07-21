package com.sameershelar.bloodalcoholcalculator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sameershelar.bloodalcoholcalculator.DrinkDao
import com.sameershelar.bloodalcoholcalculator.di.AppModule.ApplicationScope
import com.sameershelar.bloodalcoholcalculator.utils.Constants.DrinkType.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Drink::class], version = 1, exportSchema = false)
abstract class BACDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

    class Callback @Inject constructor(
        private val database: Provider<BACDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    )  : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().drinkDao()

            applicationScope.launch {
                //Adding Beer's
                dao.insert(Drink(name = "Tuborg Strong", type = BEER, volume = 650, abv = 8.0))
                dao.insert(Drink(name = "Kingfisher Strong", type = BEER, volume = 650, abv = 8.0))
                dao.insert(Drink(name = "LP Strong", type = BEER, volume = 650, abv = 8.0))
                dao.insert(Drink(name = "Budweiser Strong", type = BEER, volume = 650, abv = 8.0))

                //Adding Whisky's
                dao.insert(Drink(name = "Royal Stag", type = WHISKY, volume = 180, abv = 48.8))
                dao.insert(Drink(name = "Officer's Choice", type = WHISKY, volume = 180, abv = 42.8))
                dao.insert(Drink(name = "Blenders Pride", type = WHISKY, volume = 180, abv = 42.8))
                dao.insert(Drink(name = "Imperial Blue", type = WHISKY, volume = 180, abv = 42.8))
                dao.insert(Drink(name = "Royal Challenge United Spirit", type = WHISKY, volume = 180, abv = 42.8))

                //Adding Vodka's
                dao.insert(Drink(name = "Grey Goose", type = VODKA, volume = 180, abv = 40.0))
                dao.insert(Drink(name = "Absolut", type = VODKA, volume = 180, abv = 40.0))
                dao.insert(Drink(name = "Smirnoff No-21", type = VODKA, volume = 180, abv = 40.0))
                dao.insert(Drink(name = "Magic Moments", type = VODKA, volume = 180, abv = 40.0))
                dao.insert(Drink(name = "Romanov", type = VODKA, volume = 180, abv = 42.8))
                dao.insert(Drink(name = "White Mischief", type = VODKA, volume = 180, abv = 42.8))
            }
        }
    }
}