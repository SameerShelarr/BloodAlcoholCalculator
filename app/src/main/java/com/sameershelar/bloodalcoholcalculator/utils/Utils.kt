package com.sameershelar.bloodalcoholcalculator.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.data.Gender.FEMALE
import com.sameershelar.bloodalcoholcalculator.data.Gender.MALE

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
