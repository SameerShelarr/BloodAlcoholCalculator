package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.DrinkDao
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BACalculatorViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    fun getPreferencesFlow() = preferencesManager.preferencesFlow.asLiveData()

    fun updateIsPreferencesSelected(isPreferencesSelected: Boolean) = viewModelScope.launch {
        preferencesManager.setPreferenceSelected(isPreferencesSelected)
    }
}