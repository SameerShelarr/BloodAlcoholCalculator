package com.sameershelar.bloodalcoholcalculator.ui

import android.app.TimePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAddDrinkBottomSheetDialogBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_BUNDLE_KEY
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_RESULT_KEY
import com.sameershelar.bloodalcoholcalculator.utils.Constants.DrinkType.*
import com.sameershelar.bloodalcoholcalculator.utils.Constants.TIME_FORMAT_0
import com.sameershelar.bloodalcoholcalculator.utils.exhaustive
import com.sameershelar.bloodalcoholcalculator.vm.AddDrinksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddDrinkBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddDrinkBottomSheetDialogBinding
    private lateinit var adapter: DrinksListAdapter
    private val viewModel: AddDrinksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddDrinkBottomSheetDialogBinding.inflate(layoutInflater)
        adapter = DrinksListAdapter(arrayListOf())

        binding.apply {
            bottomSheetLayout.layoutParams.height =
                Resources.getSystem().displayMetrics.heightPixels / 10 * 8

            startTimeText.text = SimpleDateFormat(TIME_FORMAT_0,
                Locale.getDefault()).format(Calendar.getInstance().time)

            selectStartTimeLayout.setOnClickListener {
                val currentTime = Calendar.getInstance()
                val mTimePicker = TimePickerDialog(requireContext(),
                    { _, selectedHour, selectedMinute -> onTimeSet(selectedHour, selectedMinute) },
                    currentTime[Calendar.HOUR_OF_DAY],
                    currentTime[Calendar.MINUTE],
                    false)
                mTimePicker.setTitle("What time did you start drinking?")
                mTimePicker.show()

            }

            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                adapter.setData(
                    when (checkedId) {
                        R.id.all_chip -> {
                            viewModel.drinksList
                        }
                        R.id.beer_chip -> {
                            viewModel.drinksList.filter { drink -> drink.type == BEER }
                        }

                        R.id.whisky_chip -> {
                            viewModel.drinksList.filter { drink -> drink.type == WHISKY }
                        }

                        R.id.vodka_chip -> {
                            viewModel.drinksList.filter { drink -> drink.type == VODKA }
                        }

                        R.id.rum_chip -> {
                            viewModel.drinksList.filter { drink -> drink.type == RUM }
                        }
                        R.id.wine_chip -> {
                            viewModel.drinksList.filter { drink -> drink.type == WINE }
                        }

                        R.id.gin_chip -> {
                            viewModel.drinksList.filter { drink -> drink.type == GIN }
                        }

                        R.id.brandy_chip -> {
                            viewModel.drinksList.filter { drink -> drink.type == BRANDY }
                        }

                        else -> {
                            arrayListOf()
                        }
                    }
                )
            }

            incrementButton.setOnClickListener {
                val qty = qtyText.text.toString().toInt()
                qtyText.text = if (qty < 20) qty.plus(1).toString() else "20"
            }

            decrementButton.setOnClickListener {
                val qty = qtyText.text.toString().toInt()
                qtyText.text = if (qty != 1) qty.minus(1).toString() else "1"
            }

            addDrinkButton.setOnClickListener {
                val drink =
                    viewModel.addSelectedDrinkOrShowError(getString(R.string.please_select_a_drink))
                drink?.let {
                    drink.quantity = qtyText.text.toString().toInt()
                    setFragmentResult(ADD_DRINK_RESULT_KEY, bundleOf(ADD_DRINK_BUNDLE_KEY to drink))
                    dismiss()
                }
            }

            drinksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            drinksRecyclerView.adapter = adapter
        }

        viewModel.getAllDrinksLive().observe(viewLifecycleOwner) {
            viewModel.drinksList = it as MutableList<Drink>
            val allList = viewModel.drinksList
            adapter.setData(allList)
        }

        lifecycleScope.launch {
            viewModel.addDrinkTaskEvent.collect { event ->
                when (event) {
                    is AddDrinksViewModel.AddDrinkEvent.ShowSelectDrinkSnack -> {
                        Snackbar.make(requireView(), event.message, Snackbar.LENGTH_SHORT).show()
                    }
                }.exhaustive
            }
        }

        return binding.root
    }

    private fun onTimeSet(hourOfDay: Int, minute: Int) {
        var amPm = ""
        val datetime = Calendar.getInstance()
        datetime[Calendar.HOUR_OF_DAY] = hourOfDay
        datetime[Calendar.MINUTE] = minute
        if (datetime[Calendar.AM_PM] == Calendar.AM) amPm =
            "AM" else if (datetime[Calendar.AM_PM] == Calendar.PM) amPm = "PM"
        val strHrsToShow =
            if (datetime[Calendar.HOUR] == 0) "12" else datetime[Calendar.HOUR].toString() + ""
        (strHrsToShow + ":" + datetime[Calendar.MINUTE] + " " + amPm).also {
            binding.startTimeText.text = it
        }
    }
}