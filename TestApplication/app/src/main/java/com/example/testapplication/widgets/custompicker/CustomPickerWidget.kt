package com.example.testapplication.widgets.custompicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.*
import com.example.testapplication.R
import com.example.testapplication.screens.userdetail.view.UserDetailActivity
import kotlinx.android.synthetic.main.item_age.view.*
import kotlinx.android.synthetic.main.item_height.view.*
import kotlinx.android.synthetic.main.item_weight.view.*
import kotlinx.android.synthetic.main.layout_custom_picker.view.*


class CustomPickerWidget : LinearLayout {
    var snapPosition: Int = 0
    lateinit var recyclerView: RecyclerView
    private lateinit var snapHelper: SnapHelper
    private lateinit var mValues: List<String>
    var itemType = -1;
    lateinit var pickerValueChangedListener: UserDetailActivity.PickerValueChangedListener

    /**
     * Constructor to create view.
     */
    constructor(context: Context) : this(context, null)

    /**
     * Constructor to create view.
     */
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    /**
     * Constructor to create view.
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_custom_picker,
            this, true
        )
        recyclerView = view.customNumberPickerRecyclerView
        recyclerView.isVerticalScrollBarEnabled = false
    }

    /**
     * method used to set values to CustomPicker
     */
    fun setDisplayValues(
        values: List<String>,
        scrollType: Int,
        layoutId: Int,
        pickerValueChangedListener: UserDetailActivity.PickerValueChangedListener,
        itemType: Int
    ) {
        mValues = values
        this.itemType = itemType
        this.pickerValueChangedListener = pickerValueChangedListener
        snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(null)
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = context?.let {
            NumberPickerAdapter(
                values, layoutId, itemType
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(context, scrollType, false)

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                notifySnapPositionChange(recyclerView)
                setSelectedItemStyle()
                pickerValueChangedListener.onValueChanged()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifySnapPositionChange(recyclerView)
                    setSelectedItemStyle()
                    pickerValueChangedListener.onValueChanged()
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
        if (snapPosition == 0) {
            snapPosition =
                ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() -
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()) / 2
        }
        setSelectedItemStyle()

    }

    private fun notifySnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            this.snapPosition = snapPosition
        }
    }

    private fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }

    /**
     * Method used set style of selected item in list to bold
     */
    private fun setSelectedItemStyle() {
        if (recyclerView.childCount > 0) {
            for (i in 0..mValues.size) {
                var view = recyclerView.layoutManager?.findViewByPosition(i)
                view?.let { changeStyleOfUnSelectedItem(it) }
            }
            if (snapPosition > 0) {
                var view = recyclerView.layoutManager?.findViewByPosition(snapPosition)
                view?.let { changeStyleOfSelectedItem(it) }
            }
        }
    }

    private fun changeStyleOfSelectedItem(view: View) {
        when (itemType) {
            AGE -> {
                view.text_age.setTextAppearance(R.style.TextLarge_HighLight)
            }
            HEIGHT -> {
                view.text_height.setTextAppearance(R.style.TextLarge_HighLight)
            }
            WEIGHT -> {
                view.text_weight.setTextAppearance(R.style.TextLarge_HighLight)
            }
        }
    }

    private fun changeStyleOfUnSelectedItem(view: View) {
        when (itemType) {
            AGE -> {
                view.text_age.setTextAppearance(R.style.Text_hint)
            }
            HEIGHT -> {
                view.text_height.setTextAppearance(R.style.Text_hint)
            }
            WEIGHT -> {
                view.text_weight.setTextAppearance(R.style.Text_hint)
            }
        }
    }

    /**
     * Method return selected item value.
     * @return string of selected value
     */
    fun getSelectedValue(): String? {
        if (recyclerView.childCount > 0) {
            return mValues[snapPosition]
        }
        return mValues[0]
    }

    companion object {
        const val AGE = 0
        const val HEIGHT = 1
        const val WEIGHT = 2
    }
}