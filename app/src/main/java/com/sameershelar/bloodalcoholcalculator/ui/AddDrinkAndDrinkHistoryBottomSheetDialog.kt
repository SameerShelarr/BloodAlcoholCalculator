package com.sameershelar.bloodalcoholcalculator.ui

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.tables.AddedDrink
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAddDrinkAndDrinkHistoryBottomSheetDialogBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.DrinkType.*
import com.sameershelar.bloodalcoholcalculator.utils.exhaustive
import com.sameershelar.bloodalcoholcalculator.vm.AddDrinksAndDrinksHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddDrinkAndDrinkHistoryBottomSheetDialog : BottomSheetDialogFragment(),
    OnDeleteAddedDrinkClickListener {

    companion object {
        private const val TAG = "AddDrinkAndDrinkHistory"
    }

    private lateinit var binding: FragmentAddDrinkAndDrinkHistoryBottomSheetDialogBinding
    private lateinit var adapter: DrinksListAdapter
    private val viewModel: AddDrinksAndDrinksHistoryViewModel by viewModels()
    private val args: AddDrinkAndDrinkHistoryBottomSheetDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddDrinkAndDrinkHistoryBottomSheetDialogBinding.inflate(layoutInflater)

        binding.apply {

            addDrinkBottomSheetLayout.layoutParams.height =
                Resources.getSystem().displayMetrics.heightPixels / 10 * 8

            if (args.isHistoryMode) {

                addDrinkButton.isVisible = false
                selectQtyLayout.isVisible = false
                searchDrink.isVisible = false
                horizontalScrollView.isVisible = false
                selectStartTimeLayout.isVisible = false

                clearAllAddedDrinksButton.isVisible = true

                clearAllAddedDrinksButton.setOnClickListener {
                    viewModel.deleteAllAddedDrinks()
                }

                val constraintSetForRecyclerView = ConstraintSet()
                constraintSetForRecyclerView.clone(addDrinkBottomSheetLayout)
                constraintSetForRecyclerView.connect(
                    drinksRecyclerViewScrollView.id,
                    ConstraintSet.TOP,
                    clearAllAddedDrinksButton.id,
                    ConstraintSet.BOTTOM,
                    0
                )
                constraintSetForRecyclerView.applyTo(addDrinkBottomSheetLayout)

                adapter = DrinksListAdapter(
                    arrayListOf(),
                    true,
                    this@AddDrinkAndDrinkHistoryBottomSheetDialog
                )

                viewModel.addedDrinksLive.observe(viewLifecycleOwner) { addedDrinks ->

                    Log.d(TAG, "onCreateView: Added drinks list size is ${addedDrinks.size}")

                    if (addedDrinks.isEmpty()) {
                        drinksProgressBar.isVisible = false
                        drinksHistoryEmptyText.isVisible = true
                        clearAllAddedDrinksButton.isEnabled = false
                        clearAllAddedDrinksButton.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.button_background_disabled
                        )

                        adapter.setData(listOf())
                    } else {
                        drinksProgressBar.isVisible = false
                        drinksHistoryEmptyText.isVisible = false
                        clearAllAddedDrinksButton.isEnabled = true
                        clearAllAddedDrinksButton.background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.button_background
                        )

                        adapter.setData(getDrinksFromAddedDrinksList(addedDrinks))

                        drinksRecyclerView.layoutManager =
                            LinearLayoutManager(requireContext())
                        drinksRecyclerView.adapter = adapter
                    }
                }

            } else {

                addDrinkButton.isVisible = true
                selectQtyLayout.isVisible = true
                searchDrink.isVisible = true
                horizontalScrollView.isVisible = true
                selectStartTimeLayout.isVisible = true

                clearAllAddedDrinksButton.isVisible = false

                adapter = DrinksListAdapter(arrayListOf(), false)

                chipGroup.setOnCheckedChangeListener { _, checkedId ->
                    adapter.setData(viewModel.sortDrinksAccordingToCategorySelected(checkedId))
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

                        viewModel.addDrink(drink)

                        dismiss()
                    }
                }

                searchDrink.setOnSearchClickListener {
                    horizontalScrollView.isVisible = false
                    searchDrink.layoutParams =
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )

                    searchDrink.layoutParams =
                        ConstraintLayout.LayoutParams(
                            0,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        )
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(addDrinkBottomSheetLayout)
                    constraintSet.connect(
                        searchDrink.id,
                        ConstraintSet.TOP,
                        selectStartTimeLayout.id,
                        ConstraintSet.BOTTOM,
                        0
                    )
                    constraintSet.connect(
                        searchDrink.id,
                        ConstraintSet.START,
                        addDrinkBottomSheetLayout.id,
                        ConstraintSet.START,
                        0
                    )
                    constraintSet.connect(
                        searchDrink.id,
                        ConstraintSet.END,
                        addDrinkBottomSheetLayout.id,
                        ConstraintSet.END,
                        0
                    )
                    constraintSet.applyTo(addDrinkBottomSheetLayout)
                }

                searchDrink.setOnCloseListener {
                    horizontalScrollView.isVisible = true
                    searchDrink.layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )

                    searchDrink.layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(addDrinkBottomSheetLayout)
                    constraintSet.connect(
                        searchDrink.id,
                        ConstraintSet.TOP,
                        selectStartTimeLayout.id,
                        ConstraintSet.BOTTOM,
                        0
                    )
                    constraintSet.connect(
                        searchDrink.id,
                        ConstraintSet.START,
                        addDrinkBottomSheetLayout.id,
                        ConstraintSet.START,
                        0
                    )
                    constraintSet.applyTo(addDrinkBottomSheetLayout)
                    return@setOnCloseListener false
                }

                searchDrink.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        adapter.setData(viewModel.drinksList.filter { drink ->
                            drink.name.lowercase().contains(newText?.lowercase() ?: "")
                        })
                        return false
                    }
                })

                drinksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                drinksRecyclerView.adapter = adapter
            }
        }

        if (!args.isHistoryMode) {
            viewModel.getAllDrinksLive().observe(viewLifecycleOwner) {
                viewModel.drinksList = it as MutableList<Drink>
                val allList = viewModel.drinksList
                adapter.setData(allList)
                binding.drinksProgressBar.isVisible = false
            }
        }

        lifecycleScope.launch {
            viewModel.addDrinkTaskEvent.collect { event ->
                when (event) {
                    is AddDrinksAndDrinksHistoryViewModel.AddDrinkEvent.ShowSelectDrinkSnack -> {
                        Snackbar.make(requireView(), event.message, Snackbar.LENGTH_SHORT).show()
                    }
                }.exhaustive
            }
        }

        return binding.root
    }

    private fun getDrinksFromAddedDrinksList(addedDrinks: List<AddedDrink>) =
        addedDrinks.map { addedDrink -> addedDrink.drink }

    override fun onDeleteAddedDrink(drink: Drink) {
        Log.d(TAG, "onDeleteAddedDrink: delete clicked for ${drink.name}, ${drink.volume} ml")
        viewModel.removeDrink(drink)
    }
}