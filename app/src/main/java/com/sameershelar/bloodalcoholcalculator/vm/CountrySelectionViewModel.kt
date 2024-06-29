package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountrySelectionViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    fun onCountrySelected(country: String) = viewModelScope.launch {
        preferencesManager.updateCountry(country)
    }

    fun onBacLimitSelected(bacLimit: Float) = viewModelScope.launch {
        preferencesManager.updateBacLimit(bacLimit)
    }
}