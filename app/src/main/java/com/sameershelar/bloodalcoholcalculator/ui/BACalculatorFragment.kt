package com.sameershelar.bloodalcoholcalculator.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Gender
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentBACalculatorBinding
import com.sameershelar.bloodalcoholcalculator.utils.inPercent
import com.sameershelar.bloodalcoholcalculator.utils.navigateSafe
import com.sameershelar.bloodalcoholcalculator.vm.BACalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class BACalculatorFragment : Fragment() {

    companion object {
        private const val TAG = "BACalculatorFragment"
    }

    private lateinit var binding: FragmentBACalculatorBinding
    private val viewModel: BACalculatorViewModel by viewModels()
    private var weight = 20
    private var gender = Gender.MALE
    private var country = "India"
    private var countryBacLimit = 0.03f
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBACalculatorBinding.inflate(layoutInflater)

        binding.apply {
            addDrinkFab.setOnClickListener {
                findNavController().navigateSafe(
                    BACalculatorFragmentDirections
                        .actionBACCalculatorFragmentToAddDrinkBottomSheetDialog(false)
                )
            }

            historyButton.setOnClickListener {
                findNavController().navigateSafe(
                    BACalculatorFragmentDirections
                        .actionBACCalculatorFragmentToAddDrinkBottomSheetDialog(
                            true
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
            country = it.country
            countryBacLimit = it.countryLimit

            binding.legalLimitText.text =
                getString(R.string.legal_limit_template, country, countryBacLimit)
        }

        viewModel.bacLive.observe(viewLifecycleOwner) { bac ->
            if (bac > countryBacLimit) {
                binding.apply {
                    bloodAlcoholContentText.setTextColor(Color.RED)
                    addDrinkFab.setBackgroundColor(Color.RED)
                    doNotDriveText.isVisible = true
                }
            } else {
                binding.apply {
                    bloodAlcoholContentText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.design_default_color_primary
                        )
                    )
                    addDrinkFab.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.design_default_color_primary
                        )
                    )
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

        viewModel.addedDrinksLive.observe(viewLifecycleOwner) { addedDrinks ->
            viewModel.calculateAndShowBACAndTimeUntilSober(addedDrinks)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        countDownTimer?.cancel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.share_options_menu_button -> {

                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Blood Alcohol Calculator")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=" + requireContext().packageName
                    )
                }
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No suitable application found, Cannot send email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                try {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Blood Alcohol Calculator")
                    shareIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=" + requireContext().packageName
                    )
                    startActivity(Intent.createChooser(shareIntent, "Share Using"))
                } catch (e: Exception) {
                    Log.e(TAG, "onOptionsItemSelected: ${e.message}", e)
                }
                true
            }

            R.id.settings_options_menu_button -> {
                findNavController().navigateSafe(
                    BACalculatorFragmentDirections.actionBACCalculatorFragmentToWeightSelectionFragment(
                        true
                    )
                )
                true
            }

            R.id.about_options_menu_button -> {
                findNavController().navigateSafe(
                    BACalculatorFragmentDirections.actionBACCalculatorFragmentToAboutFragment()
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
}