package com.sameershelar.bloodalcoholcalculator.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.data.Gender.FEMALE
import com.sameershelar.bloodalcoholcalculator.data.Gender.MALE
import com.sameershelar.bloodalcoholcalculator.data.dao.DrinkDao
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink

val <T> T.exhaustive: T
    get() = this

fun getGenderConstant(gender: Gender): Double = when (gender) {
    MALE -> 0.68
    FEMALE -> 0.55
}.exhaustive

fun inPercent(number: Double) = String.format("%.4f%%", number)

fun NavController.navigateSafe(
    navDirections: NavDirections? = null
) {
    try {
        navDirections?.let {
            this.navigate(navDirections)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

suspend fun addDrinksData(dao: DrinkDao) {
    //Adding Beer's
    dao.insert(
        Drink(
            name = "Tuborg Strong",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Corona Extra",
            type = Constants.DrinkType.BEER,
            volume = 355,
            abv = 4.6
        )
    )
    dao.insert(
        Drink(
            name = "Kingfisher Strong",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 8.0
        )
    )
    dao.insert(Drink(name = "LP Strong", type = Constants.DrinkType.BEER, volume = 650, abv = 8.0))
    dao.insert(
        Drink(
            name = "Budweiser Premium",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 5.0
        )
    )
    dao.insert(
        Drink(
            name = "Carlsberg Smooth",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 4.5
        )
    )
    dao.insert(
        Drink(
            name = "Carlsberg Elephant",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 7.2
        )
    )
    dao.insert(
        Drink(
            name = "Bira Blonde",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 4.5
        )
    )
    dao.insert(Drink(name = "Bira White", type = Constants.DrinkType.BEER, volume = 650, abv = 4.7))
    dao.insert(
        Drink(
            name = "Foster's Lager",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 4.0
        )
    )
    dao.insert(
        Drink(
            name = "Heineken Lager",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 5.0
        )
    )
    dao.insert(
        Drink(
            name = "Budweiser Magnum",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Tuborg Classic",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 6.7
        )
    )
    dao.insert(
        Drink(
            name = "Tuborg Green",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 4.6
        )
    )
    dao.insert(
        Drink(
            name = "Miller High Life",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 4.6
        )
    )
    dao.insert(
        Drink(
            name = "Miller Ace Strong",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Royal Challenge Premium Lager",
            type = Constants.DrinkType.BEER,
            volume = 650,
            abv = 5.0
        )
    )
    dao.insert(Drink(name = "Beck’s Ice", type = Constants.DrinkType.BEER, volume = 650, abv = 8.0))

    dao.insert(
        Drink(
            name = "Tuborg Strong Pint/Can",
            type = Constants.DrinkType.BEER,
            volume = 330,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Tuborg Strong Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Kingfisher Strong Pint/Can",
            type = Constants.DrinkType.BEER,
            volume = 330,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Kingfisher Strong Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "LP Strong Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Budweiser Premium Pint/Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 5.0
        )
    )
    dao.insert(
        Drink(
            name = "Carlsberg Elephant",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 7.2
        )
    )
    dao.insert(
        Drink(
            name = "Carlsberg Smooth",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 4.5
        )
    )
    dao.insert(
        Drink(
            name = "Bira Blonde",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 4.5
        )
    )
    dao.insert(Drink(name = "Bira White", type = Constants.DrinkType.BEER, volume = 500, abv = 4.7))
    dao.insert(
        Drink(
            name = "Foster's Lager",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 4.0
        )
    )
    dao.insert(
        Drink(
            name = "Heineken Lager",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 5.0
        )
    )
    dao.insert(
        Drink(
            name = "Budweiser Magnum Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Tuborg Classic Pint/Can",
            type = Constants.DrinkType.BEER,
            volume = 330,
            abv = 6.7
        )
    )
    dao.insert(
        Drink(
            name = "Tuborg Classic Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 6.7
        )
    )
    dao.insert(
        Drink(
            name = "Tuborg Green Pint/Can",
            type = Constants.DrinkType.BEER,
            volume = 330,
            abv = 4.6
        )
    )
    dao.insert(
        Drink(
            name = "Tuborg Green Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 4.6
        )
    )
    dao.insert(
        Drink(
            name = "Miller High Life",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 4.6
        )
    )
    dao.insert(
        Drink(
            name = "Miller Ace Strong",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 8.0
        )
    )
    dao.insert(
        Drink(
            name = "Corona Light",
            type = Constants.DrinkType.BEER,
            volume = 355,
            abv = 4.1
        )
    )
    dao.insert(Drink(name = "Hoegaarden", type = Constants.DrinkType.BEER, volume = 330, abv = 4.9))
    dao.insert(
        Drink(
            name = "Royal Challenge Premium Lager",
            type = Constants.DrinkType.BEER,
            volume = 330,
            abv = 4.9
        )
    )
    dao.insert(
        Drink(
            name = "Beck’s Ice Can",
            type = Constants.DrinkType.BEER,
            volume = 500,
            abv = 8.0
        )
    )


    //Adding Whisky's
    dao.insert(
        Drink(
            name = "Royal Stag",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 48.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Stag",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 48.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Stag",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 48.8
        )
    )
    dao.insert(
        Drink(
            name = "Officer's Choice",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Officer's Choice",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Officer's Choice",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blenders Pride",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blenders Pride",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blenders Pride",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Imperial Blue",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Imperial Blue",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Imperial Blue",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Challenge United Spirit",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Challenge United Spirit",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Challenge United Spirit",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Dewars 18",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Dewars 18",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Dewars 18",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Black Dog Reserve",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Black Dog Reserve",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Black Dog Reserve",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Glenkinchie 12",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenkinchie 12",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenkinchie 12",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenmorangie 10-Year Original",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenmorangie 10-Year Original",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenmorangie 10-Year Original",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenfiddich 12",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenfiddich 12",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Glenfiddich 12",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "The Glenlivet",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "The Glenlivet",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "The Glenlivet",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ballantine's 12-Year",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ballantine's 12-Year",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ballantine's 12-Year",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Talisker 10",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 45.8
        )
    )
    dao.insert(
        Drink(
            name = "Talisker 10",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 45.8
        )
    )
    dao.insert(
        Drink(
            name = "Talisker 10",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 45.8
        )
    )
    dao.insert(
        Drink(
            name = "Jack Daniel’s Old No.7",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Jack Daniel’s Old No.7",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Jack Daniel’s Old No.7",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Jim Beam Bourbon Black",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Jim Beam Bourbon Black",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Jim Beam Bourbon Black",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Monkey Shoulder",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Monkey Shoulder",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Monkey Shoulder",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 43.0
        )
    )
    dao.insert(
        Drink(
            name = "Amrut Fusion Single Malt Whisky",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 50.0
        )
    )
    dao.insert(
        Drink(
            name = "Amrut Fusion Single Malt Whisky",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 50.0
        )
    )
    dao.insert(
        Drink(
            name = "Amrut Fusion Single Malt Whisky",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 50.0
        )
    )
    dao.insert(
        Drink(
            name = "Chivas Regal 12 Years",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Chivas Regal 12 Years",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Chivas Regal 12 Years",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Jameson", type = Constants.DrinkType.WHISKY, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Jameson", type = Constants.DrinkType.WHISKY, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Jameson", type = Constants.DrinkType.WHISKY, volume = 180, abv = 40.0))
    dao.insert(
        Drink(
            name = "Johnnie Walker Red Label",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Johnnie Walker Red Label",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Johnnie Walker Red Label",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Black & White",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Black & White",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Black & White",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Seagrams 100 Pipers Deluxe",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Seagrams 100 Pipers Deluxe",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Seagrams 100 Pipers Deluxe",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Teacher's Highland Cream",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Teacher's Highland Cream",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Teacher's Highland Cream",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Vat 69", type = Constants.DrinkType.WHISKY, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Vat 69", type = Constants.DrinkType.WHISKY, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Vat 69", type = Constants.DrinkType.WHISKY, volume = 180, abv = 40.0))
    dao.insert(
        Drink(
            name = "McDowell's No.1 Platinum",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "McDowell's No.1 Platinum",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "McDowell's No.1 Platinum",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Blender's Pride",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blender's Pride",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blender's Pride",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Challenge",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Royal Challenge",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Royal Challenge",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Royal Stag",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Stag",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Royal Stag",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Director's Special",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Director's Special",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Director's Special",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "Bagpiper", type = Constants.DrinkType.WHISKY, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Bagpiper", type = Constants.DrinkType.WHISKY, volume = 90, abv = 42.8))
    dao.insert(
        Drink(
            name = "Bagpiper",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "8 PM", type = Constants.DrinkType.WHISKY, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "8 PM", type = Constants.DrinkType.WHISKY, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "8 PM", type = Constants.DrinkType.WHISKY, volume = 180, abv = 42.8))
    dao.insert(
        Drink(
            name = "Old Tavern",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Old Tavern",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Old Tavern",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Original Choice",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Original Choice",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Original Choice",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blenders Pride Reserve",
            type = Constants.DrinkType.WHISKY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blenders Pride Reserve",
            type = Constants.DrinkType.WHISKY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Blenders Pride Reserve",
            type = Constants.DrinkType.WHISKY,
            volume = 180,
            abv = 42.8
        )
    )


    //Adding Vodka's
    dao.insert(
        Drink(
            name = "Grey Goose",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Grey Goose",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Grey Goose",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Absolut", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Absolut", type = Constants.DrinkType.VODKA, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Absolut", type = Constants.DrinkType.VODKA, volume = 180, abv = 40.0))
    dao.insert(
        Drink(
            name = "Smirnoff No-21",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Smirnoff No-21",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Smirnoff No-21",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Magic Moments",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Magic Moments",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Magic Moments",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Romanov", type = Constants.DrinkType.VODKA, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Romanov", type = Constants.DrinkType.VODKA, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "Romanov", type = Constants.DrinkType.VODKA, volume = 180, abv = 42.8))
    dao.insert(
        Drink(
            name = "White Mischief",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "White Mischief",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "White Mischief",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "Fuel", type = Constants.DrinkType.VODKA, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Fuel", type = Constants.DrinkType.VODKA, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "Fuel", type = Constants.DrinkType.VODKA, volume = 180, abv = 42.8))
    dao.insert(Drink(name = "Vladivar", type = Constants.DrinkType.VODKA, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Vladivar", type = Constants.DrinkType.VODKA, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "Vladivar", type = Constants.DrinkType.VODKA, volume = 180, abv = 42.8))
    dao.insert(Drink(name = "Eristoff", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Eristoff", type = Constants.DrinkType.VODKA, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Eristoff", type = Constants.DrinkType.VODKA, volume = 180, abv = 40.0))
    dao.insert(
        Drink(
            name = "Wodka Gorbatschow",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Wodka Gorbatschow",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Wodka Gorbatschow",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "Belvedere", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Belvedere", type = Constants.DrinkType.VODKA, volume = 90, abv = 40.0))
    dao.insert(
        Drink(
            name = "Belvedere",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Red Bliss", type = Constants.DrinkType.VODKA, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Red Bliss", type = Constants.DrinkType.VODKA, volume = 90, abv = 42.8))
    dao.insert(
        Drink(
            name = "Red Bliss",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Wyborowa (Pernod Ricard)",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Wyborowa (Pernod Ricard)",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Wyborowa (Pernod Ricard)",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Stolichnaya",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Stolichnaya",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Stolichnaya",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Ketel One", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Ketel One", type = Constants.DrinkType.VODKA, volume = 90, abv = 40.0))
    dao.insert(
        Drink(
            name = "Ketel One",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Beluga", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Beluga", type = Constants.DrinkType.VODKA, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Beluga", type = Constants.DrinkType.VODKA, volume = 180, abv = 40.0))
    dao.insert(Drink(name = "Ciroc", type = Constants.DrinkType.VODKA, volume = 60, abv = 35.0))
    dao.insert(Drink(name = "Ciroc", type = Constants.DrinkType.VODKA, volume = 90, abv = 35.0))
    dao.insert(Drink(name = "Ciroc", type = Constants.DrinkType.VODKA, volume = 180, abv = 35.0))
    dao.insert(Drink(name = "Finlandia", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Finlandia", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(
        Drink(
            name = "Finlandia",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Icelandic Reyka",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Icelandic Reyka",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Icelandic Reyka",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Skyy", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Skyy", type = Constants.DrinkType.VODKA, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Skyy", type = Constants.DrinkType.VODKA, volume = 180, abv = 40.0))
    dao.insert(Drink(name = "42 Below", type = Constants.DrinkType.VODKA, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "42 Below", type = Constants.DrinkType.VODKA, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "42 Below", type = Constants.DrinkType.VODKA, volume = 180, abv = 40.0))
    dao.insert(Drink(name = "Artic", type = Constants.DrinkType.VODKA, volume = 60, abv = 38.0))
    dao.insert(Drink(name = "Artic", type = Constants.DrinkType.VODKA, volume = 90, abv = 38.0))
    dao.insert(Drink(name = "Artic", type = Constants.DrinkType.VODKA, volume = 180, abv = 38.0))
    dao.insert(
        Drink(
            name = "Russkiy Parliament",
            type = Constants.DrinkType.VODKA,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Russkiy Parliament",
            type = Constants.DrinkType.VODKA,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Russkiy Parliament",
            type = Constants.DrinkType.VODKA,
            volume = 180,
            abv = 40.0
        )
    )


    //Adding Rum's
    dao.insert(Drink(name = "Bacardi", type = Constants.DrinkType.RUM, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Bacardi", type = Constants.DrinkType.RUM, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "Bacardi", type = Constants.DrinkType.RUM, volume = 180, abv = 42.8))
    dao.insert(Drink(name = "Malibu", type = Constants.DrinkType.RUM, volume = 60, abv = 21.0))
    dao.insert(Drink(name = "Malibu", type = Constants.DrinkType.RUM, volume = 90, abv = 21.0))
    dao.insert(Drink(name = "Malibu", type = Constants.DrinkType.RUM, volume = 180, abv = 21.0))
    dao.insert(
        Drink(
            name = "Captain Morgan",
            type = Constants.DrinkType.RUM,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Captain Morgan",
            type = Constants.DrinkType.RUM,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Captain Morgan",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "Havana Club", type = Constants.DrinkType.RUM, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Havana Club", type = Constants.DrinkType.RUM, volume = 90, abv = 40.0))
    dao.insert(
        Drink(
            name = "Havana Club",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Hercules", type = Constants.DrinkType.RUM, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Hercules", type = Constants.DrinkType.RUM, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "Hercules", type = Constants.DrinkType.RUM, volume = 180, abv = 42.8))
    dao.insert(
        Drink(
            name = "McDowell’s №1 Rum",
            type = Constants.DrinkType.RUM,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "McDowell’s №1 Rum",
            type = Constants.DrinkType.RUM,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "McDowell’s №1 Rum",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "Jolly Roger", type = Constants.DrinkType.RUM, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Jolly Roger", type = Constants.DrinkType.RUM, volume = 90, abv = 42.8))
    dao.insert(
        Drink(
            name = "Jolly Roger",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "Contessa", type = Constants.DrinkType.RUM, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Contessa", type = Constants.DrinkType.RUM, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "Contessa", type = Constants.DrinkType.RUM, volume = 180, abv = 42.8))
    dao.insert(Drink(name = "Old Port", type = Constants.DrinkType.RUM, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Old Port", type = Constants.DrinkType.RUM, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Old Port", type = Constants.DrinkType.RUM, volume = 180, abv = 40.0))
    dao.insert(
        Drink(
            name = "Sailor Jerry",
            type = Constants.DrinkType.RUM,
            volume = 60,
            abv = 46.0
        )
    )
    dao.insert(
        Drink(
            name = "Sailor Jerry",
            type = Constants.DrinkType.RUM,
            volume = 90,
            abv = 46.0
        )
    )
    dao.insert(
        Drink(
            name = "Sailor Jerry",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 46.0
        )
    )
    dao.insert(Drink(name = "El Dorado", type = Constants.DrinkType.RUM, volume = 60, abv = 43.0))
    dao.insert(Drink(name = "El Dorado", type = Constants.DrinkType.RUM, volume = 90, abv = 43.0))
    dao.insert(Drink(name = "El Dorado", type = Constants.DrinkType.RUM, volume = 180, abv = 43.0))
    dao.insert(
        Drink(
            name = "Ron Matusalem",
            type = Constants.DrinkType.RUM,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ron Matusalem",
            type = Constants.DrinkType.RUM,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ron Matusalem",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ron Zacapa Centenario",
            type = Constants.DrinkType.RUM,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ron Zacapa Centenario",
            type = Constants.DrinkType.RUM,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Ron Zacapa Centenario",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Mount Gay", type = Constants.DrinkType.RUM, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Mount Gay", type = Constants.DrinkType.RUM, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Mount Gay", type = Constants.DrinkType.RUM, volume = 180, abv = 40.0))
    dao.insert(
        Drink(
            name = "Appleton Estate",
            type = Constants.DrinkType.RUM,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Appleton Estate",
            type = Constants.DrinkType.RUM,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Appleton Estate",
            type = Constants.DrinkType.RUM,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Angostura", type = Constants.DrinkType.RUM, volume = 60, abv = 44.7))
    dao.insert(Drink(name = "Angostura", type = Constants.DrinkType.RUM, volume = 90, abv = 44.7))
    dao.insert(Drink(name = "Angostura", type = Constants.DrinkType.RUM, volume = 180, abv = 44.7))
    dao.insert(Drink(name = "Barcelo", type = Constants.DrinkType.RUM, volume = 60, abv = 38.0))
    dao.insert(Drink(name = "Barcelo", type = Constants.DrinkType.RUM, volume = 90, abv = 38.0))
    dao.insert(Drink(name = "Barcelo", type = Constants.DrinkType.RUM, volume = 180, abv = 38.0))
    dao.insert(Drink(name = "Old Monk", type = Constants.DrinkType.RUM, volume = 60, abv = 42.8))
    dao.insert(Drink(name = "Old Monk", type = Constants.DrinkType.RUM, volume = 90, abv = 42.8))
    dao.insert(Drink(name = "Old Monk", type = Constants.DrinkType.RUM, volume = 180, abv = 42.8))
    dao.insert(Drink(name = "Clement", type = Constants.DrinkType.RUM, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Clement", type = Constants.DrinkType.RUM, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Clement", type = Constants.DrinkType.RUM, volume = 180, abv = 40.0))
    dao.insert(Drink(name = "Goslings", type = Constants.DrinkType.RUM, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Goslings", type = Constants.DrinkType.RUM, volume = 90, abv = 40.0))
    dao.insert(Drink(name = "Goslings", type = Constants.DrinkType.RUM, volume = 180, abv = 40.0))


    //Adding Wines
    dao.insert(
        Drink(
            name = "Sula Rasa Shiraz (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.0
        )
    )
    dao.insert(
        Drink(
            name = "Sula Rasa Shiraz",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 13.0
        )
    )
    dao.insert(
        Drink(
            name = "Myra Misfit (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.0
        )
    )
    dao.insert(
        Drink(
            name = "Myra Misfit",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 13.0
        )
    )
    dao.insert(
        Drink(
            name = "Fratelli SETTE (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "Fratelli SETTE",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "Grover La Reserve (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "Grover La Reserve",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "YORK Arros (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 14.3
        )
    )
    dao.insert(
        Drink(
            name = "YORK Arros",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 14.3
        )
    )
    dao.insert(
        Drink(
            name = "KRSMA Sangiovese (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.6
        )
    )
    dao.insert(
        Drink(
            name = "KRSMA Sangiovese",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 13.6
        )
    )
    dao.insert(
        Drink(
            name = "Charosa (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.0
        )
    )
    dao.insert(Drink(name = "Charosa", type = Constants.DrinkType.WINE, volume = 750, abv = 13.0))
    dao.insert(
        Drink(
            name = "J'NOON White (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "J'NOON White",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "Belstar Cult Prosecco (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 11.0
        )
    )
    dao.insert(
        Drink(
            name = "Belstar Cult Prosecco",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 11.0
        )
    )
    dao.insert(
        Drink(
            name = "Crios Torrontes (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "Crios Torrontes",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 13.5
        )
    )
    dao.insert(
        Drink(
            name = "Riesling (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 12.2
        )
    )
    dao.insert(Drink(name = "Riesling", type = Constants.DrinkType.WINE, volume = 750, abv = 12.2))
    dao.insert(
        Drink(
            name = "Sula Chenin Blanc (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 12.5
        )
    )
    dao.insert(
        Drink(
            name = "Sula Chenin Blanc",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 12.5
        )
    )
    dao.insert(
        Drink(
            name = "Zampa Soiree Brut (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 12.0
        )
    )
    dao.insert(
        Drink(
            name = "Zampa Soiree Brut",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 12.0
        )
    )
    dao.insert(
        Drink(
            name = "Carpene Malvolti Prosecco (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 11.0
        )
    )
    dao.insert(
        Drink(
            name = "Carpene Malvolti Prosecco",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 11.0
        )
    )
    dao.insert(
        Drink(
            name = "Les Cordeliers (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 11.0
        )
    )
    dao.insert(
        Drink(
            name = "Les Cordeliers",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 11.0
        )
    )
    dao.insert(
        Drink(
            name = "Port Wine 1000 (1 glass)",
            type = Constants.DrinkType.WINE,
            volume = 150,
            abv = 14.0
        )
    )
    dao.insert(
        Drink(
            name = "Port Wine 1000",
            type = Constants.DrinkType.WINE,
            volume = 750,
            abv = 14.0
        )
    )


    //Adding Gin's
    dao.insert(
        Drink(
            name = "Bombay Sapphire",
            type = Constants.DrinkType.GIN,
            volume = 45,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Botanist", type = Constants.DrinkType.GIN, volume = 45, abv = 46.0))
    dao.insert(Drink(name = "Hapusa", type = Constants.DrinkType.GIN, volume = 45, abv = 43.0))
    dao.insert(Drink(name = "Tanqueray", type = Constants.DrinkType.GIN, volume = 45, abv = 43.1))
    dao.insert(Drink(name = "Hendricks", type = Constants.DrinkType.GIN, volume = 45, abv = 44.0))
    dao.insert(
        Drink(
            name = "Greater Than",
            type = Constants.DrinkType.GIN,
            volume = 45,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Jaisalmer Indian Craft Gin",
            type = Constants.DrinkType.GIN,
            volume = 45,
            abv = 43.0
        )
    )
    dao.insert(Drink(name = "Gin Mare", type = Constants.DrinkType.GIN, volume = 45, abv = 42.7))
    dao.insert(Drink(name = "Magellan", type = Constants.DrinkType.GIN, volume = 45, abv = 41.3))
    dao.insert(Drink(name = "Beefeater", type = Constants.DrinkType.GIN, volume = 45, abv = 40.0))
    dao.insert(
        Drink(
            name = "Stranger & Sons",
            type = Constants.DrinkType.GIN,
            volume = 45,
            abv = 42.8
        )
    )
    dao.insert(Drink(name = "Samsara", type = Constants.DrinkType.GIN, volume = 45, abv = 42.8))


    //Adding Brandies
    dao.insert(
        Drink(
            name = "Mc Dowell's No 1 Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 42.0
        )
    )
    dao.insert(
        Drink(
            name = "Mc Dowell's No 1 Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 42.0
        )
    )
    dao.insert(
        Drink(
            name = "Mc Dowell's No 1 Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 42.0
        )
    )
    dao.insert(
        Drink(
            name = "Honey Bee",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 42.0
        )
    )
    dao.insert(
        Drink(
            name = "Honey Bee",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 42.0
        )
    )
    dao.insert(
        Drink(
            name = "Honey Bee",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 42.0
        )
    )
    dao.insert(
        Drink(
            name = "Remy Martin",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Remy Martin",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Remy Martin",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Hennesey", type = Constants.DrinkType.BRANDY, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Hennesey", type = Constants.DrinkType.BRANDY, volume = 90, abv = 40.0))
    dao.insert(
        Drink(
            name = "Hennesey",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(Drink(name = "Morpheus", type = Constants.DrinkType.BRANDY, volume = 60, abv = 40.0))
    dao.insert(Drink(name = "Morpheus", type = Constants.DrinkType.BRANDY, volume = 90, abv = 40.0))
    dao.insert(
        Drink(
            name = "Morpheus",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Old Admiral",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Old Admiral",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Old Admiral",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Courrier Napoleon",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Courrier Napoleon",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Courrier Napoleon",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Mansion House Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Mansion House Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Mansion House Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Golden Grape Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Golden Grape Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Golden Grape Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 40.0
        )
    )
    dao.insert(
        Drink(
            name = "Doctor Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 60,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Doctor Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 90,
            abv = 42.8
        )
    )
    dao.insert(
        Drink(
            name = "Doctor Brandy",
            type = Constants.DrinkType.BRANDY,
            volume = 180,
            abv = 42.8
        )
    )
}
