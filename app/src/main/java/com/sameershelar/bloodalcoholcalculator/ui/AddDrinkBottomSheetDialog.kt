package com.sameershelar.bloodalcoholcalculator.ui

import android.app.TimePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAddDrinkBottomSheetDialogBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.TIME_FORMAT_0
import java.text.SimpleDateFormat
import java.util.*


class AddDrinkBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddDrinkBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddDrinkBottomSheetDialogBinding.inflate(layoutInflater)

        binding.apply {
            bottomSheetLayout.layoutParams.height =
                Resources.getSystem().displayMetrics.heightPixels / 2

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