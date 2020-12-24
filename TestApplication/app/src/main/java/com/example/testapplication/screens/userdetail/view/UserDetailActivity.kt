package com.example.testapplication.screens.userdetail.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    private lateinit var fragmentAge: AgeFragment
    private lateinit var fragmentHeight: HeightFragment
    private lateinit var fragmentWeight: WeightFragment
    private lateinit var fragmentUserDetail: UserDetailFragment
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        fragmentAge = AgeFragment()
        fragmentHeight = HeightFragment()
        fragmentWeight = WeightFragment()
        fragmentUserDetail = UserDetailFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_fragment, fragmentAge)
            .commit();
        button_next.setOnClickListener { nextClicked() }
        button_back.setOnClickListener { onBackPressed() }
    }

    /**
     * Listener class for picker widget value change
     */
    interface PickerValueChangedListener {
        /**
         * handle on value change of picker
         */
        fun onValueChanged()
    }

    override fun onBackPressed() {
        val manager: FragmentManager = supportFragmentManager
        if (count <= 1) {
            super.onBackPressed()
        } else {
            if (count == 4) {
                contraint_buttons.visibility = View.VISIBLE
            }
            manager.popBackStack()
            count--
            text_page_number.text = "${this.count}/3"
        }
    }

    private fun nextClicked() {
        when (count) {
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_fragment, fragmentHeight)
                    .addToBackStack(null).commit()
            }
            2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_fragment, fragmentWeight)
                    .addToBackStack(null).commit()
            }
            3 -> {
                contraint_buttons.visibility = View.GONE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout_fragment, fragmentUserDetail)
                    .addToBackStack(null).commit()
            }
        }
        count++
        text_page_number.text = "${this.count}/3"
    }
}