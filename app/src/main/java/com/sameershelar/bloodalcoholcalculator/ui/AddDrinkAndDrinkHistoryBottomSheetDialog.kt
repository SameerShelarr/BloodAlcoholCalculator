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
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Drink
import com.sameershelar.bloodalcoholcalculator.databinding.FragmentAddDrinkAndDrinkHistoryBottomSheetDialogBinding
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_BUNDLE_KEY
import com.sameershelar.bloodalcoholcalculator.utils.Constants.ADD_DRINK_RESULT_KEY
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
            if (args.isHistoryMode) {
                drinksHistoryBottomSheetLayout.isVisible = true
                addDrinkBottomSheetLayout.isVisible = false

                adapter = DrinksListAdapter(
                    arrayListOf(),
                    true,
                    this@AddDrinkAndDrinkHistoryBottomSheetDialog
                )

                drinksHistoryBottomSheetLayout.layoutParams.height =
                    Resources.getSystem().displayMetrics.heightPixels / 10 * 8

                val addedDrinksList: List<Drink> = args.addedDrinks?.toList() ?: listOf()
                Log.d(TAG, "onCreateView: Added drinks list size is ${addedDrinksList.size}")

                if (addedDrinksList.isEmpty()) {
                    drinksHistoryProgressBar.isVisible = false
                    drinksHistoryEmptyText.isVisible = true
                } else {
                    drinksHistoryProgressBar.isVisible = false
                    drinksHistoryEmptyText.isVisible = false

                    adapter.setData(addedDrinksList)

                    drinksHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    drinksHistoryRecyclerView.adapter = adapter
                }
            } else {
                drinksHistoryBottomSheetLayout.isVisible = false
                addDrinkBottomSheetLayout.isVisible = true

                adapter = DrinksListAdapter(arrayListOf(), false)

                addDrinkBottomSheetLayout.layoutParams.height =
                    Resources.getSystem().displayMetrics.heightPixels / 10 * 8

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
                        setFragmentResult(
                            ADD_DRINK_RESULT_KEY,
                            bundleOf(ADD_DRINK_BUNDLE_KEY to drink)
                        )
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
                binding.addDrinksProgressBar.isVisible = false
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

    override fun onDeleteAddedDrink(drink: Drink) {
        //TODO Delete drink
    }
}