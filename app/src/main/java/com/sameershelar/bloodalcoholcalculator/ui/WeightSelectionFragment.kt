package com.sameershelar.bloodalcoholcalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentWeightSelectionBinding
import com.sameershelar.bloodalcoholcalculator.vm.WeightSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class WeightSelectionFragment : Fragment() {

    private lateinit var binding: FragmentWeightSelectionBinding
    private val viewModel: WeightSelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeightSelectionBinding.inflate(layoutInflater)

        runBlocking {
            val pref = viewModel.getPreferencesFlow()
            if (pref.isPreferencesSelected) {
                findNavController().navigate(WeightSelectionFragmentDirections.actionWeightSelectionFragmentToBACCalculatorFragment())
            }
        }

        binding.apply {
            weightPicker.minValue = 20
            weightPicker.maxValue = 200

            weightPicker.setOnValueChangedListener { _, _, newVal ->
                viewModel.onWeightSelected(newVal)
            }

            nextButton.setOnClickListener {
                findNavController().navigate(WeightSelectionFragmentDirections.actionWeightSelectionFragmentToGenderSelectionFragment2())
            }
        }

        return binding.root
    }
}