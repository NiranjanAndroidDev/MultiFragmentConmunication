package com.example.testapplication.screens.userdetail.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.testapplication.R
import com.example.testapplication.screens.viewmodel.UserViewModel
import com.example.testapplication.widgets.custompicker.CustomPickerWidget
import kotlinx.android.synthetic.main.fragment_weight.*

class WeightFragment : Fragment(R.layout.fragment_weight),
    UserDetailActivity.PickerValueChangedListener {
    private lateinit var userViewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setWeightValues()
    }

    private fun setWeightValues() {
        custom_picker.setDisplayValues(
            getWeightList(),
            LinearLayout.HORIZONTAL,
            R.layout.item_weight, this,
            CustomPickerWidget.WEIGHT
        )
    }

    private fun onWeightValueChanged(weight: String) {
        text_selected.text = weight
        userViewModel.setWeight(weight)
    }

    private fun getWeightList(): List<String> {
        var weights = ArrayList<String>()
        for (i in 0..500) {
            weights.add("$i ")
        }
        return weights
    }

    private fun initViewModel() {
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)
    }

    override fun onValueChanged() {
        custom_picker.getSelectedValue()?.let { onWeightValueChanged(it) }
    }
}