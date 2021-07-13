package com.sameershelar.bloodalcoholcalculator.ui

import android.app.TimePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAddDrinkBottomSheetDialogBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.TIME_FORMAT_0
import java.text.SimpleDateFormat
import java.util.*


class AddDrinkBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddDrinkBottomSheetDialogBinding
    private lateinit var adapter: DrinksListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddDrinkBottomSheetDialogBinding.inflate(layoutInflater)
        adapter = DrinksListAdapter(getDummyList())

        binding.apply {
            bottomSheetLayout.layoutParams.height =
                Resources.getSystem().displayMetrics.heightPixels / 10 * 6

            startTimeText.text = SimpleDateFormat(TIME_FORMAT_0,
                Locale.getDefault()).format(Calendar.getInstance().time)

            selectStartTimeLayout.setOnClickListener {
                val currentTime = Calendar.getInstance()
                val mTimePicker = TimePickerDialog(requireContext(),
                    { _, selectedHour, selectedMinute -> onTimeSet(selectedHour, selectedMinute) },
                    currentTime[Calendar.HOUR_OF_DAY],
                    currentTime[Calendar.MINUTE],
                    false)
                mTimePicker.setTitle("When did you start drinking?")
                mTimePicker.show()

            }

            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.beer_chip -> {

                    }

                    R.id.whisky_chip -> {

                    }

                    R.id.vodka_chip -> {

                    }

                    R.id.rum_chip -> {

                    }
                    R.id.wine_chip -> {

                    }

                    R.id.gin_chip -> {

                    }

                    R.id.brandy_chip -> {

                    }

                }
            }

            incrementButton.setOnClickListener {
                val qty = qtyText.text.toString().toInt()
                qtyText.text = if (qty < 20) qty.plus(1).toString() else "20"
            }

            decrementButton.setOnClickListener {
                val qty = qtyText.text.toString().toInt()
                qtyText.text = if (qty != 1) qty.minus(1).toString() else "1"
            }

            drinksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            drinksRecyclerView.adapter = adapter
        }

        return binding.root
    }

    private fun getDummyList(): List<Drink> {
        val drinksList = arrayListOf<Drink>()
        drinksList.add(Drink(
            id = 0,
            name = "Kingfisher Strong",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Tuborg Strong",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "LP Strong",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))
        drinksList.add(Drink(
            id = 0,
            name = "Budweiser Magnum",
            volume = 650,
            abv = 8
        ))

        return drinksList
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