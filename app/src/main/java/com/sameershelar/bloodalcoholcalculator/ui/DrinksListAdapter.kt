package com.sameershelar.bloodalcoholcalculator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sameershelar.bloodalcoholcalculator.R
import com.sameershelar.bloodalcoholcalculator.data.Drink

class DrinksListAdapter(
    private val drinksList: List<Drink>,
) :
    RecyclerView.Adapter<DrinksListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DrinksListAdapter.ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.drink_list_item_layout, parent,
            false)
    )

    override fun onBindViewHolder(holder: DrinksListAdapter.ItemViewHolder, position: Int) {
        val currentDrink = drinksList[position]
        if (currentDrink.imageResId != -1) {
            holder.drinkImageView.setImageResource(currentDrink.imageResId)
        }
        holder.drinkNameText.text = currentDrink.name
        "${currentDrink.volume}ml, ${currentDrink.abv}%".also {
            holder.drinkVolumeAndAbvText.text = it
        }
        holder.isDrinkSelectedCheckBox.isChecked = currentDrink.isSelected
    }

    override fun getItemCount(): Int = drinksList.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
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
}