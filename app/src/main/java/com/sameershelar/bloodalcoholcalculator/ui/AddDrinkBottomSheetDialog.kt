package com.sameershelar.bloodalcoholcalculator.ui

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
import com.sameershelar.bloodalcoholcalculator.utils.exhaustive
import com.sameershelar.bloodalcoholcalculator.vm.AddDrinksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

            incrementQtyButton.setOnClickListener {
                val qty = qtyText.text.toString().toInt()
                qtyText.text = if (qty < 20) qty.plus(1).toString() else "20"
            }

            decrementQtyButton.setOnClickListener {
                val qty = qtyText.text.toString().toInt()
                qtyText.text = if (qty != 1) qty.minus(1).toString() else "1"
            }

            incrementStartTimeButton.setOnClickListener {
                val mins = startTimeText.text.toString().toInt()
                startTimeText.text = if (mins < 990) mins.plus(10).toString() else "990"
            }

            decrementStartTimeButton.setOnClickListener {
                val mins = startTimeText.text.toString().toInt()
                startTimeText.text = if (mins != 0) mins.minus(10).toString() else "0"
            }

            addDrinkButton.setOnClickListener {
                val drink =
                    viewModel.addSelectedDrinkOrShowError(getString(R.string.please_select_a_drink))
                drink?.let {
                    drink.quantity = qtyText.text.toString().toInt()
                    drink.startedMinsAgo = startTimeText.text.toString().toInt()
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
}