package com.sameershelar.bloodalcoholcalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentBACalculatorBinding
import com.sameershelar.bloodalcoholcalculator.vm.BACalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

        lifecycleScope.launchWhenCreated {
            viewModel.updateIsPreferencesSelected(true)
        }

        viewModel.getPreferencesFlow().observe(viewLifecycleOwner) {
            weight = it.weight
            gender = it.gender

            Toast.makeText(context, "$weight and ${gender.name}", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}