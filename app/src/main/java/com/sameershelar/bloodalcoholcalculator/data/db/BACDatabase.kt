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
                //Adding Beer's
                dao.insert(Drink(name = "Tuborg Strong", type = BEER, volume = 650, abv = 8.0))
                dao.insert(Drink(name = "Corona Extra", type = BEER, volume = 355, abv = 4.6))
                dao.insert(Drink(name = "Kingfisher Strong", type = BEER, volume = 650, abv = 8.0))
                dao.insert(Drink(name = "LP Strong", type = BEER, volume = 650, abv = 8.0))
                dao.insert(Drink(name = "Budweiser Premium", type = BEER, volume = 650, abv = 5.0))
                dao.insert(Drink(name = "Carlsberg Smooth", type = BEER, volume = 650, abv = 4.5))
                dao.insert(Drink(name = "Carlsberg Elephant", type = BEER, volume = 650, abv = 7.2))
                dao.insert(Drink(name = "Bira Blonde", type = BEER, volume = 650, abv = 4.5))
                dao.insert(Drink(name = "Bira White", type = BEER, volume = 650, abv = 4.7))
                dao.insert(Drink(name = "Foster's Lager", type = BEER, volume = 650, abv = 4.0))
                dao.insert(Drink(name = "Heineken Lager", type = BEER, volume = 650, abv = 5.0))
                dao.insert(Drink(name = "Budweiser Magnum", type = BEER, volume = 650, abv = 8.0))
                dao.insert(Drink(name = "Tuborg Classic", type = BEER, volume = 650, abv = 6.7))
                dao.insert(Drink(name = "Tuborg Green", type = BEER, volume = 650, abv = 4.6))
                dao.insert(Drink(name = "Miller High Life", type = BEER, volume = 650, abv = 4.6))
                dao.insert(Drink(name = "Miller Ace Strong", type = BEER, volume = 650, abv = 8.0))
                dao.insert(
                    Drink(
                        name = "Royal Challenge Premium Lager",
                        type = BEER,
                        volume = 650,
                        abv = 5.0
                    )
                )
                dao.insert(Drink(name = "Beck’s Ice", type = BEER, volume = 650, abv = 8.0))

                dao.insert(
                    Drink(
                        name = "Tuborg Strong Pint/Can",
                        type = BEER,
                        volume = 330,
                        abv = 8.0
                    )
                )
                dao.insert(Drink(name = "Tuborg Strong Can", type = BEER, volume = 500, abv = 8.0))
                dao.insert(
                    Drink(
                        name = "Kingfisher Strong Pint/Can",
                        type = BEER,
                        volume = 330,
                        abv = 8.0
                    )
                )
                dao.insert(
                    Drink(
                        name = "Kingfisher Strong Can",
                        type = BEER,
                        volume = 500,
                        abv = 8.0
                    )
                )
                dao.insert(Drink(name = "LP Strong Can", type = BEER, volume = 500, abv = 8.0))
                dao.insert(
                    Drink(
                        name = "Budweiser Premium Pint/Can",
                        type = BEER,
                        volume = 500,
                        abv = 5.0
                    )
                )
                dao.insert(Drink(name = "Carlsberg Elephant", type = BEER, volume = 500, abv = 7.2))
                dao.insert(Drink(name = "Carlsberg Smooth", type = BEER, volume = 500, abv = 4.5))
                dao.insert(Drink(name = "Bira Blonde", type = BEER, volume = 500, abv = 4.5))
                dao.insert(Drink(name = "Bira White", type = BEER, volume = 500, abv = 4.7))
                dao.insert(Drink(name = "Foster's Lager", type = BEER, volume = 500, abv = 4.0))
                dao.insert(Drink(name = "Heineken Lager", type = BEER, volume = 500, abv = 5.0))
                dao.insert(
                    Drink(
                        name = "Budweiser Magnum Can",
                        type = BEER,
                        volume = 500,
                        abv = 8.0
                    )
                )
                dao.insert(
                    Drink(
                        name = "Tuborg Classic Pint/Can",
                        type = BEER,
                        volume = 330,
                        abv = 6.7
                    )
                )
                dao.insert(Drink(name = "Tuborg Classic Can", type = BEER, volume = 500, abv = 6.7))
                dao.insert(
                    Drink(
                        name = "Tuborg Green Pint/Can",
                        type = BEER,
                        volume = 330,
                        abv = 4.6
                    )
                )
                dao.insert(Drink(name = "Tuborg Green Can", type = BEER, volume = 500, abv = 4.6))
                dao.insert(Drink(name = "Miller High Life", type = BEER, volume = 500, abv = 4.6))
                dao.insert(Drink(name = "Miller Ace Strong", type = BEER, volume = 500, abv = 8.0))
                dao.insert(Drink(name = "Corona Light", type = BEER, volume = 355, abv = 4.1))
                dao.insert(Drink(name = "Hoegaarden", type = BEER, volume = 330, abv = 4.9))
                dao.insert(
                    Drink(
                        name = "Royal Challenge Premium Lager",
                        type = BEER,
                        volume = 330,
                        abv = 4.9
                    )
                )
                dao.insert(Drink(name = "Beck’s Ice Can", type = BEER, volume = 500, abv = 8.0))


                //Adding Whisky's
                dao.insert(Drink(name = "Royal Stag", type = WHISKY, volume = 180, abv = 48.8))
                dao.insert(
                    Drink(
                        name = "Officer's Choice",
                        type = WHISKY,
                        volume = 180,
                        abv = 42.8
                    )
                )
                dao.insert(Drink(name = "Blenders Pride", type = WHISKY, volume = 180, abv = 42.8))
                dao.insert(Drink(name = "Imperial Blue", type = WHISKY, volume = 180, abv = 42.8))
                dao.insert(
                    Drink(
                        name = "Royal Challenge United Spirit",
                        type = WHISKY,
                        volume = 180,
                        abv = 42.8
                    )
                )


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