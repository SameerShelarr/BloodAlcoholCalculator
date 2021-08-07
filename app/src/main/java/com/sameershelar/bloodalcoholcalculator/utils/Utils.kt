package com.sameershelar.bloodalcoholcalculator.utils

import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.data.Gender.FEMALE
import com.sameershelar.bloodalcoholcalculator.data.Gender.MALE

val <T> T.exhaustive: T
    get() = this

fun getGenderConstant(gender: Gender): Double = when (gender) {
    MALE -> 0.55
    FEMALE -> 0.68
}.exhaustive

fun inPercent(number: Double) = String.format("%.4f%%", number)
