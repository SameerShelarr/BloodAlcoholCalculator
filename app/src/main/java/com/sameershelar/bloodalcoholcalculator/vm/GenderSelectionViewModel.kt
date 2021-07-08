package com.sameershelar.bloodalcoholcalculator.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderSelectionViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val state: SavedStateHandle,
) : ViewModel() {
    fun onGenderSelected(gender: Gender) = viewModelScope.launch {
        preferencesManager.updateGender(gender)
    }
}