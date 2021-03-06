package com.sameershelar.bloodalcoholcalculator.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.*
import androidx.lifecycle.asLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

enum class Gender { MALE, FEMALE }

private const val TAG = "PreferencesManager"

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.createDataStore("user_preferences")

    data class PreferencesData(
        var weight: Int,
        var gender: Gender,
        var isPreferencesSelected: Boolean,
    )

    val preferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences", exception)
                emit(emptyPreferences())
            } else {
                throw exception;
            }
        }
        .map { preferences ->
            val weight = preferences[PreferencesKeys.WEIGHT] ?: 20
            val gender = Gender.valueOf(
                preferences[PreferencesKeys.GENDER] ?: Gender.MALE.name
            )
            val isPreferencesSelected = preferences[PreferencesKeys.IS_PREFERENCES_SELECTED] ?: false

            PreferencesData(weight, gender, isPreferencesSelected)
        }

    suspend fun getWeight() = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.WEIGHT] ?: 20
    }.first()

    suspend fun getGender() = dataStore.data.map { preferences ->
        Gender.valueOf(
            preferences[PreferencesKeys.GENDER] ?: Gender.MALE.name
        )
    }.first()

    suspend fun updateGender(gender: Gender) {
        dataStore.edit {
            it[PreferencesKeys.GENDER] = gender.name
        }
    }

    suspend fun updateWeight(weight: Int) {
        dataStore.edit {
            it[PreferencesKeys.WEIGHT] = weight
        }
    }

    suspend fun setPreferenceSelected(isPreferencesSelected: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.IS_PREFERENCES_SELECTED] = isPreferencesSelected
        }
    }

    private object PreferencesKeys {
        val GENDER = preferencesKey<String>("gender")
        val WEIGHT = preferencesKey<Int>("weight")
        val IS_PREFERENCES_SELECTED = preferencesKey<Boolean>("is_preferences_selected")
    }
}