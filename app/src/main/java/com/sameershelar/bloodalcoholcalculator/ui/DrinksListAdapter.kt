package com.sameershelar.bloodalcoholcalculator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.tables.Drink

class DrinksListAdapter(
    private var drinksList: MutableList<Drink>,
    private var isHistoryMode: Boolean,
) :
    RecyclerView.Adapter<DrinksViewHolder>() {

    constructor(
        drinksList: MutableList<Drink>,
        isHistoryMode: Boolean,
        onDeleteAddedDrinkClickListener: OnDeleteAddedDrinkClickListener
    )
            : this(drinksList, isHistoryMode) {
        this.deleteDrinkClickListener = onDeleteAddedDrinkClickListener
    }

    private lateinit var deleteDrinkClickListener: OnDeleteAddedDrinkClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DrinksViewHolder = if (isHistoryMode) {
        AddedDrinkItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.added_drink_list_item_layout, parent,
                false
            ),
            deleteDrinkClickListener
        )
    } else {
        DrinkItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.drink_list_item_layout, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        val currentDrink = drinksList[position]
        when (holder) {
            is DrinkItemViewHolder -> {
                if (currentDrink.imageResId != -1) {
                    holder.drinkImageView.setImageResource(currentDrink.imageResId)
                }
                holder.drinkNameText.text = currentDrink.name
                "${currentDrink.volume}ml, ${currentDrink.abv}%".also {
                    holder.drinkVolumeAndAbvText.text = it
                }
                holder.isDrinkSelectedCheckBox.isChecked = currentDrink.isSelected
            }
            is AddedDrinkItemViewHolder -> {
                if (currentDrink.imageResId != -1) {
                    holder.drinkImageView.setImageResource(currentDrink.imageResId)
                }
                holder.drinkQtyText.text = currentDrink.quantity.toString()
                holder.drinkNameText.text = currentDrink.name
                "${currentDrink.volume}ml, ${currentDrink.abv}%".also {
                    holder.drinkVolumeAndAbvText.text = it
                }
            }
        }
    }

    override fun getItemCount(): Int = drinksList.size

    fun setData(drinksList: List<Drink>) {
        this.drinksList.forEachIndexed { _, drink ->
            drink.isSelected = false
        }
        with(this.drinksList) {
            clear()
            addAll(drinksList)
        }
        notifyDataSetChanged()
    }

    inner class DrinkItemViewHolder(itemView: View) : DrinksViewHolder(itemView),
        View.OnClickListener {
        val drinkImageView: AppCompatImageView =
            itemView.findViewById(R.id.drink_image_view)
        val drinkNameText: AppCompatTextView =
            itemView.findViewById(R.id.drink_name_text)
        val drinkVolumeAndAbvText: AppCompatTextView =
            itemView.findViewById(R.id.drink_volume_and_abv_text)
        val isDrinkSelectedCheckBox: AppCompatCheckBox =
            itemView.findViewById(R.id.is_drink_selected_check_box)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            drinksList.forEachIndexed { index, drink ->
                if (drink.isSelected && index != adapterPosition) {
                    drink.isSelected = false
                    notifyItemChanged(index)
                }
            }
            drinksList[adapterPosition].isSelected = true
            notifyItemChanged(adapterPosition)
        }
    }

    inner class AddedDrinkItemViewHolder(
        itemView: View,
        onDeleteAddedDrinkClickListener: OnDeleteAddedDrinkClickListener
    ) : DrinksViewHolder(itemView) {
        val drinkImageView: AppCompatImageView =
            itemView.findViewById(R.id.drink_image_view)
        val drinkNameText: AppCompatTextView =
            itemView.findViewById(R.id.drink_name_text)
        val drinkQtyText: AppCompatTextView =
            itemView.findViewById(R.id.qty_text)
        val drinkVolumeAndAbvText: AppCompatTextView =
            itemView.findViewById(R.id.drink_volume_and_abv_text)
        private val deleteAddedDrinkButton: AppCompatImageView =
            itemView.findViewById(R.id.delete_added_drink_button)

        init {
            deleteAddedDrinkButton.setOnClickListener {
                onDeleteAddedDrinkClickListener.onDeleteAddedDrink(drinksList[adapterPosition])
            }
        }
    }
}

open class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

interface OnDeleteAddedDrinkClickListener {
    fun onDeleteAddedDrink(drink: Drink)
}