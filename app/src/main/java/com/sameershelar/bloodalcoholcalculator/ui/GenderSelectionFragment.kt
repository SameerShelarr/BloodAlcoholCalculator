package com.sameershelar.bloodalcoholcalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentGenderSelectionBinding
import com.sameershelar.bloodalcoholcalculator.vm.GenderSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenderSelectionFragment : Fragment() {

    private lateinit var binding: FragmentGenderSelectionBinding
    private val viewModel: GenderSelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenderSelectionBinding.inflate(layoutInflater)

        binding.apply {

            previousButton.setOnClickListener {
                findNavController().popBackStack()
            }

            nextButton.setOnClickListener {
                findNavController().navigate(GenderSelectionFragmentDirections.actionGenderSelectionFragmentToCountrySelectionFragment())
            }

            viewModel.onGenderSelected(Gender.FEMALE)

            toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.male_button -> {
                            viewModel.onGenderSelected(Gender.MALE)
                        }
                        R.id.female_button -> {
                            viewModel.onGenderSelected(Gender.FEMALE)
                        }
                    }
                }
            }
        }

        return binding.root
    }
}