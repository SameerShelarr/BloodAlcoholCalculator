package com.sameershelar.bloodalcoholcalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAddCustomDrinkDialogBinding

class AddCustomDrinkDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "AddCustomDrinkDialogFragment"
    }

    private lateinit var binding: FragmentAddCustomDrinkDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCustomDrinkDialogBinding.inflate(inflater)

        binding.apply {
            addNewDrinkButton.setOnClickListener {
                /*if (validateFields()) {
                    createNewDrinkAndAddInDb()
                }*/
            }
        }

        return binding.root
    }

    private fun createNewDrinkAndAddInDb() {
        TODO("Not yet implemented")
    }

    private fun validateFields(): Boolean {
        TODO("Not yet implemented")
    }
}