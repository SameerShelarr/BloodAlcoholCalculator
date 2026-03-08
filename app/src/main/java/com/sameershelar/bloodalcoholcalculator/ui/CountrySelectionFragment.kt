package com.sameershelar.bloodalcoholcalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentCountrySelectionBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.legalLimits
import com.sameershelar.bloodalcoholcalculator.vm.CountrySelectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountrySelectionFragment : Fragment() {

    private lateinit var binding: FragmentCountrySelectionBinding
    private val viewModel: CountrySelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountrySelectionBinding.inflate(layoutInflater)

        binding.apply {
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.country_spinner_item,
                legalLimits.keys.sorted().toList()
            )
            val defaultCountry = "India"
            val dropDownTextView = (countrySpinner.editText as? AutoCompleteTextView)
            dropDownTextView?.setAdapter(adapter)
            dropDownTextView?.setText(defaultCountry, false)

            previousButton.setOnClickListener {
                findNavController().popBackStack()
            }

            nextButton.setOnClickListener {
                findNavController()
                    .navigate(
                        CountrySelectionFragmentDirections
                            .actionCountrySelectionFragmentToBACCalculatorFragment()
                    )

                val selectedItem = dropDownTextView?.text?.toString() ?: defaultCountry
                val selectedLimit = legalLimits[selectedItem] ?: 0.03f
                viewModel.onCountrySelected(selectedItem)
                viewModel.onBacLimitSelected(selectedLimit)
            }
        }

        return binding.root
    }
}