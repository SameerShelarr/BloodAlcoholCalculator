package com.sameershelar.bloodalcoholcalculator.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAddCustomDrinkDialogBinding
import com.sameershelar.bloodalcoholcalculator.vm.AddCustomDrinkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCustomDrinkDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "AddCustomDrinkDialogFragment"
    }

    private lateinit var binding: FragmentAddCustomDrinkDialogBinding
    private val viewModel: AddCustomDrinkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCustomDrinkDialogBinding.inflate(inflater)

        binding.apply {
            addNewDrinkButton.setOnClickListener {
                if (validateFields()) {
                    createNewDrinkAndAddInDb()
                }
            }
        }

        return binding.root
    }

    private fun createNewDrinkAndAddInDb() {
        val drinkName = binding.drinkNameEdit.editText?.text.toString()
        val drinkVolume = binding.volumeEdit.editText?.text.toString().toInt()
        val abv = binding.abvEdit.editText?.text.toString().toDouble()
        viewModel.addCustomDrink(drinkName, drinkVolume, abv)
        dismiss()
    }

    private fun validateFields(): Boolean {
        val drinkName = binding.drinkNameEdit.editText?.text.toString()
        val drinkVolume = binding.volumeEdit.editText?.text.toString()
        val abv = binding.abvEdit.editText?.text.toString()

        //Validating empty text
        if (drinkName.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Name cannot be empty",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (drinkVolume.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Volume cannot be empty",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        if (abv.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Alcohol content cannot be empty",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        //Validating type and valid range
        try {
            val drinkVolumeInt = drinkVolume.toInt()
            if (drinkVolumeInt < 30 || drinkVolumeInt > 2000) {
                Toast.makeText(
                    requireContext(),
                    "Please enter valid volume",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        } catch (e: NumberFormatException) {
            Log.d(TAG, "Invalid drink volume: ${e.message}")
            Toast.makeText(
                requireContext(),
                "Please enter valid volume",
                Toast.LENGTH_SHORT
            ).show()
            return false;
        }
        try {
            val abvDouble = abv.toDouble()
            if (abvDouble <= 0 || abvDouble > 100) {
                Toast.makeText(
                    requireContext(),
                    "Please enter valid alcohol content",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        } catch (e: NumberFormatException) {
            Log.d(TAG, "Invalid alcohol content: ${e.message}")
            Toast.makeText(
                requireContext(),
                "Please enter valid alcohol content",
                Toast.LENGTH_SHORT
            ).show()
            return false;
        }

        return true;
    }
}