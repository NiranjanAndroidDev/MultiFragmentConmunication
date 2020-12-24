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

class AgeFragment : Fragment(R.layout.fragment_age), UserDetailActivity.PickerValueChangedListener {

    private lateinit var userViewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setAgeValues()
    }

    private fun setAgeValues() {
        custom_picker.setDisplayValues(
            getAgeList(),
            LinearLayout.HORIZONTAL,
            R.layout.item_age, this,
            CustomPickerWidget.AGE
        )
    }

    private fun onAgeValueChanged(age: String) {
        text_selected.text=age
        userViewModel.setAge(age)
    }

    private fun getAgeList(): List<String> {
        var ages = ArrayList<String>()
        for (i in 0..100) {
            ages.add("$i ")
        }
        return ages
    }

    private fun initViewModel() {
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)
    }

    override fun onValueChanged() {
        custom_picker.getSelectedValue()?.let { onAgeValueChanged(it) }
    }
}