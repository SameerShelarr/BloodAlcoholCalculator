package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightSelectionViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    fun onWeightSelected(weight: Int) = viewModelScope.launch {
        preferencesManager.updateWeight(weight)
    }

    suspend fun getPreferencesFlow() = preferencesManager.preferencesFlow.first()
}