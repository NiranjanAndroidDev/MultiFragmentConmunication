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

class HeightFragment : Fragment(R.layout.fragment_height),
    UserDetailActivity.PickerValueChangedListener {
    private lateinit var userViewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setHeightValues()
    }

    private fun onHeightValueChanged(height: String) {
        text_selected.text=height
        userViewModel.setHeight(height)
    }

    private fun setHeightValues() {
        custom_picker.setDisplayValues(
            getHeightList(),
            LinearLayout.VERTICAL,
            R.layout.item_height, this,
            CustomPickerWidget.HEIGHT
        )
    }

    private fun getHeightList(): List<String> {
        var heights = ArrayList<String>()
        for (i in 0..50) {
            var height = i * 5
            heights.add("$height")
        }
        return heights
    }

    private fun initViewModel() {
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)
    }

    override fun onValueChanged() {
        custom_picker.getSelectedValue()?.let { onHeightValueChanged(it) }
    }

}