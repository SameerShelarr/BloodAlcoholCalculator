package com.sameershelar.bloodalcoholcalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentBACalculatorBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_BUNDLE_KEY
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_RESULT_KEY
import com.sameershelar.bloodalcoholcalculator.vm.BACalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BACalculatorFragment : Fragment() {

    private lateinit var binding: FragmentBACalculatorBinding
    private val viewModel: BACalculatorViewModel by viewModels()
    private var weight = 20
    private var gender = Gender.MALE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBACalculatorBinding.inflate(layoutInflater)

        binding.apply {
            addDrinkFab.setOnClickListener {
                findNavController().navigate(BACalculatorFragmentDirections.actionBACCalculatorFragmentToAddDrinkBottomSheetDialog())
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.updateIsPreferencesSelected(true)
        }

        viewModel.getPreferencesFlow().observe(viewLifecycleOwner) {
            weight = it.weight
            gender = it.gender
        }

        viewModel.addedDrinksLive.observe(viewLifecycleOwner) { addedDrinks ->
            viewModel.calculateAndShowBACAndTimeUntilSober(addedDrinks)
        }

        setFragmentResultListener(ADD_DRINK_RESULT_KEY) { _, bundle ->
            val selectedDrink = bundle[ADD_DRINK_BUNDLE_KEY] as Drink
            viewModel.addDrink(selectedDrink)
            Toast.makeText(requireContext(),
                "Selected Drink is ${selectedDrink.name}",
                Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}