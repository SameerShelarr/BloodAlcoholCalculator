package com.sameershelar.bloodalcoholcalculator.ui

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentBACalculatorBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_BUNDLE_KEY
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_RESULT_KEY
import com.sameershelar.bloodalcoholcalculator.utils.inPercent
import com.sameershelar.bloodalcoholcalculator.vm.BACalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class BACalculatorFragment : Fragment() {

    private lateinit var binding: FragmentBACalculatorBinding
    private val viewModel: BACalculatorViewModel by viewModels()
    private var weight = 20
    private var gender = Gender.MALE
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBACalculatorBinding.inflate(layoutInflater)

        binding.apply {
            addDrinkFab.setOnClickListener {
                findNavController().navigate(
                    BACalculatorFragmentDirections
                        .actionBACCalculatorFragmentToAddDrinkBottomSheetDialog(false)
                )
            }

            historyButton.setOnClickListener {
                findNavController().navigate(
                    BACalculatorFragmentDirections
                        .actionBACCalculatorFragmentToAddDrinkBottomSheetDialog(
                            true,
                            viewModel.addedDrinksLive.value?.toTypedArray()
                        )
                )
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

        viewModel.bacLive.observe(viewLifecycleOwner) { bac ->
            if (bac > 0.03) {
                binding.apply {
                    bloodAlcoholContentText.setTextColor(Color.RED)
                    addDrinkFab.setBackgroundColor(Color.RED)
                    doNotDriveText.isVisible = true
                }
            } else {
                binding.apply {
                    bloodAlcoholContentText.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.design_default_color_primary))
                    addDrinkFab.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.design_default_color_primary))
                    doNotDriveText.isVisible = false
                }
            }
            binding.bloodAlcoholContentText.text = inPercent(bac)
        }

        viewModel.timeUntilSoberLive.observe(viewLifecycleOwner) { timeUntilSober ->
            countDownTimer?.cancel()
            val timeUntilSoberString = String.format("%.2f", timeUntilSober)
            val h = timeUntilSoberString.split(".")[0].toLong()
            val m = timeUntilSoberString.split(".")[1].toLong()
            val t = TimeUnit.HOURS.toMillis(h) + TimeUnit.MINUTES.toMillis(m)
            countDownTimer = object : CountDownTimer(t, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val minutes = (millisUntilFinished / (1000 * 60) % 60).toInt()
                    val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished).toInt()
                    binding.timeUntilSoberText.text =
                        String.format("%dh %dm", hours, minutes)
                }

                override fun onFinish() {
                    binding.timeUntilSoberText.text = "0h 0m"
                }

            }
            countDownTimer?.start()
        }

        setFragmentResultListener(ADD_DRINK_RESULT_KEY) { _, bundle ->
            val selectedDrink = bundle[ADD_DRINK_BUNDLE_KEY] as Drink
            viewModel.addDrink(selectedDrink)
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        countDownTimer?.cancel()
    }
}