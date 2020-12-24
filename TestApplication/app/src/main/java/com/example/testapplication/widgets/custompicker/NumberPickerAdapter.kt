package com.example.testapplication.widgets.custompicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_age.view.*
import kotlinx.android.synthetic.main.item_height.view.*
import kotlinx.android.synthetic.main.item_weight.view.*

class NumberPickerAdapter(
    private val dataSet: List<String>,
    private val layoutId: Int,
    private val itemType: Int
) :
    RecyclerView.Adapter<NumberPickerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(layoutId, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //viewHolder.itemView.numberPickerTextView.text = dataSet[position]
        when (itemType) {
            CustomPickerWidget.AGE -> {
                bindAge(viewHolder, position)
            }
            CustomPickerWidget.HEIGHT -> {
                bindHeight(viewHolder, position)
            }
            CustomPickerWidget.WEIGHT -> {
                bindWeight(viewHolder, position)
            }
        }
    }

    private fun bindAge(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_age.text = dataSet[position]

    }

    private fun bindHeight(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_height.text = dataSet[position]
    }

    private fun bindWeight(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_weight.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}